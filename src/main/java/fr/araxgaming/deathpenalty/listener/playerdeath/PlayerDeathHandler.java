package fr.araxgaming.deathpenalty.listener.playerdeath;

import fr.araxgaming.deathpenalty.DeathPenaltyPlugin;
import fr.araxgaming.deathpenalty.Loadable;
import fr.araxgaming.deathpenalty.config.DeathPenaltyConfig;
import fr.araxgaming.deathpenalty.config.enums.InventoryOnDeath;
import fr.araxgaming.deathpenalty.listener.PluginEventHandler;
import fr.araxgaming.deathpenalty.services.PlayerDeathService;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathHandler extends Loadable implements PluginEventHandler<PlayerDeathEvent> {

    private DeathPenaltyConfig config;

    @Override
    public void load(final DeathPenaltyPlugin plugin) {
        super.load(plugin);

        config = plugin.getPluginConfig();
    }

    public void execute(final PlayerDeathEvent playerDeathEvent) {
        final InventoryOnDeath inventoryOnDeath = config.getInventoryOnDeath();

        PlayerDeathService.doInventoryDeath(playerDeathEvent, inventoryOnDeath);
    }

}
