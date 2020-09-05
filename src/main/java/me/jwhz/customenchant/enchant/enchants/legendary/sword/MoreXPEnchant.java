package me.jwhz.customenchant.enchant.enchants.legendary.sword;

import me.jwhz.customenchant.config.ConfigValue;
import me.jwhz.customenchant.enchant.Enchant;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;

@Enchant.EnchantInfo(
        name = "Grind",
        itemTypes = Enchant.Tool.SWORD,
        rarity = Enchant.Rarity.LEGENDARY,
        maxLevel = 5
)
public class MoreXPEnchant extends Enchant {

    @ConfigValue(path = "Enchant.Grind.xp multiplier.level 1")
    public double level1 = 1.15;
    @ConfigValue(path = "Enchant.Grind.xp multiplier.level 2")
    public double level2 = 1.35;
    @ConfigValue(path = "Enchant.Grind.xp multiplier.level 3")
    public double level3 = 1.5;
    @ConfigValue(path = "Enchant.Grind.xp multiplier.level 4")
    public double level4 = 1.65;
    @ConfigValue(path = "Enchant.Grind.xp multiplier.level 5")
    public double level5 = 1.8;

    @EventHandler
    public void onEntityDeath(EntityDeathEvent e){

        if(e.getEntity().getKiller() != null){

            Player p = e.getEntity().getKiller();

            if(p.getItemInHand() != null && core.enchantManager.getEnchants(p.getItemInHand()).contains(this))
                e.setDroppedExp((int) (e.getDroppedExp() * getChance(core.enchantManager.getLevel(p.getItemInHand(), this))));

        }

    }

}
