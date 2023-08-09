package lsy291.gatekeeperchicken.utils;

import org.bukkit.entity.Player;

import static lsy291.gatekeeperchicken.GatekeeperChicken.pluginPrefix;
import static lsy291.gatekeeperchicken.config.ConfigItems.enablePluginPrefix;

public class SendMessageAPI {
    public static void sendMsgToPlayer(Player player, String msg) { player.sendMessage(enablePluginPrefix ? pluginPrefix : "" + msg); }
}
