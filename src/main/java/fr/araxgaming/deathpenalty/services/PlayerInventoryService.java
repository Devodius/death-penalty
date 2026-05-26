package fr.araxgaming.deathpenalty.services;

import io.papermc.paper.persistence.PersistentDataContainerView;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import javax.annotation.Nullable;
import java.util.List;

public class PlayerInventoryService {

    public static int searchItemsForDataKey(final List<ItemStack> itemStacksList, final NamespacedKey dataKey) {
        for (int index = 0; index < itemStacksList.size(); index++) {
            if (PlayerInventoryService.hasDataFromKey(itemStacksList.get(index), dataKey)) {
                return index;
            }
        }
        return -1;
    }

    public static void removeOneFromList(final List<ItemStack> itemStacksList, final int index) {
        final ItemStack stack = itemStacksList.get(index);

        stack.setAmount(stack.getAmount() - 1);
    }

    @Nullable
    public static String getDataFromKey(final ItemStack itemStack, final NamespacedKey dataKey) {
        PersistentDataContainerView pdc = itemStack.getPersistentDataContainer();

        return pdc.get(dataKey, PersistentDataType.STRING);
    }

    public static boolean hasDataFromKey(final ItemStack itemStack, final NamespacedKey dataKey) {
        return getDataFromKey(itemStack, dataKey) != null;
    }

}
