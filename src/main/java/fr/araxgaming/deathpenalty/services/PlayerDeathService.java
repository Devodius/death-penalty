package fr.araxgaming.deathpenalty.services;

import fr.araxgaming.deathpenalty.config.enums.InventoryOnDeath;
import fr.araxgaming.deathpenalty.recipes.totems.TotemOfDropInventoryRecipes;
import fr.araxgaming.deathpenalty.recipes.totems.TotemOfKeepInventoryRecipes;
import org.bukkit.Material;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class PlayerDeathService {

    public static void doInventoryDeath(final PlayerDeathEvent playerDeathEvent, final InventoryOnDeath inventoryOnDeath) {
        final List<ItemStack> drops = playerDeathEvent.getDrops();
        final List<ItemStack> keep = playerDeathEvent.getItemsToKeep();

        switch (inventoryOnDeath) {
            case DEFAULT -> {
                // Empty
            }
            case TOTEM -> totemInventory(drops, keep);
            case KEEP -> keepInventory(drops, keep);
            case CLEAR -> deleteDrops(drops);
        }
    }

    private static void totemInventory(final List<ItemStack> drops, final List<ItemStack> keep) {
        final ItemStack originalTotem = new ItemStack(Material.TOTEM_OF_UNDYING);
        final ItemStack totemKeep = TotemOfKeepInventoryRecipes.getItemCrafted();
        final ItemStack totemDrop = TotemOfDropInventoryRecipes.getItemCrafted();
        final List<ItemStack> validTotems = List.of(originalTotem, totemKeep, totemDrop);

        final int foundIndex = isItemInList(drops, validTotems);

        if (foundIndex == -1 || foundIndex >= drops.size()) {
            deleteDrops(drops);
            return;
        }

        final ItemStack itemStack = drops.get(foundIndex);

        if (PlayerInventoryService.isSameCustomItem(itemStack, totemKeep)) {
            removeOneFromList(drops, foundIndex);
            keepInventory(drops, keep);
        } else if (PlayerInventoryService.isSameCustomItem(itemStack, totemDrop)) {
            removeOneFromList(drops, foundIndex);
        }
    }

    private static void deleteDrops(final List<ItemStack> drops) {
        drops.clear();
    }

    private static void keepInventory(final List<ItemStack> drops, final List<ItemStack> keep) {
        keep.clear();
        keep.addAll(drops);
        deleteDrops(drops);
    }

    private static void removeOneFromList(final List<ItemStack> itemStacksList, final int index) {
        final ItemStack stack = itemStacksList.get(index);

        stack.setAmount(stack.getAmount() - 1);
    }

    private static int isItemInList(final List<ItemStack> itemStacksList, final List<ItemStack> toSearch) {
        for (final ItemStack search : toSearch) {
            for (int index = 0; index < itemStacksList.size(); index++) {
                if (PlayerInventoryService.isSameCustomItem(itemStacksList.get(index), search)) {
                    return index;
                }
            }
        }
        return -1;
    }

}
