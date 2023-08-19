package fr.araxgaming.deathpenalty.services;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;

public class PlayerInventoryService {

    public static @Nullable ItemStack isMaterialInInventory(final Inventory playerInventory, final Set<ItemStack> toSearch) {
        for (final ItemStack search : toSearch) {
            if (playerInventory.contains(search)) {
                return search;
            }
        }
        return null;
    }

    public static @Nullable ItemStack isMaterialInHand(final PlayerInventory playerInventory, final Set<ItemStack> toSearch) {
        for (final ItemStack search : toSearch) {
            if (isSameCustomItem(playerInventory.getItemInMainHand(), search)) {
                return search;
            }
            if (isSameCustomItem(playerInventory.getItemInOffHand(), search)) {
                return search;
            }
        }
        return null;
    }

    public static int isItemInList(final List<ItemStack> itemStacksList, final ItemStack toSearch) {
        for (int index = 0; index < itemStacksList.size(); index++) {
            if (PlayerInventoryService.isSameCustomItem(itemStacksList.get(index), toSearch)) {
                return index;
            }
        }
        return -1;
    }

    public static int isItemInList(final List<ItemStack> itemStacksList, final List<ItemStack> toSearch) {
        for (final ItemStack search : toSearch) {
            final int index = isItemInList(itemStacksList, search);

            if (index != -1) {
                return index;
            }
        }
        return -1;
    }

    public static void removeOneFromList(final List<ItemStack> itemStacksList, final int index) {
        final ItemStack stack = itemStacksList.get(index);

        stack.setAmount(stack.getAmount() - 1);
    }

    public static boolean isSameCustomItem(final ItemStack itemStack1, final ItemStack itemStack2) {
        final ItemMeta itemMeta1 = itemStack1.getItemMeta();
        final ItemMeta itemMeta2 = itemStack2.getItemMeta();

        if (itemStack1.getType() != itemStack2.getType()) {
            return false;
        }

        if (itemMeta1.hasCustomModelData() != itemMeta2.hasCustomModelData()) {
            return false;
        } else if (!itemMeta1.hasCustomModelData()) {
            return true;
        }

        if (itemMeta1.getCustomModelData() != itemMeta2.getCustomModelData()) {
            return false;
        }

        return true;
    }

}
