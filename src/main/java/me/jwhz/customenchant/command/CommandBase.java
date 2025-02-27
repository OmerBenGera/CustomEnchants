package me.jwhz.customenchant.command;

import me.jwhz.customenchant.manager.ManagerObject;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public abstract class CommandBase extends ManagerObject<String> implements CommandExecutor {

    private Info annotationInfo = getClass().getAnnotation(Info.class);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (annotationInfo.command().equalsIgnoreCase(command.getName())) {

            if (annotationInfo.onlyPlayers() && !(sender instanceof Player)) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cOnly players can use this command!"));
                return true;
            }

            onCommand(sender, args);

        }

        return true;

    }

    @Override
    public String getIdentifier() {

        return annotationInfo.command();

    }

    public Info getAnnotationInfo() {

        return annotationInfo;

    }

    public abstract void onCommand(CommandSender sender, String[] args);

    @Retention(RetentionPolicy.RUNTIME)
    public @interface Info {

        String command();

        String permission() default "customenchants.user";

        boolean onlyPlayers() default true;

    }

}
