package me.jwhz.customenchant.enchant;

import me.jwhz.customenchant.itemnbtapi.NBTItem;
import me.jwhz.customenchant.config.ConfigFile;
import me.jwhz.customenchant.config.ConfigHandler;
import me.jwhz.customenchant.config.ConfigValue;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Random;

public class EnchantsListener extends ConfigFile implements Listener {

    @ConfigValue(path = "messages.opened enchant book")
    public String openedEnchantBook = "&aYou have opened a %rarity% enchant book and got %book%!";
    @ConfigValue(path = "messages.enchanted item")
    public String enchantedItem = "&aYou have enchanted your item with %enchant%!";
    @ConfigValue(path = "messages.cant enchant this")
    public String cantEnchant = "&cThis book can't enchant this item.";
    @ConfigValue(path = "messages.already enchanted")
    public String alreadyEnchanted = "&cThis item is already enchanted with a similar enchantment.";

    private EnchantManager enchants;
    private Random random;

    public EnchantsListener(EnchantManager enchants) {

        super("config");

        this.enchants = enchants;
        random = new Random();

        ConfigHandler.setPresets(this);
        ConfigHandler.reload(this);

    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {

        if(e.getSlot() >= 100 && e.getSlot() <= 103)
            return;

        if (e.getCursor() != null && e.getCursor().getType() != Material.AIR && e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR)

            if (e.getWhoClicked() instanceof Player && e.getClickedInventory().equals(e.getWhoClicked().getInventory())) {

                NBTItem item;

                if ((item = new NBTItem(e.getCursor())).hasKey("isEnchant")) {

                    Enchant enchant = enchants.getEnchant(item.getString("enchant"));

                    if (enchant.getAcceptedItems().contains(Enchant.Tool.getType(e.getCurrentItem()))) {

                        if (!enchants.getEnchants(e.getCurrentItem()).contains(enchant)) {

                            NBTItem tags = new NBTItem(e.getCurrentItem());

                            tags.setInteger(enchant.getName(), item.getInteger("enchant-level"));

                            if (e.getCursor().getAmount() == 1)
                                e.setCursor(null);
                            else
                                e.getCursor().setAmount(e.getCursor().getAmount() - 1);

                            ItemStack itemStack = tags.getItem();

                            ItemMeta meta = itemStack.getItemMeta();

                            ArrayList<String> lore = new ArrayList<>();
                            if (meta.getLore() != null)
                                lore.addAll(meta.getLore());
                            else
                                lore.add("");

                            lore.add(ChatColor.GRAY + "Lvl " + item.getInteger("enchant-level") + ChatColor.GREEN + " " + enchant.getName());

                            meta.setLore(lore);

                            itemStack.setItemMeta(meta);

                            e.getClickedInventory().setItem(e.getSlot(), itemStack);

                            e.getWhoClicked().sendMessage(enchantedItem.replace("%enchant%", enchant.getName()));

                            e.setCancelled(true);

                        } else
                            e.getWhoClicked().sendMessage(alreadyEnchanted);

                    } else
                        e.getWhoClicked().sendMessage(cantEnchant);

                }

            }

    }

    @EventHandler
    public void onItemInteract(PlayerInteractEvent e) {

        if (e.getItem() != null && new NBTItem(e.getItem()).hasKey("isEnchantBook")) {

            Enchant.Rarity rarity = Enchant.Rarity.valueOf(new NBTItem(e.getItem()).getString("enchant-type"));

            if (e.getItem().getAmount() == 1)
                e.getPlayer().setItemInHand(null);
            else
                e.getItem().setAmount(e.getItem().getAmount() - 1);

            ArrayList<Enchant> toChoseFrom = new ArrayList<Enchant>();

            enchants.getList().forEach(i -> {

                if (i.getRarity() == rarity)
                    toChoseFrom.add(i);

            });

            Enchant enchant = toChoseFrom.get(random.nextInt(toChoseFrom.size()));

            e.getPlayer().getInventory().addItem(enchant.getItem());

            e.getPlayer().sendMessage(openedEnchantBook.replace("%rarity%", enchant.getRarity().name().toLowerCase()).replace("%book%", enchant.getName()));

        }

    }

}
