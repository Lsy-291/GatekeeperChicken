package lsy291.gatekeeperchicken.listeners;

import lsy291.gatekeeperchicken.events.PlayerRegisteredEvent;
import lsy291.gatekeeperchicken.utils.PlayerIdentityStatsAPI;
import lsy291.gatekeeperchicken.language.Message;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import static lsy291.gatekeeperchicken.GatekeeperChicken.*;
import static lsy291.gatekeeperchicken.config.ConfigItems.*;
import static lsy291.gatekeeperchicken.utils.PlayerAPI.restorePlayerPotionEffects;
import static lsy291.gatekeeperchicken.utils.SendMessageAPI.sendMsgToPlayer;

public class PlayerRegisteredListener implements Listener {
    @EventHandler
    public void onPlayerRegistered(PlayerRegisteredEvent event) {
        Player player = event.getPlayer();

        player.setAllowFlight(false);
        restorePlayerPotionEffects(player);

        PlayerIdentityStatsAPI.setPlayerLoginState(player.getUniqueId(), autoLoginTime);
        PlayerIdentityStatsAPI.removePlayerLoginTimeOut(player.getUniqueId());

        getDatabase().setPlayerData(player.getUniqueId(), event.getPassword());

        sendMsgToPlayer(player, language.getString(Message.REGISTER_SUCESS));

        Player[] players = Bukkit.getOnlinePlayers().toArray(new Player[0]);
        for (Player onlinePlayer : players) {
            sendMsgToPlayer(onlinePlayer, language.getString(Message.LOGIN_SUCESS).replace("%player%", player.getDisplayName()));
            onlinePlayer.playSound(player.getLocation(), Sound.valueOf(loginSucessSound), loginSoundVolume, 1);
        }
    }
}
