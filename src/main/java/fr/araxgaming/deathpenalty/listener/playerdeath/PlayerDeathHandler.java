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

public class PlayerDeathHandler extends Loadable implements PluginEventHandler<PlayerDeathEvent> {

    private DeathPenaltyConfig config;

    @Override
    public void load(final DeathPenaltyPlugin plugin) {
        super.load(plugin);

        config = plugin.getPluginConfig();
    }

    private static void doInventoryDeath(final PlayerDeathEvent playerDeathEvent, final InventoryOnDeath inventoryOnDeath) {
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

    private static void totemInventory(final Player player, final List<ItemStack> drops, final List<ItemStack> keep) {
        final ItemStack originalTotem = new ItemStack(Material.TOTEM_OF_UNDYING);
        final ItemStack totemKeep = TotemOfKeepInventoryRecipes.getItemCrafted();
        final ItemStack totemDrop = TotemOfDropInventoryRecipes.getItemCrafted();
        final List<ItemStack> validTotems = List.of(totemKeep, totemDrop);
        final PlayerInventory inventory = player.getInventory();

        if (PlayerInventoryService.isSameCustomItem(inventory.getItemInMainHand(), originalTotem) || PlayerInventoryService.isSameCustomItem(inventory.getItemInOffHand(), originalTotem)) {
            return;
        }

        final int foundIndex = PlayerInventoryService.isItemInList(drops, validTotems);

        if (foundIndex == -1 || foundIndex >= drops.size()) {
            PlayerDeathService.deleteDrops(drops);
            return;
        }

        final ItemStack itemStack = drops.get(foundIndex);

        if (PlayerInventoryService.isSameCustomItem(itemStack, totemKeep)) {
            PlayerParticleService.playerTotemEffect(player, itemStack);
            PlayerInventoryService.removeOneFromList(drops, foundIndex);
            PlayerDeathService.keepInventory(drops, keep);
        } else if (PlayerInventoryService.isSameCustomItem(itemStack, totemDrop)) {
            PlayerParticleService.playerTotemEffect(player, itemStack);
            PlayerInventoryService.removeOneFromList(drops, foundIndex);
        }
    }

    public void execute(final PlayerDeathEvent playerDeathEvent) {
        final InventoryOnDeath inventoryOnDeath = config.getInventoryOnDeath();

        doInventoryDeath(playerDeathEvent, inventoryOnDeath);
    }

}
