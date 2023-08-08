package lsy291.gatekeeperchicken.handler;

import lsy291.gatekeeperchicken.events.PlayerLoggedEvent;
import lsy291.gatekeeperchicken.events.PlayerRegisteredEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

import static lsy291.gatekeeperchicken.GatekeeperChicken.*;
import static lsy291.gatekeeperchicken.config.ConfigItems.*;
import static lsy291.gatekeeperchicken.utils.EncryptionAPI.SHA1Encryption;

public class MainHandler {
    public static int allowRegistration(Player player, String password, String confirmPassword) {
        if (getDatabase().hasData(player.getUniqueId())) return 1;

        if (password.equals(confirmPassword))
        {
            if (!(password.length() <= passwordMaxLength && password.length() >= passwordMinLength)) return 3;

            Bukkit.getScheduler().runTask(plugin, () -> {
                Bukkit.getPluginManager().callEvent(new PlayerRegisteredEvent(player, enablePassworldEncryption ? SHA1Encryption(password) : password));
            });
            return 0;
        }
        else return 2;
    }

    public static int allowLogin(Player player, String password) {
        if (!getDatabase().hasData(player.getUniqueId())) return 1;
        if (getDatabase().isPasswordMatchingNow(player.getUniqueId(), password)) {
            Bukkit.getScheduler().runTask(plugin, () -> {
                Bukkit.getPluginManager().callEvent(new PlayerLoggedEvent(player));
            });
            return 0;
        }
        else return 2;
    }

    public static int allowChangePassword(UUID player, String oldPassword, String newPassword) {
        if (oldPassword.equals(newPassword)) return 1;
        if (getDatabase().isPasswordMatchingNow(player, oldPassword)) {
            if (!(newPassword.length() <= passwordMaxLength && newPassword.length() >= passwordMinLength)) return 3;
            getDatabase().setPlayerData(player, enablePassworldEncryption ? SHA1Encryption(newPassword) : newPassword);
            return 0;
        } else return 2;
    }
}
