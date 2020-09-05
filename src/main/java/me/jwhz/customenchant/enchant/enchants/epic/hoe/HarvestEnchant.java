package me.jwhz.customenchant.enchant.enchants.epic.hoe;

import me.jwhz.customenchant.enchant.Enchant;
import org.bukkit.CropState;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Crops;

@Enchant.EnchantInfo(
        name = "Harvest",
        itemTypes = Enchant.Tool.HOE,
        rarity = Enchant.Rarity.EPIC,
        keepChecking = false
)
public class HarvestEnchant extends Enchant {

    @EventHandler
    public void onCropHarvest(BlockBreakEvent e) {

        if (e.getPlayer().getItemInHand() != null && core.enchantManager.getEnchants(e.getPlayer().getItemInHand()).contains(this)) {

            Material type = e.getBlock().getType();

            if (type == Material.WHEAT || type == Material.CARROT || type == Material.POTATO || type == Material.CROPS && e.getPlayer().getInventory().firstEmpty() != -1) {

                if (e.getBlock().getData() == 7) {

                    for (ItemStack drop : e.getBlock().getDrops())
                        e.getPlayer().getInventory().addItem(drop);

                    e.getBlock().setType(Material.AIR);

                    e.setCancelled(true);

                }

            }

        }

    }

}
