package me.jwhz.customenchant.enchant.enchants.epic.sword;

import me.jwhz.customenchant.config.ConfigValue;
import me.jwhz.customenchant.enchant.Enchant;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

@Enchant.EnchantInfo(
        name = "Thunderous",
        itemTypes = Enchant.Tool.SWORD,
        rarity = Enchant.Rarity.EPIC,
        maxLevel = 3
)
public class LightningEnchant extends Enchant {

    @ConfigValue(path = "Enchant.Thunderous.chance.level 1")
    public double level1 = 0.1;
    @ConfigValue(path = "Enchant.Thunderous.chance.level 2")
    public double level2 = 1;
    @ConfigValue(path = "Enchant.Thunderous.chance.level 3")
    public double level3 = 10;

    @EventHandler
    public void onEntityAttack(EntityDamageByEntityEvent e){

        if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {

            ItemStack sword = ((Player) e.getDamager()).getInventory().getItemInHand();

            if (sword != null && core.enchantManager.getEnchants(sword).contains(this)) {

                int level = core.enchantManager.getLevel(sword, this);

                if (Math.random() * 100 < getChance(level))
                    e.getDamager().getWorld().strikeLightning(e.getEntity().getLocation());

            }

        }

    }

}
