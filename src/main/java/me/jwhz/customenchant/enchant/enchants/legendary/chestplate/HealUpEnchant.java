package me.jwhz.customenchant.enchant.enchants.legendary.chestplate;

import me.jwhz.customenchant.config.ConfigValue;
import me.jwhz.customenchant.enchant.Enchant;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

@Enchant.EnchantInfo(
        name = "Reborn",
        itemTypes = Enchant.Tool.CHESTPLATE,
        rarity = Enchant.Rarity.LEGENDARY,
        maxLevel = 3
)
public class HealUpEnchant extends Enchant {

    @ConfigValue(path = "Enchant.Reborn.chance.level 1")
    public double level1 = 0.1;
    @ConfigValue(path = "Enchant.Reborn.chance.level 2")
    public double level2 = 1;
    @ConfigValue(path = "Enchant.Reborn.chance.level 3")
    public double level3 = 10;

    @EventHandler
    public void onEntityAttack(EntityDamageByEntityEvent e){

        if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {

            ItemStack chestplate = ((Player) e.getEntity()).getInventory().getArmorContents()[2];

            if (chestplate != null && core.enchantManager.getEnchants(chestplate).contains(this)) {

                int level = core.enchantManager.getLevel(chestplate, this);

                if (Math.random() * 100 < getChance(level))
                    ((Player) e.getEntity()).setHealth(((Player) e.getEntity()).getMaxHealth());

            }

        }

    }

}
