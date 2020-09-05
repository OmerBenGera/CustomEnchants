package me.jwhz.customenchant.enchant;

import me.jwhz.customenchant.itemnbtapi.NBTItem;
import me.jwhz.customenchant.ItemUtils;
import me.jwhz.customenchant.manager.ManagerObject;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public abstract class Enchant extends ManagerObject<String> implements Listener {

    private EnchantInfo enchantInfo;
    private String path;

    public Enchant() {

        enchantInfo = getClass().getAnnotation(EnchantInfo.class);

        path = "Enchant." + enchantInfo.name();

        if (!core.enchantManager.yamlConfiguration.isSet(path)) {

            core.enchantManager.yamlConfiguration.set(path + ".name", enchantInfo.name());

            core.enchantManager.save();

        }
    }

    public PotionEffect getActivePotion(Player player, PotionEffectType type){

        for(PotionEffect potionEffect : player.getActivePotionEffects())
            if(potionEffect.getType().getName().equalsIgnoreCase(type.getName()))
                return potionEffect;

        return null;

    }

    public ItemStack getItem(){

        if(!core.enchantManager.yamlConfiguration.isSet(path + ".item")) {

            ItemStack item = new ItemStack(Material.BOOK);

            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(ChatColor.GREEN + getName() + " Lvl %level%");

            String worksOn = "";

            for(Tool tool : getAcceptedItems())
                worksOn += tool.name().toLowerCase() + ", ";

            worksOn = worksOn.substring(0, worksOn.length() - 2);

            meta.setLore(Arrays.asList(
                    "",
                    ChatColor.GRAY + "Rarity: " + getRarity().name().toLowerCase(),
                    ChatColor.GRAY + "Works on: " + worksOn
            ));

            item.setItemMeta(meta);

            core.enchantManager.yamlConfiguration.set(path + ".item", ItemUtils.readItemStack(item));

            core.enchantManager.save();

        }

        int level = getRandomLevel();

        ItemStack item = ItemUtils.readString(core.enchantManager.yamlConfiguration.getString(path + ".item").replace("%level%", level + ""));

        NBTItem tags = new NBTItem(item);

        tags.setBoolean("isEnchant", true);
        tags.setString("enchant", getName());
        tags.setInteger("enchant-level", level);

        return tags.getItem();

    }

    public double getChance(int level) {

        try {
            return (double) getClass().getField("level" + level).get(this);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        return 0;

    }

    private int getRandomLevel(){

        return new Random().nextInt(getMaxLevel()) + 1;

    }

    @Override
    public String getIdentifier() {

        return getName();

    }

    public String getName() {

        return core.enchantManager.yamlConfiguration.getString(path + ".name");

    }

    public ArrayList<Tool> getAcceptedItems(){

        return new ArrayList<Tool>(Arrays.asList(enchantInfo.itemTypes()));

    }

    public Rarity getRarity(){

        return enchantInfo.rarity();

    }

    public int getMaxLevel(){

        return enchantInfo.maxLevel();

    }

    public boolean keepChecking(){

        return enchantInfo.keepChecking();

    }

    @Retention(RetentionPolicy.RUNTIME)
    public @interface EnchantInfo {

        String name();

        Tool[] itemTypes();

        Rarity rarity();

        int maxLevel() default 1;

        boolean keepChecking() default false;

    }

    public enum Tool {

        HELMET,
        CHESTPLATE,
        LEGGINGS,
        BOOTS,
        PICKAXE,
        HOE,
        AXE,
        SHOVEL,
        SWORD,
        BOW;

        public static Tool getType(ItemStack item){

            String name = item.getType().name();

            for(Tool tool : Tool.values()){

                if(name.contains(tool.name())){

                    if(tool.name().equalsIgnoreCase("AXE") && name.contains("PICKAXE"))
                        return Tool.PICKAXE;

                    return tool;

                }

            }

            return null;

        }

    }

    public enum Rarity {

        LEGENDARY,
        EPIC,
        COMMON

    }

}
