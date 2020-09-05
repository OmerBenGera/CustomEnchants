package me.jwhz.customenchant.enchant;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class PotionEnchant extends Enchant {

    @Override
    public boolean keepChecking() {

        return true;

    }

    public abstract void onEnable(Player player, ItemStack item);

    public abstract void onDisable(Player player);

    public abstract void checkEnchant(Player player);

}
