package lsy291.gatekeeperchicken.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static lsy291.gatekeeperchicken.GatekeeperChicken.plugin;

public class EncryptionAPI {
    public static String SHA1Encryption(String plaintext) {
        MessageDigest sha1Digest = null;

        try {
            sha1Digest = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            plugin.getLogger().warning("Failed to encrypt the password using SHA-1.");
            throw new RuntimeException(e);
        }

        byte[] hashBytes = sha1Digest.digest(plaintext.getBytes());

        StringBuilder hexString = new StringBuilder();
        for (byte b : hashBytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();
    }
}
