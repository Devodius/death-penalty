package fr.araxgaming.deathpenalty.services;

import org.bukkit.inventory.ItemStack;

import java.util.List;

public class PlayerDeathService {

    public static void deleteDrops(final List<ItemStack> drops) {
        drops.clear();
    }

    public static void keepInventory(final List<ItemStack> drops, final List<ItemStack> keep) {
        keep.clear();
        keep.addAll(drops);
        deleteDrops(drops);
    }
}
