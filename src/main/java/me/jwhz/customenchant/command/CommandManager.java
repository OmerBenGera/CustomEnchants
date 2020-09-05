package me.jwhz.customenchant.command;

import me.jwhz.customenchant.command.commands.CustomEnchantCMD;
import me.jwhz.customenchant.config.ConfigHandler;
import me.jwhz.customenchant.config.ConfigValue;
import me.jwhz.customenchant.manager.Manager;
import org.bukkit.ChatColor;

public class CommandManager extends Manager<CommandBase> {

    @ConfigValue(path = "Command.no permission")
    public String noPermission = "&cYou do not have permission to use this command!";

    public CommandManager() {

        super("messages");

        add(new CustomEnchantCMD());

        ConfigHandler.setPresets(this);
        ConfigHandler.reload(this);

    }

    @Override
    public void add(Object obj) {

        CommandBase command = (CommandBase) obj;

        core.getCommand(command.getAnnotationInfo().command()).setExecutor(command);
        core.getCommand(command.getAnnotationInfo().command()).setPermission(command.getAnnotationInfo().permission());
        core.getCommand(command.getAnnotationInfo().command()).setPermissionMessage(ChatColor.translateAlternateColorCodes('&', noPermission));

        ConfigHandler.setPresets(command, getFile());
        ConfigHandler.reload(command, getFile());

        list.add(command);

    }
}
