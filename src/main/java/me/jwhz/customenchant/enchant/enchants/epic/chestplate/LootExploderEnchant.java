package me.jwhz.customenchant.enchant.enchants.epic.chestplate;

import me.jwhz.customenchant.config.ConfigValue;
import me.jwhz.customenchant.enchant.Enchant;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

@Enchant.EnchantInfo(
        name = "Implode",
        itemTypes = Enchant.Tool.CHESTPLATE,
        rarity = Enchant.Rarity.EPIC,
        keepChecking = false,
        maxLevel = 3
)
public class LootExploderEnchant extends Enchant {

    @ConfigValue(path = "Enchant.Implode.chance.level 1")
    public double level1 = 0.1;
    @ConfigValue(path = "Enchant.Implode.chance.level 2")
    public double level2 = 1;
    @ConfigValue(path = "Enchant.Implode.chance.level 3")
    public double level3 = 10;

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {

        ItemStack chestplate = e.getEntity().getInventory().getArmorContents()[2];

        if (chestplate != null && core.enchantManager.getEnchants(chestplate).contains(this)) {

            int level = core.enchantManager.getLevel(chestplate, this);

            if (Math.random() * 100 < getChance(level)){

                TNTPrimed tnt = (TNTPrimed) e.getEntity().getWorld().spawnEntity(e.getEntity().getLocation(), EntityType.PRIMED_TNT);
                tnt.setFuseTicks(1);

            }

        }

    }

}
