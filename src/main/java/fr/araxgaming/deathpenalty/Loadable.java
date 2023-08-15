package fr.araxgaming.deathpenalty;

import java.util.logging.Logger;

public abstract class Loadable {
    protected DeathPenaltyPlugin plugin;
    protected Logger logger;

    /**
     * First function called on plugin load
     *
     * @param plugin ref to the main plugin
     */
    public void load(final DeathPenaltyPlugin plugin) {
        this.plugin = plugin;
        this.logger = plugin.getLogger();
    }

    /**
     * Second function called on plugin load
     *
     * @param plugin ref to the main plugin
     */
    public void enable(final DeathPenaltyPlugin plugin) {
    }

    /**
     * Called when plugin is deactivated
     *
     * @param plugin ref to the main plugin
     */
    public void disable(final DeathPenaltyPlugin plugin) {
    }

}
