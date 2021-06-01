package academy.pocu.comp3500.lab5;

import javax.crypto.Cipher;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;

public class Bank {
    private HashMap<Integer, Long> pubKeyHash = new HashMap<>();

    public Bank(byte[][] pubKeys, final long[] amounts) {
        for (int i = 0; i < amounts.length; ++i) {
            pubKeyHash.put(Base64.getEncoder().encodeToString(pubKeys[i]).hashCode(), amounts[i]);
        }
    }

    public long getBalance(final byte[] pubKey) {
        if (pubKey == null) {
            return 0;
        }
        Long l = pubKeyHash.get(Base64.getEncoder().encodeToString(pubKey).hashCode());
        if (l == null) {
            return 0;
        }
        return l;
    }

    public boolean transfer(final byte[] from, byte[] to, final long amount, final byte[] signature) {
        if (amount <= 0) {
            return false;
        } else { //if (from.length > 0 && to.length > 0 && signature.length > 0) {

            ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
            buffer.putLong(amount);
            byte[] longBytes = buffer.array();
            byte[] bytes = new byte[from.length + to.length + longBytes.length];

            System.arraycopy(from, 0, bytes, 0, from.length);
            System.arraycopy(to, 0, bytes, from.length, to.length);
            System.arraycopy(longBytes, 0, bytes, from.length + to.length, longBytes.length);

            try {
                MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
                byte[] fromToAmountShaBytes = sha256.digest(bytes);
                String fromToAmountShaString = new String(fromToAmountShaBytes, StandardCharsets.UTF_8);

                X509EncodedKeySpec keySpec = new X509EncodedKeySpec(from);
                KeyFactory kf = KeyFactory.getInstance("RSA");
                PublicKey privKey = kf.generatePublic(keySpec);

                Cipher rsa = Cipher.getInstance("RSA");
                rsa.init(Cipher.DECRYPT_MODE, privKey);
                byte[] decrypt = rsa.doFinal(signature);
                String decryptSignature = new String(decrypt, StandardCharsets.UTF_8);
                String fromBase64 = Base64.getEncoder().encodeToString(from);
                String toBase64 = Base64.getEncoder().encodeToString(to);

                if (pubKeyHash.containsKey(fromBase64.hashCode()) == false || pubKeyHash.containsKey(toBase64.hashCode()) == false) {
                    return false;
                }

                if (decryptSignature.hashCode() == fromToAmountShaString.hashCode()) {
                    Long fromCoin = pubKeyHash.get(fromBase64.hashCode());
                    Long toCoin = pubKeyHash.get(toBase64.hashCode());
                    Long amountLong = amount;
                    Long longMax = Long.MAX_VALUE;
                    BigInteger toResult = new BigInteger(toCoin.toString()).add(new BigInteger(amountLong.toString()));

                    if (fromCoin != null && toCoin != null && fromCoin >= amount && toResult.compareTo(new BigInteger(longMax.toString())) <= 0) {
                        pubKeyHash.put(fromBase64.hashCode(), (fromCoin - amount));
                        pubKeyHash.put(toBase64.hashCode(), (toCoin + amount));
                        return true;
                    }
                }
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }
}