package lsy291.gatekeeperchicken.utils;

import org.bukkit.entity.Player;

import static lsy291.gatekeeperchicken.GatekeeperChicken.pluginPrefix;

public class SendMessageAPI {
    public static void sendMsgToPlayer(Player player, String msg)
    {
        player.sendMessage(pluginPrefix + msg);
    }
}
