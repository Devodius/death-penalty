package fr.araxgaming.deathpenalty.services;

import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class PlayerDeathService {

    public static void deleteDrops(final PlayerDeathEvent playerDeathEvent) {
        final List<ItemStack> drops = playerDeathEvent.getDrops();

        drops.clear();
    }

    public static void keepInventory(final PlayerDeathEvent playerDeathEvent) {
        final List<ItemStack> drops = playerDeathEvent.getDrops();
        final List<ItemStack> keep = playerDeathEvent.getItemsToKeep();

        keep.clear();

        keep.addAll(drops);

        deleteDrops(playerDeathEvent);
    }

}
