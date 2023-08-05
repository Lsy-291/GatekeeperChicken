package lsy291.gatekeeperchicken.task;

import lsy291.gatekeeperchicken.utils.PlayerIdentityStatsAPI;

import java.util.Map;
import java.util.UUID;

public class AutoLoginTask implements Runnable {

    @Override
    public void run() {
        for (Map.Entry<UUID, Integer> entry : PlayerIdentityStatsAPI.getLoggedInPlayers().entrySet()) {
            UUID player = entry.getKey();
            int autoLoginTime = entry.getValue();

            if (autoLoginTime > 0) PlayerIdentityStatsAPI.setPlayerLoginState(player, --autoLoginTime);
            else if (autoLoginTime == 0) PlayerIdentityStatsAPI.removePlayerLoginState(player);
        }
    }
}
