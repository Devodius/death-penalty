package fr.araxgaming.deathpenalty.listener;

import fr.araxgaming.deathpenalty.DeathPenaltyPlugin;
import fr.araxgaming.deathpenalty.Loadable;
import fr.araxgaming.deathpenalty.listener.playerdeath.PlayerDeathHandler;
import fr.araxgaming.deathpenalty.listener.playerjoin.PlayerJoinHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class DeathPenaltyListener implements Listener, Loadable {

    private final PlayerDeathHandler playerDeathHandler = new PlayerDeathHandler();
    private final PlayerJoinHandler playerJoinHandler = new PlayerJoinHandler();

    @Override
    public void enable(final DeathPenaltyPlugin plugin) {
        playerDeathHandler.enable(plugin);
        playerJoinHandler.enable(plugin);
    }

    @Override
    public void load(final DeathPenaltyPlugin plugin) {
        playerDeathHandler.load(plugin);
        playerJoinHandler.load(plugin);
    }

    @Override
    public void disable(final DeathPenaltyPlugin plugin) {
        playerDeathHandler.disable(plugin);
        playerJoinHandler.disable(plugin);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerDeath(final PlayerDeathEvent playerDeathEvent) {
        playerDeathHandler.execute(playerDeathEvent);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerJoin(final PlayerJoinEvent playerJoinEvent) {
        playerJoinHandler.execute(playerJoinEvent);
    }
}
