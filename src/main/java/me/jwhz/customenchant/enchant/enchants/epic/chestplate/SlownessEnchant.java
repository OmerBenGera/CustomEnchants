package me.jwhz.customenchant.enchant.enchants.epic.chestplate;

import me.jwhz.customenchant.config.ConfigValue;
import me.jwhz.customenchant.enchant.Enchant;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@Enchant.EnchantInfo(
        name = "Freeze",
        itemTypes = Enchant.Tool.CHESTPLATE,
        rarity = Enchant.Rarity.EPIC,
        maxLevel = 3
)
public class SlownessEnchant extends Enchant {

    @ConfigValue(path = "Enchant.Freeze.chance.level 1")
    public double level1 = 0.1;
    @ConfigValue(path = "Enchant.Freeze.chance.level 2")
    public double level2 = 1;
    @ConfigValue(path = "Enchant.Freeze.chance.level 3")
    public double level3 = 10;
    @ConfigValue(path = "Enchant.Freeze.duration in ticks")
    public int duration = 100;

    @EventHandler
    public void onPlayerAttack(EntityDamageByEntityEvent e) {

        if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {

            ItemStack chestplate = ((Player) e.getEntity()).getInventory().getArmorContents()[2];

            if (chestplate != null && core.enchantManager.getEnchants(chestplate).contains(this)) {

                int level = core.enchantManager.getLevel(chestplate, this);

                if (Math.random() * 100 < getChance(level))
                    ((Player) e.getDamager()).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, duration, 0));

            }

        }

    }

}
