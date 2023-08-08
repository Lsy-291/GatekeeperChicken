package lsy291.gatekeeperchicken.listeners;

import lsy291.gatekeeperchicken.utils.PlayerIdentityStatsAPI;
import lsy291.gatekeeperchicken.language.Message;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;

import static lsy291.gatekeeperchicken.GatekeeperChicken.*;
import static lsy291.gatekeeperchicken.utils.SendMessageAPI.sendMsgToPlayer;
import static lsy291.gatekeeperchicken.config.ConfigItems.*;
import static lsy291.gatekeeperchicken.utils.PlayerAPI.*;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void PlayerJoinServer(PlayerJoinEvent event) {
        event.setJoinMessage(null);
        Player player = event.getPlayer();

        if (PlayerIdentityStatsAPI.isPlayerLoggedIn(player.getUniqueId())) {
            sendMsgToPlayer(player, language.getString(Message.LOGIN_SUCESS_AUTO_LOGIN));

            Player[] players = Bukkit.getOnlinePlayers().toArray(new Player[0]);
            for (Player onlinePlayer : players) {
                sendMsgToPlayer(onlinePlayer, language.getString(Message.LOGIN_SUCESS).replace("%player%", player.getDisplayName()));
                onlinePlayer.playSound(player.getLocation(), Sound.valueOf(loginSucessSound), loginSoundVolume, 1);
            }

            return;
        }

        addPlayerEffectCache(player, player.getActivePotionEffects());
        removePlayerAllPotionEffects(player);

        if (!getDatabase().hasData(player.getUniqueId())) sendMsgToPlayer(player, language.getString(Message.REGISTER_REQUIRED));
        else sendMsgToPlayer(player, language.getString(Message.LOGIN_REQUIRED));

        PlayerIdentityStatsAPI.setPlayerLoginTimeOut(player.getUniqueId(), loginTimeout);
        player.setAllowFlight(true);
    }
}
