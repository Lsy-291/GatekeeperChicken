package lsy291.gatekeeperchicken.utils;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class PlayerAPI {
    private static HashMap<Player, Collection<PotionEffect>> playerEffectCache = new HashMap<>();

    public static void addPlayerEffectCache(Player player, Collection<PotionEffect> effects) { playerEffectCache.put(player, effects); }
    public static void removePlayerEffectCache(Player player) { playerEffectCache.remove(player); }
    public static void getPlayerEffectCache(Player player) { playerEffectCache.get(player); }
    public static void removePlayerAllPotionEffects(Player player) {
        Iterator<PotionEffect> iterator = player.getActivePotionEffects().iterator();
        while (iterator.hasNext()) {
            PotionEffect effect = iterator.next();
            player.removePotionEffect(effect.getType());
        }
    }
    public static void restorePlayerPotionEffects(Player player) {
        player.addPotionEffects(playerEffectCache.get(player));
    }
}
