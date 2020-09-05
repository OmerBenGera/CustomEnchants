package me.jwhz.customenchant.enchant.enchants.legendary.pickaxe;

import me.jwhz.customenchant.config.ConfigValue;
import me.jwhz.customenchant.enchant.Enchant;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@Enchant.EnchantInfo(
        name = "Obsidian Destroyer",
        itemTypes = Enchant.Tool.PICKAXE,
        rarity = Enchant.Rarity.LEGENDARY,
        maxLevel = 3
)
public class FasterObbyEnchant extends Enchant {

    @EventHandler
    public void onBlockDamage(BlockDamageEvent e){

        if(e.getBlock().getType() == Material.OBSIDIAN && e.getItemInHand() != null && core.enchantManager.getEnchants(e.getItemInHand()).contains(this)){

            int level = core.enchantManager.getLevel(e.getItemInHand(), this);

            e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, level * 80, level - 1));

        }

    }

}
