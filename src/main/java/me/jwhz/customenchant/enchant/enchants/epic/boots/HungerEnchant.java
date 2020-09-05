package me.jwhz.customenchant.enchant.enchants.epic.boots;

import me.jwhz.customenchant.enchant.Enchant;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.inventory.ItemStack;

@Enchant.EnchantInfo(
        name = "Feast",
        itemTypes = Enchant.Tool.BOOTS,
        rarity = Enchant.Rarity.EPIC
)
public class HungerEnchant extends Enchant {

    @EventHandler
    public void onPlayerFoodChange(FoodLevelChangeEvent e){

        if(e.getEntity() instanceof Player){

            Player player = (Player) e.getEntity();

            ItemStack boots = player.getInventory().getArmorContents()[3];

            if(boots != null && core.enchantManager.getEnchants(boots).contains(this))
                e.setFoodLevel(20);

        }

    }

}
