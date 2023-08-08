package lsy291.gatekeeperchicken.listeners;

import lsy291.gatekeeperchicken.handler.MainHandler;
import lsy291.gatekeeperchicken.language.Message;
import lsy291.gatekeeperchicken.utils.PlayerIdentityStatsAPI;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import static lsy291.gatekeeperchicken.GatekeeperChicken.language;
import static lsy291.gatekeeperchicken.config.ConfigItems.*;
import static lsy291.gatekeeperchicken.utils.SendMessageAPI.sendMsgToPlayer;

public class PlayerChangePasswordListener implements Listener {
    @EventHandler
    public void onPlayerRegister(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();

        if (PlayerIdentityStatsAPI.isPlayerLoggedIn(player.getUniqueId()) && (loginMethod.equals("both") || loginMethod.equals("text")))
        {
            // Check if the message is in change password format ".cp/changepassword xxxx xxxx"
            if (message.startsWith(".cp") || message.startsWith(".changepassword")) {
                String[] split = message.split(" ");
                if (split.length == 3) {
                    String oldPassword = split[1];
                    String newPassword = split[2];

                    int changeState = MainHandler.allowChangePassword(player.getUniqueId(), oldPassword, newPassword);
                    switch (changeState) {
                        case 0:
                            sendMsgToPlayer(player, language.getString(Message.CHANGE_PASSWORD_SUCESS));
                        case 1:
                            sendMsgToPlayer(player, language.getString(Message.CHANGE_PASSWORD_FAILED_EQUAL));
                            break;
                        case 2:
                            sendMsgToPlayer(player, language.getString(Message.CHANGE_PASSWORD_FAILED_WORNG_ORIGINAL_PASSWORD));
                            break;
                        case 3:
                            sendMsgToPlayer(player, language.getString(Message.CHANGE_PASSWORD_LENGTH_INVAILD)
                                    .replace("%passwordMinLength%", String.valueOf(passwordMinLength))
                                    .replace("%passwordMaxLength%", String.valueOf(passwordMaxLength)));
                            break;
                    }
                    if (changeState != 0) player.playSound(player.getLocation(), Sound.valueOf(loginFailedSound), loginSoundVolume, 1);
                }
                else
                {
                    sendMsgToPlayer(player, language.getString(Message.CHANGE_PASSWORD_FAILED_USAGE_ERROR));
                    player.playSound(player.getLocation(), Sound.valueOf(loginFailedSound), loginSoundVolume, 1);
                }
            }

            // Cancel the chat event to prevent the message from being displayed in the chat bar.
            event.setCancelled(true);
        }
    }
}
