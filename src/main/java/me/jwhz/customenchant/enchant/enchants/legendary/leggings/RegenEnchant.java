package me.jwhz.customenchant.enchant.enchants.legendary.leggings;

import me.jwhz.customenchant.enchant.Enchant;
import me.jwhz.customenchant.enchant.PotionEnchant;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@Enchant.EnchantInfo(
        name = "Replenish",
        itemTypes = Enchant.Tool.LEGGINGS,
        rarity = Enchant.Rarity.LEGENDARY,
        maxLevel = 2
)
public class RegenEnchant extends PotionEnchant {

    @Override
    public void onEnable(Player player, ItemStack item) {

        int level = core.enchantManager.getLevel(item, this);

        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, Integer.MAX_VALUE, level - 1));

    }

    @Override
    public void onDisable(Player player) {

        player.removePotionEffect(PotionEffectType.REGENERATION);

    }

    @Override
    public void checkEnchant(Player player) {

        ItemStack helmet = player.getInventory().getArmorContents()[1];

        if (player.hasPotionEffect(PotionEffectType.REGENERATION) &&
                getActivePotion(player, PotionEffectType.REGENERATION).getDuration() >= 72000 &&
                (helmet == null || !core.enchantManager.getEnchants(helmet).contains(this)))
            onDisable(player);
        else if (helmet != null && core.enchantManager.getEnchants(helmet).contains(this) && !player.hasPotionEffect(PotionEffectType.REGENERATION))
            onEnable(player, helmet);

    }

}
