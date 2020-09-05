package me.jwhz.customenchant.enchant.enchants.legendary.axe;

import me.jwhz.customenchant.config.ConfigValue;
import me.jwhz.customenchant.enchant.Enchant;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.HashMap;
import java.util.UUID;

@Enchant.EnchantInfo(
        name = "Rage",
        itemTypes = {Enchant.Tool.AXE},
        rarity = Enchant.Rarity.LEGENDARY,
        maxLevel = 3
)
public class ComboEnchant extends Enchant {

    public HashMap<UUID, Double> rage = new HashMap<>();

    @ConfigValue(path = "Enchant.Rage.rage boost.level 1")
    public double level1 = 0.15;
    @ConfigValue(path = "Enchant.Rage.rage boost.level 2")
    public double level2 = 0.25;
    @ConfigValue(path = "Enchant.Rage.rage boost.level 3")
    public double level3 = 0.5;

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent e) {

        rage.remove(e.getEntity().getUniqueId());

        if (e.getDamager() instanceof Player && e.getEntity() instanceof Player && ((Player) e.getDamager()).getItemInHand() != null &&
                core.enchantManager.getEnchants(((Player) e.getDamager()).getItemInHand()).contains(this)) {

            double rageLevel = 1;

            if (rage.containsKey(e.getDamager().getUniqueId())) {

                rageLevel = rage.get(e.getDamager().getUniqueId());

                rageLevel += getChance(core.enchantManager.getLevel(((Player) e.getDamager()).getItemInHand(), this));

                rage.remove(e.getDamager().getUniqueId());

            }

            rage.put(e.getDamager().getUniqueId(), rageLevel);

            e.setDamage(e.getDamage() * rageLevel);

        }

    }

}
