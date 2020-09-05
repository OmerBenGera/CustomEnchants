package me.jwhz.customenchant.enchant.enchants.epic.sword;

import me.jwhz.customenchant.enchant.Enchant;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

@Enchant.EnchantInfo(
        name = "Assassin",
        itemTypes = {Enchant.Tool.SWORD},
        rarity = Enchant.Rarity.EPIC,
        keepChecking = false
)
public class InstaEnchant extends Enchant {

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent e) {

        if (e.getDamager() instanceof Player && ((Player) e.getDamager()).getItemInHand() != null && core.enchantManager.getEnchants(((Player) e.getDamager()).getItemInHand()).contains(this) &&
                (e.getEntity() instanceof Blaze || e.getEntity() instanceof Skeleton || e.getEntity() instanceof Zombie)) {

            e.setDamage(Integer.MAX_VALUE);

        }

    }

}
