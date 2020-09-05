package me.jwhz.customenchant.enchant.enchants.common.helmet;

import me.jwhz.customenchant.enchant.Enchant;
import me.jwhz.customenchant.enchant.PotionEnchant;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@Enchant.EnchantInfo(
        name = "Fish",
        itemTypes = Enchant.Tool.HELMET,
        rarity = Enchant.Rarity.COMMON
)
public class WaterBreathingEnchant extends PotionEnchant {

    @Override
    public void onEnable(Player player, ItemStack item) {

        player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, Integer.MAX_VALUE, 0));

    }

    @Override
    public void onDisable(Player player) {

        player.removePotionEffect(PotionEffectType.WATER_BREATHING);

    }

    @Override
    public void checkEnchant(Player player) {

        ItemStack helmet = player.getInventory().getArmorContents()[3];

        if (player.hasPotionEffect(PotionEffectType.WATER_BREATHING) &&
                getActivePotion(player, PotionEffectType.WATER_BREATHING).getDuration() >= 72000 &&
                (helmet == null || !core.enchantManager.getEnchants(helmet).contains(this)))
            onDisable(player);
        else if (helmet != null && core.enchantManager.getEnchants(helmet).contains(this) && !player.hasPotionEffect(PotionEffectType.WATER_BREATHING))
            onEnable(player, helmet);

    }

}
