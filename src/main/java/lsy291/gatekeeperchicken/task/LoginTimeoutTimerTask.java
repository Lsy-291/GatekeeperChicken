package lsy291.gatekeeperchicken.task;

import lsy291.gatekeeperchicken.utils.PlayerIdentityStatsAPI;
import lsy291.gatekeeperchicken.language.Message;
import lsy291.gatekeeperchicken.utils.ActionBarAPI;
import org.bukkit.Bukkit;

import static lsy291.gatekeeperchicken.GatekeeperChicken.language;

public class LoginTimeoutTimerTask implements Runnable{
    @Override
    public void run() {
        PlayerIdentityStatsAPI.getLoggingInPlayers().forEach((player, loginTimeout) -> {
            ActionBarAPI.sendActionBar(Bukkit.getPlayer(player), language.getString(Message.LOGIN_TIMEOUT_TIMER).replace("%timeout%", String.valueOf(loginTimeout)));
        });
    }
}
