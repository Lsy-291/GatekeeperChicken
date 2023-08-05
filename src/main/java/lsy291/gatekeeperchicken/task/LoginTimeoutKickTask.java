package lsy291.gatekeeperchicken.task;

import lsy291.gatekeeperchicken.utils.PlayerIdentityStatsAPI;
import lsy291.gatekeeperchicken.language.Message;
import org.bukkit.Bukkit;

import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import static lsy291.gatekeeperchicken.GatekeeperChicken.language;
public class LoginTimeoutKickTask implements Runnable{

    @Override
    public void run() {
        Iterator<Map.Entry<UUID, Integer>> iterator = PlayerIdentityStatsAPI.getLoggingInPlayers().entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<UUID, Integer> entry = iterator.next();
            UUID player = entry.getKey();
            int loginTimeout = entry.getValue();

            if (loginTimeout > 1) PlayerIdentityStatsAPI.setPlayerLoginTimeOut(player, --loginTimeout);
            else if (loginTimeout == 1) {
                Bukkit.getPlayer(player).kickPlayer(language.getString(Message.LOGIN_FAILED_TIME_OUT));
                iterator.remove();
            }
        }
    }
}
