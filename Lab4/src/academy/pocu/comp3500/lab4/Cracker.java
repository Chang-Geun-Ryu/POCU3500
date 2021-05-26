package academy.pocu.comp3500.lab4;

import academy.pocu.comp3500.lab4.pocuhacker.RainbowTable;
import academy.pocu.comp3500.lab4.pocuhacker.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.HashMap;
import java.util.zip.CRC32;
import java.security.NoSuchAlgorithmException;

public final class Cracker {
    private User[] userTable;
    private String[] plainTexts;
    private int hashAlgorismIndex = -1;

    public Cracker(User[] userTable, String email, String password) {
        String[] cryptoAlgos = {"MD2", "MD5", "SHA-1", "SHA-256"};
        this.plainTexts = new String[userTable.length];
        this.userTable = userTable;
        int checkIndex = -1;
        CRC32 crc32 = new CRC32();
        MessageDigest md;

        for (int i = 0; i < userTable.length; ++i) {
            if (userTable[i].getEmail().hashCode() == email.hashCode()) {
                checkIndex = i;
                break;
            }
        }

        crc32.update(password.getBytes(StandardCharsets.UTF_8));
        String str = String.format("%d", crc32.getValue());

        if (checkIndex == -1) {
            return;
        }

        if (userTable[checkIndex].getPasswordHash().hashCode() == str.hashCode()) {
            hashAlgorismIndex = 0;
            return;
        }

        try {
            for (int i = 0; i < cryptoAlgos.length; ++i) {
                md = MessageDigest.getInstance(cryptoAlgos[i]);
                byte[] bytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
                str = Base64.getEncoder().encodeToString(bytes);

                if (userTable[checkIndex].getPasswordHash().hashCode() == str.hashCode()) {
                    this.hashAlgorismIndex = i + 1;
                    break;
                }
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String[] run(final RainbowTable[] rainbowTables) {
        if (hashAlgorismIndex >= rainbowTables.length) {
            return this.plainTexts;
        }

        for (int i = 0; i < this.userTable.length; ++i) {
            this.plainTexts[i] = rainbowTables[hashAlgorismIndex].get(this.userTable[i].getPasswordHash());
        }
        return this.plainTexts;
    }
}