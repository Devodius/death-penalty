package fr.araxgaming.deathpenalty.listener;

import fr.araxgaming.deathpenalty.DeathPenaltyPlugin;
import fr.araxgaming.deathpenalty.Loadable;
import fr.araxgaming.deathpenalty.listener.playerdeath.PlayerDeathHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathPenaltyListener implements Listener, Loadable {

    PlayerDeathHandler playerDeathHandler = new PlayerDeathHandler();

    public void load(DeathPenaltyPlugin plugin) {
        playerDeathHandler.load(plugin);
    }

    public void disable(DeathPenaltyPlugin plugin) {
        playerDeathHandler.disable(plugin);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerDeath(PlayerDeathEvent playerDeathEvent) {
        playerDeathHandler.execute(playerDeathEvent);
    }
}
