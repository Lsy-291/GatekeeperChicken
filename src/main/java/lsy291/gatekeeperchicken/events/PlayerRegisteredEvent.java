package lsy291.gatekeeperchicken.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerRegisteredEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private Player player;
    private String password;

    public PlayerRegisteredEvent(Player player, String password) {
        this.player = player;
        this.password = password;
    }

    /**
     * @return Get registered player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * @return Get registration password
     */
    public String getPassword() {
        return password;
    }

    @Override
    public HandlerList getHandlers() { return handlers; }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
