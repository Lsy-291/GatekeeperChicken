package lsy291.gatekeeperchicken.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerLoggedEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private Player player;

    public PlayerLoggedEvent(Player player) {
        this.player = player;
    }

    /**
     * @return Get successfully logged in player
     */
    public Player getPlayer() {
        return player;
    }

    @Override
    public HandlerList getHandlers() { return handlers; }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
