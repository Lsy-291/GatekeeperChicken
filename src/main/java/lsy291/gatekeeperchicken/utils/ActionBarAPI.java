package lsy291.gatekeeperchicken.utils;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import static lsy291.gatekeeperchicken.GatekeeperChicken.plugin;
import static lsy291.gatekeeperchicken.GatekeeperChicken.pluginPrefix;

public class ActionBarAPI {
    public static void sendActionBar(Player player, String message) {
        if (player == null) return;
        if (!player.isOnline()) { return; }
        player.spigot().sendMessage( ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(pluginPrefix + message));
    }

    public static void sendActionBar(Player player, String message, int duration) {
        if (player == null) return;
        sendActionBar(player, message);

        if (duration > 0) {
            // Send an empty Action Bar message after the specified duration. Ensure that messages disappear at the correct time
            // Note: The duration of the Action Bar message displayed by the client is 3 seconds
            new BukkitRunnable() {
                @Override
                public void run() {
                    sendActionBar(player, "");
                }
            }.runTaskLater(plugin, duration);
        }

        // Loop sending messages to ensure correct display duration
        while (duration > 20) {
            duration -= 20;
            new BukkitRunnable() {
                @Override
                public void run() {
                    sendActionBar(player, message);
                }
            }.runTaskLater(plugin, duration);
        }
    }
}
