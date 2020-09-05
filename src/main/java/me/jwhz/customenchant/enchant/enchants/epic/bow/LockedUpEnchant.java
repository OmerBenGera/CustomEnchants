package me.jwhz.customenchant.enchant.enchants.epic.bow;

import me.jwhz.customenchant.config.ConfigValue;
import me.jwhz.customenchant.enchant.Enchant;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@Enchant.EnchantInfo(
        name = "Locked Up",
        itemTypes = Enchant.Tool.BOW,
        rarity = Enchant.Rarity.EPIC,
        maxLevel = 3
)
public class LockedUpEnchant extends Enchant {

    @ConfigValue(path = "Enchant.Lock Up.chance.level 1")
    public double level1 = 0.1;
    @ConfigValue(path = "Enchant.Lock Up.chance.level 2")
    public double level2 = 1;
    @ConfigValue(path = "Enchant.Lock Up.chance.level 3")
    public double level3 = 10;

    @EventHandler
    public void onEntityAttack(EntityDamageByEntityEvent e){

        if (e.getEntity() instanceof Player && e.getDamager() instanceof Projectile && ((Projectile) e.getDamager()).getShooter() instanceof Player) {

            ItemStack bow = ((Player) ((Projectile) e.getDamager()).getShooter()).getInventory().getItemInHand();

            if (bow != null && bow.getType() == Material.BOW && core.enchantManager.getEnchants(bow).contains(this)) {

                int level = core.enchantManager.getLevel(bow, this);

                if (Math.random() * 100 < getChance(level))
                    ((Player) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, level * 40, 0));

            }

        }

    }

}