package fr.araxgaming.deathpenalty.listener;

import fr.araxgaming.deathpenalty.LoadableManager;
import fr.araxgaming.deathpenalty.listener.playerdeath.PlayerDeathHandler;
import fr.araxgaming.deathpenalty.listener.playerjoin.PlayerJoinHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class DeathPenaltyListener extends LoadableManager implements Listener {

    private final PlayerDeathHandler playerDeathHandler = new PlayerDeathHandler();
    private final PlayerJoinHandler playerJoinHandler = new PlayerJoinHandler();

    public DeathPenaltyListener() {
        registerLoadable(playerDeathHandler);
        registerLoadable(playerJoinHandler);
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
