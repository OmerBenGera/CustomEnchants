package me.jwhz.customenchant;

import me.jwhz.customenchant.command.CommandManager;
import me.jwhz.customenchant.enchant.EnchantManager;
import org.bukkit.plugin.java.JavaPlugin;

public class CustomEnchants extends JavaPlugin {

    private static CustomEnchants instance;

    public CommandManager commandManager;
    public EnchantManager enchantManager;

    @Override
    public void onEnable() {

        instance = this;

        enchantManager = new EnchantManager();

        enchantManager.loadEnchants();

        commandManager = new CommandManager();


    }

    public static CustomEnchants getInstance() {

        return instance;

    }
}
