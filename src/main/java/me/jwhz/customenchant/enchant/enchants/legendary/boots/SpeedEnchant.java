package me.jwhz.customenchant.enchant.enchants.legendary.boots;

import me.jwhz.customenchant.enchant.Enchant;
import me.jwhz.customenchant.enchant.PotionEnchant;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@Enchant.EnchantInfo(
        name = "Scoot",
        itemTypes = Enchant.Tool.BOOTS,
        rarity = Enchant.Rarity.LEGENDARY,
        maxLevel = 3
)
public class SpeedEnchant extends PotionEnchant {

    @Override
    public void onEnable(Player player, ItemStack item) {

        int level = core.enchantManager.getLevel(item, this);

        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, level - 1));

    }

    @Override
    public void onDisable(Player player) {

        player.removePotionEffect(PotionEffectType.SPEED);

    }

    @Override
    public void checkEnchant(Player player) {

        ItemStack boots = player.getInventory().getArmorContents()[0];

        if (player.hasPotionEffect(PotionEffectType.SPEED) &&
                getActivePotion(player, PotionEffectType.SPEED).getDuration() >= 72000 &&
                (boots == null || !core.enchantManager.getEnchants(boots).contains(this)))
            onDisable(player);
        else if (boots != null && core.enchantManager.getEnchants(boots).contains(this) && !player.hasPotionEffect(PotionEffectType.SPEED))
            onEnable(player, boots);

    }

}
