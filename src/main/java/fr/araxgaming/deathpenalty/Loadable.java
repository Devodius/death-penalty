package fr.araxgaming.deathpenalty;

public interface Loadable {

    /**
     * First function called on plugin load
     *
     * @param plugin ref to the main plugin
     */
    default void load(DeathPenaltyPlugin plugin) {
    }

    /**
     * Second function called on plugin load
     *
     * @param plugin ref to the main plugin
     */
    default void enable(DeathPenaltyPlugin plugin) {
    }

    /**
     * Called when plugin is deactivated
     *
     * @param plugin ref to the main plugin
     */
    default void disable(DeathPenaltyPlugin plugin) {
    }

}
