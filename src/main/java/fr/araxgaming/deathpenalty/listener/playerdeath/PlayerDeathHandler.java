package fr.araxgaming.deathpenalty.listener.playerdeath;

import fr.araxgaming.deathpenalty.DeathPenaltyPlugin;
import fr.araxgaming.deathpenalty.Loadable;
import fr.araxgaming.deathpenalty.config.DeathPenaltyConfig;
import fr.araxgaming.deathpenalty.config.enums.InventoryOnDeath;
import fr.araxgaming.deathpenalty.listener.PluginEventHandler;
import fr.araxgaming.deathpenalty.recipes.totems.TotemOfDropInventoryRecipes;
import fr.araxgaming.deathpenalty.recipes.totems.TotemOfKeepInventoryRecipes;
import fr.araxgaming.deathpenalty.services.PlayerDeathService;
import fr.araxgaming.deathpenalty.services.PlayerInventoryService;
import fr.araxgaming.deathpenalty.services.PlayerParticleService;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.List;
import java.util.Objects;

public class PlayerDeathHandler extends Loadable implements PluginEventHandler<PlayerDeathEvent> {

    private DeathPenaltyConfig config;

    @Override
    public void load(final DeathPenaltyPlugin plugin) {
        super.load(plugin);

        config = plugin.getPluginConfig();
    }

    private void doInventoryDeath(final PlayerDeathEvent playerDeathEvent, final InventoryOnDeath inventoryOnDeath) {
        final List<ItemStack> drops = playerDeathEvent.getDrops();
        final List<ItemStack> keep = playerDeathEvent.getItemsToKeep();

        switch (inventoryOnDeath) {
            case DEFAULT -> {
                // Empty
            }
            case TOTEM -> totemInventory(playerDeathEvent.getPlayer(), drops, keep);
            case KEEP -> PlayerDeathService.keepInventory(drops, keep);
            case CLEAR -> PlayerDeathService.deleteDrops(drops);
        }
    }

    private void totemInventory(final Player player, final List<ItemStack> drops, final List<ItemStack> keep) {
        final PlayerInventory inventory = player.getInventory();


        if (isDefaultTotem(inventory.getItemInMainHand()) || isDefaultTotem(inventory.getItemInOffHand())) {
            return;
        }

        final int foundIndex = PlayerInventoryService.searchItemsForDataKey(drops, plugin.getNamespaceKeyService().getCustomTotem());

        if (foundIndex == -1 || foundIndex >= drops.size()) {
            PlayerDeathService.deleteDrops(drops);
            return;
        }

        final ItemStack itemStack = drops.get(foundIndex);
        final String totemType = PlayerInventoryService.getDataFromKey(itemStack, plugin.getNamespaceKeyService().getCustomTotem());

        if (Objects.equals(totemType, TotemOfKeepInventoryRecipes.CUSTOM_TOTEM_NAME)) {
            PlayerParticleService.playerTotemEffect(player, itemStack);
            PlayerInventoryService.removeOneFromList(drops, foundIndex);
            PlayerDeathService.keepInventory(drops, keep);
        } else if (Objects.equals(totemType, TotemOfDropInventoryRecipes.CUSTOM_TOTEM_NAME)) {
            PlayerParticleService.playerTotemEffect(player, itemStack);
            PlayerInventoryService.removeOneFromList(drops, foundIndex);
        }
    }

    private boolean isDefaultTotem(final ItemStack item) {
        if (item.getType() != Material.TOTEM_OF_UNDYING) {
            return false;
        }

        return !PlayerInventoryService.hasDataFromKey(item, plugin.getNamespaceKeyService().getCustomTotem());
    }

    @Override
    public void execute(final PlayerDeathEvent playerDeathEvent) {
        final InventoryOnDeath inventoryOnDeath = config.getInventoryOnDeath();

        doInventoryDeath(playerDeathEvent, inventoryOnDeath);
    }

}
