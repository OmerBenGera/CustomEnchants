package me.jwhz.customenchant.gui.guis;

import me.jwhz.customenchant.itemnbtapi.NBTItem;
import me.jwhz.customenchant.ExpUtils;
import me.jwhz.customenchant.ItemUtils;
import me.jwhz.customenchant.config.ConfigHandler;
import me.jwhz.customenchant.config.ConfigValue;
import me.jwhz.customenchant.enchant.Enchant;
import me.jwhz.customenchant.gui.GUI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public class BuyEnchantBookGUI extends GUI {

    @ConfigValue(path = "gui.buy enchant books.size")
    public int size = 3;
    @ConfigValue(path = "gui.buy enchant books.name")
    public String name = "&aBuy Enchant Books";

    @ConfigValue(path = "gui.buy enchant books.rarity.common.cost")
    public int commonCost = 100;
    @ConfigValue(path = "gui.buy enchant books.rarity.common.item")
    public String commonItem = "item:BOOK display:&bCommon__Enchant lore:&r lore:&7Right__click__to lore:&7get__a__random__enchant.";
    @ConfigValue(path = "gui.buy enchant books.rarity.common.gui-item")
    public String commonGuiItem = "item:BOOK display:&bCommon__Enchant lore:&r lore:&7Costs:__&a100__xp";
    @ConfigValue(path = "gui.buy enchant books.rarity.common.slot")
    public int commonSlot = 11;

    @ConfigValue(path = "gui.buy enchant books.rarity.epic.cost")
    public int epicCost = 1000;
    @ConfigValue(path = "gui.buy enchant books.rarity.epic.item")
    public String epicItem = "item:BOOK display:&5Epic__Enchant lore:&r lore:&7Right__click__to lore:&7get__a__random__enchant.";
    @ConfigValue(path = "gui.buy enchant books.rarity.epic.gui-item")
    public String epicGuiItem = "item:BOOK display:&5Epic__Enchant lore:&r lore:&7Costs:__&a1000__xp";
    @ConfigValue(path = "gui.buy enchant books.rarity.epic.slot")
    public int epicSlot = 13;

    @ConfigValue(path = "gui.buy enchant books.rarity.legendary.cost")
    public int legendaryCost = 10000;
    @ConfigValue(path = "gui.buy enchant books.rarity.legendary.item")
    public String legendaryItem = "item:BOOK display:&eLegendary__Enchant lore:&r lore:&7Right__click__to lore:&7get__a__random__enchant.";
    @ConfigValue(path = "gui.buy enchant books.rarity.legendary.gui-item")
    public String legendaryGuiItem = "item:BOOK display:&eLegendary__Enchant lore:&r lore:&7Costs:__&a10000__xp";
    @ConfigValue(path = "gui.buy enchant books.rarity.legendary.slot")
    public int legendarySlot = 15;

    @ConfigValue(path = "gui.buy enchant books.messages.not enough exp")
    public String notEnoughXp = "&cYou do not have enough xp to buy this.";
    @ConfigValue(path = "gui.buy enchant books.messages.purchased enchant")
    public String purchasedBook = "&aYou have purchased an enchant book.";

    public BuyEnchantBookGUI(Player player){

        ConfigHandler.setPresets(this);
        ConfigHandler.reload(this);

        inventory = Bukkit.createInventory(null, 9 * size, name);

        addDefaultListening(player);

        setupGUI(player);

        player.openInventory(inventory);

    }

    @Override
    public void onClick(InventoryClickEvent e) {

        if(e.getCurrentItem() == null)
            return;

        String itemToGive = "";
        int cost = 0;
        Enchant.Rarity rarity = null;

        if(e.getSlot() == commonSlot){

            itemToGive = commonItem;
            cost = commonCost;
            rarity = Enchant.Rarity.COMMON;

        }

        if(e.getSlot() == epicSlot){

            itemToGive = epicItem;
            cost = epicCost;
            rarity = Enchant.Rarity.EPIC;


        }

        if(e.getSlot() == legendarySlot){

            itemToGive = legendaryItem;
            cost = legendaryCost;
            rarity = Enchant.Rarity.LEGENDARY;

        }

        if(rarity == null)
            return;

        if(((Player)e.getWhoClicked()).getTotalExperience() < cost){

            e.getWhoClicked().sendMessage(notEnoughXp);
            return;

        }

        ExpUtils.subtractExp((Player) e.getWhoClicked(), cost);

        NBTItem tags = new NBTItem(ItemUtils.readString(itemToGive));

        tags.setBoolean("isEnchantBook", true);
        tags.setString("enchant-type", rarity.name());

        e.getWhoClicked().getInventory().addItem(tags.getItem());

        e.getWhoClicked().sendMessage(purchasedBook);

    }

    @Override
    public void setupGUI(Player player) {

        inventory.setItem(commonSlot, ItemUtils.readString(commonGuiItem));
        inventory.setItem(epicSlot, ItemUtils.readString(epicGuiItem));
        inventory.setItem(legendarySlot, ItemUtils.readString(legendaryGuiItem));


    }

}
