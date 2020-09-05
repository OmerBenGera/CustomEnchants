package me.jwhz.customenchant.enchant.enchants.common.helmet;

import me.jwhz.customenchant.enchant.Enchant;
import me.jwhz.customenchant.enchant.PotionEnchant;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

@Enchant.EnchantInfo(
        name = "AntiFog",
        itemTypes = Enchant.Tool.HELMET,
        rarity = Enchant.Rarity.COMMON
)
public class NoBlindnessEnchant extends PotionEnchant {

    @Override
    public void onEnable(Player player, ItemStack item) {

    }



    @Override
    public void onDisable(Player player) {

        player.removePotionEffect(PotionEffectType.BLINDNESS);

    }

    @Override
    public void checkEnchant(Player player) {

        ItemStack helmet = player.getInventory().getArmorContents()[3];

        if ((helmet != null && core.enchantManager.getEnchants(helmet).contains(this)) &&
                player.hasPotionEffect(PotionEffectType.BLINDNESS))
            onDisable(player);

    }

}
