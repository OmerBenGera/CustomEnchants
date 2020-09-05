package me.jwhz.customenchant.enchant.enchants.legendary;

import me.jwhz.customenchant.enchant.Enchant;
import me.jwhz.customenchant.enchant.PotionEnchant;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@Enchant.EnchantInfo(
        name = "Beast",
        itemTypes = {Enchant.Tool.HELMET, Enchant.Tool.CHESTPLATE, Enchant.Tool.LEGGINGS, Enchant.Tool.BOOTS},
        rarity = Enchant.Rarity.LEGENDARY
)
public class StrengthEnchant extends PotionEnchant {

    @Override
    public void onEnable(Player player, ItemStack item) {

        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 1));

    }

    @Override
    public void onDisable(Player player) {

        player.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);

    }

    @Override
    public void checkEnchant(Player player) {

        boolean allEnchanted = true;

        for(ItemStack item : player.getInventory().getArmorContents())
            if(item == null || !core.enchantManager.getEnchants(item).contains(this))
                allEnchanted = false;

        if(allEnchanted)
            onEnable(player, null);
        else
            onDisable(player);

    }
}
