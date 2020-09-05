package me.jwhz.customenchant.enchant.enchants.epic;

import me.jwhz.customenchant.enchant.Enchant;
import me.jwhz.customenchant.enchant.PotionEnchant;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@Enchant.EnchantInfo(
        name = "Haste",
        itemTypes = {Enchant.Tool.AXE, Enchant.Tool.PICKAXE, Enchant.Tool.SHOVEL, Enchant.Tool.SWORD},
        rarity = Enchant.Rarity.EPIC,
        maxLevel = 3
)
public class HasteEnchant extends PotionEnchant {

    @Override
    public void onEnable(Player player, ItemStack item) {

        player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, Integer.MAX_VALUE, core.enchantManager.getLevel(item, this) - 1));

    }

    @Override
    public void onDisable(Player player) {

        player.removePotionEffect(PotionEffectType.FAST_DIGGING);

    }

    @Override
    public void checkEnchant(Player player) {

        ItemStack item = player.getItemInHand();

        if (player.hasPotionEffect(PotionEffectType.FAST_DIGGING) &&
                getActivePotion(player, PotionEffectType.FAST_DIGGING).getDuration() >= 72000 &&
                (item == null || !core.enchantManager.getEnchants(item).contains(this)))
            onDisable(player);
        else if (item != null && core.enchantManager.getEnchants(item).contains(this) && !player.hasPotionEffect(PotionEffectType.FAST_DIGGING))
            onEnable(player, item);

    }

}
