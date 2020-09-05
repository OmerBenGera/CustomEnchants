package me.jwhz.customenchant.command.commands;

import me.jwhz.customenchant.command.CommandBase;
import me.jwhz.customenchant.gui.guis.BuyEnchantBookGUI;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandBase.Info(
        command = "customenchants"
)
public class CustomEnchantCMD extends CommandBase {

    @Override
    public void onCommand(CommandSender sender, String[] args) {

        if (sender instanceof Player)
            new BuyEnchantBookGUI((Player) sender);

    }

}
