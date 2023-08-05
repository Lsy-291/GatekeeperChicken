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

public class PlayerRegisterListener implements Listener {

    @EventHandler
    public void onPlayerRegister(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();

        if (!PlayerIdentityStatsAPI.isPlayerLoggedIn(player.getUniqueId()) && (loginMethod.equals("both") || loginMethod.equals("text")))
        {
            // Check if the message is in registration format ".reg/register xxxx xxxx"
            if (message.startsWith(".reg") || message.startsWith(".register")) {
                String[] split = message.split(" ");
                if (split.length == 3) {
                    String password = split[1];
                    String confirmPassword = split[2];

                    int registerState = LoginRegistrationHandler.allowThroughRegistration(player, password, confirmPassword);
                    switch (registerState) {
                        case 1:
                            sendMsgToPlayer(player, language.getString(Message.REGISTER_FAILED_ALREADY_REGISTERED));
                            break;
                        case 2:
                            sendMsgToPlayer(player, language.getString(Message.REGISTER_FAILED_PASSWORD_NOT_EQUAL));
                            break;
                        case 3:
                            sendMsgToPlayer(player, language.getString(Message.REGISTER_FAILED_LENGTH_INVAILD)
                                    .replace("%passwordMinLength%", String.valueOf(passwordMinLength))
                                    .replace("%passwordMaxLength%", String.valueOf(passwordMaxLength)));
                            break;
                    }
                    if (registerState != 0) player.playSound(player.getLocation(), Sound.valueOf(loginFailedSound), loginSoundVolume, 1);
                }
                else
                {
                    sendMsgToPlayer(player, language.getString(Message.REGISTER_FAILED_USAGE_ERROR));
                    player.playSound(player.getLocation(), Sound.valueOf(loginFailedSound), loginSoundVolume, 1);
                }
            }

            // Cancel the chat event to prevent the message from being displayed in the chat bar.
            event.setCancelled(true);
        }
    }
}
