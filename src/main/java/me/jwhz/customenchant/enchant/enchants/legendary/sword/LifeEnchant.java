package me.jwhz.customenchant.enchant.enchants.legendary.sword;

import me.jwhz.customenchant.config.ConfigValue;
import me.jwhz.customenchant.enchant.Enchant;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

@Enchant.EnchantInfo(
        name = "Life",
        itemTypes = Enchant.Tool.SWORD,
        rarity = Enchant.Rarity.LEGENDARY,
        maxLevel = 3
)
public class LifeEnchant extends Enchant {

    @ConfigValue(path = "Enchant.Life.steal percent.level 1")
    public double level1 = 0.15;
    @ConfigValue(path = "Enchant.Life.steal percent.level 2")
    public double level2 = 0.35;
    @ConfigValue(path = "Enchant.Life.steal percent.level 3")
    public double level3 = 0.5;

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent e){

        if(e.getEntity() instanceof Player && e.getDamager() instanceof Player){

            if (((Player) e.getDamager()).getItemInHand() != null && core.enchantManager.getEnchants(((Player) e.getDamager()).getItemInHand()).contains(this)){

                double stolen = e.getDamage() * getChance(core.enchantManager.getLevel(((Player) e.getDamager()).getItemInHand(), this));

                double health = ((Player) e.getDamager()).getHealth() + stolen;

                health = Math.max(((Player) e.getDamager()).getMaxHealth(), health);

                ((Player) e.getDamager()).setHealth(health);

            }

        }

    }

}
