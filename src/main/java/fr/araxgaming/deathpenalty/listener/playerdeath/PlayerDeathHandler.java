package fr.araxgaming.deathpenalty.listener.playerdeath;

import fr.araxgaming.deathpenalty.DeathPenaltyPlugin;
import fr.araxgaming.deathpenalty.config.DeathPenaltyConfig;
import fr.araxgaming.deathpenalty.config.enums.InventoryOnDeath;
import fr.araxgaming.deathpenalty.listener.PluginEventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.logging.Logger;

public class PlayerDeathHandler implements PluginEventHandler<PlayerDeathEvent> {

    private Logger log;
    private DeathPenaltyConfig config;

    @Override
    public void load(DeathPenaltyPlugin plugin) {
        log = plugin.getLogger();
        config = plugin.getPluginConfig();
    }

    public void execute(PlayerDeathEvent playerDeathEvent) {
        InventoryOnDeath inventoryOnDeath = config.getInventoryOnDeath();

        switch (inventoryOnDeath) {
            case DEFAULT -> {
                // Empty
            }
            case KEEP -> keepInventory(playerDeathEvent);
            case CLEAR -> deleteDrops(playerDeathEvent);
        }
    }

    private void deleteDrops(PlayerDeathEvent playerDeathEvent) {
        List<ItemStack> drops = playerDeathEvent.getDrops();

        drops.clear();
    }

    private void keepInventory(PlayerDeathEvent playerDeathEvent) {
        List<ItemStack> drops = playerDeathEvent.getDrops();
        List<ItemStack> keep = playerDeathEvent.getItemsToKeep();

        keep.clear();

        keep.addAll(drops);

        deleteDrops(playerDeathEvent);
    }

}
