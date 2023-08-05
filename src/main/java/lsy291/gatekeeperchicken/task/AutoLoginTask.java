package lsy291.gatekeeperchicken.task;

import lsy291.gatekeeperchicken.utils.PlayerIdentityStatsAPI;
import org.bukkit.Bukkit;

import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

public class AutoLoginTask implements Runnable {

    @Override
    public void run() {
        Iterator<Map.Entry<UUID, Integer>> iterator = PlayerIdentityStatsAPI.getLoggedInPlayers().entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<UUID, Integer> entry = iterator.next();
            UUID player = entry.getKey();
            int autoLoginTime = entry.getValue();

            if (autoLoginTime > 1) PlayerIdentityStatsAPI.setPlayerLoginState(player, --autoLoginTime);
            else if (autoLoginTime == 1 && Bukkit.getPlayer(player) == null) iterator.remove();
        }
    }
}
