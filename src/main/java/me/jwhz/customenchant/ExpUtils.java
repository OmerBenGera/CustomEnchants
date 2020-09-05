package me.jwhz.customenchant;

import org.bukkit.entity.Player;

public class ExpUtils {

    public static void subtractExp(Player p, int amount){

        int currentXp = p.getTotalExperience();

        p.setTotalExperience(0);
        p.setExp(0);
        p.setLevel(0);

        p.giveExp(Math.max(0, currentXp - amount));

    }

}
