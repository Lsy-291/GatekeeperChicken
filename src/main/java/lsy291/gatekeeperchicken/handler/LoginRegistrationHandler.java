package lsy291.gatekeeperchicken.handler;

import lsy291.gatekeeperchicken.events.PlayerLoggedEvent;
import lsy291.gatekeeperchicken.events.PlayerRegisteredEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static lsy291.gatekeeperchicken.GatekeeperChicken.*;
import static lsy291.gatekeeperchicken.config.ConfigItems.*;

public class LoginRegistrationHandler {
    public static int allowThroughRegistration(Player player, String password, String confirmPassword) {
        if (getDatabase().hasData(player.getUniqueId())) return 1;

        if (password.equals(confirmPassword))
        {
            if (password.length() <= passwordMaxLength && password.length() >= passwordMinLength)
            {
                Bukkit.getScheduler().runTask(plugin, () -> {
                    String encryptedPassword = password;
                    if (enablePassworldEncryption) encryptedPassword = SHA1Encryption(password);
                    Bukkit.getPluginManager().callEvent(new PlayerRegisteredEvent(player, encryptedPassword));
                });

                return 0;
            }
            else return 3;
        }
        else return 2;
    }

    public static int allowThroughLogin(Player player, String password) {
        if (!getDatabase().hasData(player.getUniqueId())) return 1;
        if (SHA1Encryption(password).equals(getDatabase().getPlayerData(player.getUniqueId()).get("pwd"))) {
            Bukkit.getScheduler().runTask(plugin, () -> {
                Bukkit.getPluginManager().callEvent(new PlayerLoggedEvent(player));
            });
            return 0;
        }
        else return 2;
    }

    private static String SHA1Encryption(String plaintext) {
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
