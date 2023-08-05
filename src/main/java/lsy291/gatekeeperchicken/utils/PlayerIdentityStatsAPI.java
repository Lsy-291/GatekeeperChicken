package lsy291.gatekeeperchicken.utils;

import java.util.HashMap;
import java.util.UUID;

public class PlayerIdentityStatsAPI {
    private static HashMap<UUID, Integer> LoggedInPlayers = new HashMap<>();
    private static HashMap<UUID, Integer> LoggingInPlayers = new HashMap<>();

    public static void setPlayerLoginState(UUID player, int autoLoginTime) { LoggedInPlayers.put(player, autoLoginTime); }
    public static void removePlayerLoginState(UUID player) { LoggedInPlayers.remove(player); }
    public static void setPlayerLoginTimeOut(UUID player, int loginTimeOut) { LoggingInPlayers.put(player, loginTimeOut); }
    public static void removePlayerLoginTimeOut(UUID player) { LoggingInPlayers.remove(player); }

    public static boolean isPlayerLoggedIn(UUID player) { return LoggedInPlayers.containsKey(player); }
    public static boolean isPlayerLoggingIn(UUID player) { return LoggingInPlayers.containsKey(player); }

    public static HashMap<UUID, Integer> getLoggedInPlayers() { return LoggedInPlayers; }

    public static HashMap<UUID, Integer> getLoggingInPlayers() { return LoggingInPlayers; }
}
