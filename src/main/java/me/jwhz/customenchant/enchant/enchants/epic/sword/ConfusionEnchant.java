package me.jwhz.customenchant.enchant.enchants.epic.sword;

import me.jwhz.customenchant.config.ConfigValue;
import me.jwhz.customenchant.enchant.Enchant;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@Enchant.EnchantInfo(
        name = "Confusion",
        itemTypes = Enchant.Tool.SWORD,
        rarity = Enchant.Rarity.EPIC,
        maxLevel = 3
)
public class ConfusionEnchant extends Enchant {

    @ConfigValue(path = "Enchant.Confusion.chance.level 1")
    public double level1 = 0.1;
    @ConfigValue(path = "Enchant.Confusion.chance.level 2")
    public double level2 = 1;
    @ConfigValue(path = "Enchant.Confusion.chance.level 3")
    public double level3 = 10;

    @EventHandler
    public void onEntityAttack(EntityDamageByEntityEvent e){

        if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {

            ItemStack item = ((Player) e.getDamager()).getItemInHand();

            if (item != null && core.enchantManager.getEnchants(item).contains(this)) {

                int level = core.enchantManager.getLevel(item, this);

                if (Math.random() * 100 < getChance(level))
                    ((Player) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, level * 40, level - 1));

            }

        }

    }

}
