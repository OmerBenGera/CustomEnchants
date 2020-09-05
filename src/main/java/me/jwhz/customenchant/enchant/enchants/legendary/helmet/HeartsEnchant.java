package me.jwhz.customenchant.enchant.enchants.legendary.helmet;

import me.jwhz.customenchant.enchant.Enchant;
import me.jwhz.customenchant.enchant.PotionEnchant;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Enchant.EnchantInfo(
        name = "Hearts",
        itemTypes = Enchant.Tool.HELMET,
        rarity = Enchant.Rarity.LEGENDARY,
        maxLevel = 3
)
public class HeartsEnchant extends PotionEnchant {

    @Override
    public void onEnable(Player player, ItemStack item) {

        int level = core.enchantManager.getLevel(item, this);

        player.setMaxHealth(20 + (level * 4));

    }

    @Override
    public void onDisable(Player player) {

        player.setMaxHealth(20);

    }

    @Override
    public void checkEnchant(Player player) {

        ItemStack helmet = player.getInventory().getArmorContents()[3];

        if (player.getMaxHealth() > 20 && (helmet == null || !core.enchantManager.getEnchants(helmet).contains(this)))
            onDisable(player);
        else if (core.enchantManager.getEnchants(helmet).contains(this) && player.getMaxHealth() == 20)
            onEnable(player, helmet);


    }

}
