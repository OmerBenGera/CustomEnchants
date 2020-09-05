package me.jwhz.customenchant.enchant.enchants.epic.leggings;

import me.jwhz.customenchant.config.ConfigValue;
import me.jwhz.customenchant.enchant.Enchant;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@Enchant.EnchantInfo(
        name = "Vanish",
        itemTypes = Enchant.Tool.LEGGINGS,
        rarity = Enchant.Rarity.EPIC,
        maxLevel = 3
)
public class BlindnessEnchant extends Enchant {

    @ConfigValue(path = "Enchant.Vanish.chance.level 1")
    public double level1 = 0.1;
    @ConfigValue(path = "Enchant.Vanish.chance.level 2")
    public double level2 = 1;
    @ConfigValue(path = "Enchant.Vanish.chance.level 3")
    public double level3 = 10;

    @EventHandler
    public void onEntityAttack(EntityDamageByEntityEvent e) {

        if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {

            ItemStack leggings = ((Player) e.getEntity()).getInventory().getArmorContents()[1];

            if (leggings != null && core.enchantManager.getEnchants(leggings).contains(this)) {

                int level = core.enchantManager.getLevel(leggings, this);

                if (Math.random() * 100 < getChance(level))
                    ((Player) e.getDamager()).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, level * 60, 0));

            }

        }

    }

}
