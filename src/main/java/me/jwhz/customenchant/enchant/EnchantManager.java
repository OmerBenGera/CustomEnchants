package me.jwhz.customenchant.enchant;

import me.jwhz.customenchant.itemnbtapi.NBTItem;
import me.jwhz.customenchant.config.ConfigHandler;
import me.jwhz.customenchant.enchant.enchants.common.helmet.WaterBreathingEnchant;
import me.jwhz.customenchant.enchant.enchants.common.helmet.FireResistanceEnchant;
import me.jwhz.customenchant.enchant.enchants.common.helmet.NightVisionEnchant;
import me.jwhz.customenchant.enchant.enchants.common.helmet.NoBlindnessEnchant;
import me.jwhz.customenchant.enchant.enchants.common.pickaxe.AutoSmeltEnchant;
import me.jwhz.customenchant.enchant.enchants.epic.boots.HungerEnchant;
import me.jwhz.customenchant.enchant.enchants.epic.bow.BowLightningEnchant;
import me.jwhz.customenchant.enchant.enchants.epic.bow.LockedUpEnchant;
import me.jwhz.customenchant.enchant.enchants.epic.chestplate.LootExploderEnchant;
import me.jwhz.customenchant.enchant.enchants.epic.chestplate.SlownessEnchant;
import me.jwhz.customenchant.enchant.enchants.epic.chestplate.WeaknessEnchant;
import me.jwhz.customenchant.enchant.enchants.epic.hoe.HarvestEnchant;
import me.jwhz.customenchant.enchant.enchants.epic.leggings.BlindnessEnchant;
import me.jwhz.customenchant.enchant.enchants.epic.leggings.MiningFatigueEnchant;
import me.jwhz.customenchant.enchant.enchants.epic.leggings.NauseaEnchant;
import me.jwhz.customenchant.enchant.enchants.epic.sword.ConfusionEnchant;
import me.jwhz.customenchant.enchant.enchants.epic.sword.InstaEnchant;
import me.jwhz.customenchant.enchant.enchants.epic.sword.LightningEnchant;
import me.jwhz.customenchant.enchant.enchants.epic.sword.LightsOutEnchant;
import me.jwhz.customenchant.enchant.enchants.legendary.StrengthEnchant;
import me.jwhz.customenchant.enchant.enchants.legendary.axe.ComboEnchant;
import me.jwhz.customenchant.enchant.enchants.legendary.boots.JumpEnchant;
import me.jwhz.customenchant.enchant.enchants.legendary.boots.SpeedEnchant;
import me.jwhz.customenchant.enchant.enchants.legendary.chestplate.HealUpEnchant;
import me.jwhz.customenchant.enchant.enchants.legendary.helmet.HeartsEnchant;
import me.jwhz.customenchant.enchant.enchants.legendary.helmet.ResistanceEnchant;
import me.jwhz.customenchant.enchant.enchants.legendary.leggings.RegenEnchant;
import me.jwhz.customenchant.enchant.enchants.legendary.pickaxe.FasterObbyEnchant;
import me.jwhz.customenchant.enchant.enchants.legendary.sword.LifeEnchant;
import me.jwhz.customenchant.enchant.enchants.legendary.sword.MoreXPEnchant;
import me.jwhz.customenchant.enchant.enchants.legendary.sword.WitherEnchant;
import me.jwhz.customenchant.manager.Manager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class EnchantManager extends Manager<Enchant> {

    public EnchantManager() {

        super("enchants");

        Bukkit.getPluginManager().registerEvents(new EnchantsListener(this), core);

    }

    public void loadEnchants(){

        /**
         * @Common
         */

        add(new FireResistanceEnchant()); //
        add(new NightVisionEnchant()); //
        add(new NoBlindnessEnchant()); //
        add(new AutoSmeltEnchant()); //
        add(new WaterBreathingEnchant()); //

        /**
         * @Epic
         */

        add(new HungerEnchant()); //
        add(new BowLightningEnchant()); //
        add(new LockedUpEnchant()); //
        add(new LootExploderEnchant()); //
        add(new SlownessEnchant()); //
        add(new WeaknessEnchant()); //
        add(new HarvestEnchant());
        add(new BlindnessEnchant()); //
        add(new MiningFatigueEnchant()); //
        add(new NauseaEnchant()); //
        add(new ConfusionEnchant()); //
        add(new InstaEnchant()); //
        add(new LightningEnchant()); //
        add(new LightsOutEnchant()); //

        /**
         * @Legendary
         */

        add(new ResistanceEnchant()); //
        add(new ComboEnchant()); //
        add(new JumpEnchant()); //
        add(new SpeedEnchant()); //
        add(new HealUpEnchant()); //
        add(new HeartsEnchant()); //
        add(new RegenEnchant()); //
        add(new FasterObbyEnchant()); //
        add(new LifeEnchant()); //
        add(new MoreXPEnchant()); //
        add(new WitherEnchant()); //
        add(new StrengthEnchant()); //

        new BukkitRunnable(){

            @Override
            public void run() {

                for(Player player : Bukkit.getOnlinePlayers())
                    for(Enchant enchant : list)
                        if (enchant instanceof PotionEnchant)
                            ((PotionEnchant) enchant).checkEnchant(player);

            }

        }.runTaskTimer(core, 1, 2);

    }

    public Enchant getEnchant(String name){

        for(Enchant enchant : getList())
            if(enchant.getName().equalsIgnoreCase(name))
                return enchant;

        return null;

    }

    public int getLevel(ItemStack item, Enchant enchant) {

        assert item != null || enchant != null : 0;

        NBTItem tags = new NBTItem(item);

        if(tags.hasKey(enchant.getName()))
            return tags.getInteger(enchant.getName());

        return 0;

    }

    public ArrayList<Enchant> getEnchants(ItemStack item) {

        ArrayList<Enchant> enchants = new ArrayList<Enchant>();

        if(item == null || item.getType() == Material.AIR)
            return enchants;

        NBTItem tags = new NBTItem(item);

        for (Enchant enchant : getList())
            if (tags.hasKey(enchant.getName()))
                enchants.add(enchant);

        return enchants;

    }

    @Override
    public void add(Object obj) {

        Bukkit.getPluginManager().registerEvents((Listener) obj, core);

        ConfigHandler.setPresets(obj, getFile());
        ConfigHandler.reload(obj, getFile());

        list.add((Enchant)obj);

    }
}
