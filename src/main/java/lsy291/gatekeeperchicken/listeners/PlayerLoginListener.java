package lsy291.gatekeeperchicken.listeners;

import lsy291.gatekeeperchicken.handler.LoginRegistrationHandler;
import lsy291.gatekeeperchicken.utils.PlayerIdentityStatsAPI;
import lsy291.gatekeeperchicken.language.Message;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import static lsy291.gatekeeperchicken.GatekeeperChicken.language;
import static lsy291.gatekeeperchicken.config.ConfigItems.*;
import static lsy291.gatekeeperchicken.utils.SendMessageAPI.sendMsgToPlayer;

public class PlayerLoginListener implements Listener {

    @EventHandler
    public void onPlayerLogin(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();

        if (!PlayerIdentityStatsAPI.isPlayerLoggedIn(player.getUniqueId()) && (loginMethod.equals("both") || loginMethod.equals("text")))
        {
            // Check if the message is in login format ".l/login xxxx xxxx"
            if (message.startsWith(".l") || message.startsWith(".login")) {
                String[] split = message.split(" ");
                if (split.length == 2) {
                    String password = split[1];

                    int loginState = LoginRegistrationHandler.allowThroughLogin(player, password);
                    switch (loginState) {
                        case 1:
                            sendMsgToPlayer(player, language.getString(Message.LOGIN_FAILED_UNREGISTERED));
                            break;
                        case 2:
                            sendMsgToPlayer(player, language.getString(Message.LOGIN_FAILED));
                            break;
                    }
                    if (loginState != 0) player.playSound(player.getLocation(), Sound.valueOf(loginFailedSound), loginSoundVolume, 1);
                }
                else
                {
                    sendMsgToPlayer(player, language.getString(Message.LOGIN_FAILED_USAGE_ERROR));
                    player.playSound(player.getLocation(), Sound.valueOf(loginFailedSound), loginSoundVolume, 1);
                }
            }

            // Cancel the chat event to prevent the message from being displayed in the chat bar.
            event.setCancelled(true);
        }
    }
}
