package me.jwhz.customenchant.enchant.enchants.legendary.helmet;

import me.jwhz.customenchant.enchant.Enchant;
import me.jwhz.customenchant.enchant.PotionEnchant;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@Enchant.EnchantInfo(
        name = "Valor",
        itemTypes = Enchant.Tool.HELMET,
        rarity = Enchant.Rarity.LEGENDARY
)
public class ResistanceEnchant extends PotionEnchant {

    @Override
    public void onEnable(Player player, ItemStack item) {

        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 0));

    }

    @Override
    public void onDisable(Player player) {

        player.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);

    }

    @Override
    public void checkEnchant(Player player) {

        ItemStack helmet = player.getInventory().getArmorContents()[3];

        if (player.hasPotionEffect(PotionEffectType.DAMAGE_RESISTANCE) &&
                getActivePotion(player, PotionEffectType.DAMAGE_RESISTANCE).getDuration() >= 72000 &&
                (helmet == null || !core.enchantManager.getEnchants(helmet).contains(this)))
            onDisable(player);
        else if (helmet != null && core.enchantManager.getEnchants(helmet).contains(this) && !player.hasPotionEffect(PotionEffectType.DAMAGE_RESISTANCE))
            onEnable(player, helmet);

    }

}
