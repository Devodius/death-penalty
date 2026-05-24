package fr.araxgaming.deathpenalty;

import fr.araxgaming.deathpenalty.commands.DeathPenaltyCommandManagerNew;
import io.papermc.paper.plugin.bootstrap.BootstrapContext;
import io.papermc.paper.plugin.bootstrap.PluginBootstrap;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;

public class DeathPenaltyPluginBootstrap implements PluginBootstrap {

    private final DeathPenaltyCommandManagerNew deathPenaltyCommandManager = new DeathPenaltyCommandManagerNew();

    @Override
    public void bootstrap(final BootstrapContext context) {
        context.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, deathPenaltyCommandManager::bootstrapCommand);
    }

}
