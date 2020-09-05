package me.jwhz.customenchant.enchant.enchants.common.pickaxe;

import me.jwhz.customenchant.enchant.Enchant;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

@Enchant.EnchantInfo(
        name = "AutoSmelt",
        itemTypes = Enchant.Tool.PICKAXE,
        rarity = Enchant.Rarity.COMMON
)
public class AutoSmeltEnchant extends Enchant {

    private static final HashMap<Material, Material> AUTO_SMELT = new HashMap<>();

    static {

        AUTO_SMELT.put(Material.GOLD_ORE, Material.GOLD_INGOT);
        AUTO_SMELT.put(Material.STONE, Material.STONE);
        AUTO_SMELT.put(Material.IRON_ORE, Material.IRON_INGOT);

    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {

        ItemStack pickaxe = e.getPlayer().getItemInHand();

        if (!e.isCancelled() && pickaxe != null && core.enchantManager.getEnchants(pickaxe).contains(this)) {

            if(AUTO_SMELT.containsKey(e.getBlock().getType())){

                ItemStack toAdd = new ItemStack(AUTO_SMELT.get(e.getBlock().getType()), getAmount(pickaxe));

                e.setCancelled(true);

                e.getBlock().setType(Material.AIR);

                e.getPlayer().getWorld().dropItemNaturally(e.getBlock().getLocation().add(0.5,0.5,0.5), toAdd);

                e.getPlayer().giveExp(e.getExpToDrop());

                e.setCancelled(true);

            }

        }

    }


    private int getAmount(ItemStack item) {

        if (item == null || item.getType() == Material.AIR)
            return 1;

        if (!item.hasItemMeta())
            return 1;

        if (item.hasItemMeta() && !item.getItemMeta().hasEnchant(Enchantment.LOOT_BONUS_BLOCKS))
            return 1;

        int level = item.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS);

        return (int) (1 + (Math.random() * 2) + (Math.random() * level));

    }

}
