package academy.pocu.comp3500.lab5;

import javax.crypto.Cipher;
import java.security.MessageDigest;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;

public class Bank {
    private HashMap<String, Integer> pubKeyHash = new HashMap<>();

    public Bank(byte[][] pubKeys, final long[] amounts) {
        for (int i = 0; i < amounts.length; ++i) {
            pubKeyHash.put(Base64.getEncoder().encodeToString(pubKeys[i]), Math.toIntExact(amounts[i]));
        }
    }

    public long getBalance(final byte[] pubKey) {
        if (pubKey == null) {
            return 0;
        }
        Integer integer = pubKeyHash.get(Base64.getEncoder().encodeToString(pubKey));
        if (integer == null) {
            return 0;
        }
        return integer;
    }

    public boolean transfer(final byte[] from, byte[] to, final long amount, final byte[] signature) {
        if (amount < 0) {
            return false;
        } else if (from.length > 0 && to.length > 0 && signature.length > 0) {

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

                if (decryptSignature.hashCode() == fromToAmountShaString.hashCode() && pubKeyHash.containsKey(fromBase64) && pubKeyHash.containsKey(toBase64)) {

                    Integer fromCoin = pubKeyHash.get(fromBase64);
                    Integer toCoin = pubKeyHash.get(toBase64);
                    if (fromCoin != null && toCoin != null && fromCoin.longValue() >= amount) {
                        pubKeyHash.put(fromBase64, Math.toIntExact(fromCoin.longValue() - amount));
                        pubKeyHash.put(toBase64, Math.toIntExact(toCoin.longValue() + amount));
                    }
                    return true;
                }
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }
}