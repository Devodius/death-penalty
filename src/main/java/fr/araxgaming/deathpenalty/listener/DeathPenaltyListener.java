package fr.araxgaming.deathpenalty.listener;

import fr.araxgaming.deathpenalty.LoadableManager;
import fr.araxgaming.deathpenalty.listener.entityDeath.EntityDeathHandler;
import fr.araxgaming.deathpenalty.listener.entityResurrect.EntityResurrectHandler;
import fr.araxgaming.deathpenalty.listener.playerdeath.PlayerDeathHandler;
import fr.araxgaming.deathpenalty.listener.playerjoin.PlayerJoinHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityResurrectEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class DeathPenaltyListener extends LoadableManager implements Listener {

    private final PlayerDeathHandler playerDeathHandler = new PlayerDeathHandler();
    private final PlayerJoinHandler playerJoinHandler = new PlayerJoinHandler();
    private final EntityResurrectHandler entityResurrectHandler = new EntityResurrectHandler();
    private final EntityDeathHandler entityDeathHandler = new EntityDeathHandler();

    public DeathPenaltyListener() {
        registerLoadable(playerDeathHandler);
        registerLoadable(playerJoinHandler);
        registerLoadable(entityResurrectHandler);
        registerLoadable(entityDeathHandler);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerDeath(final PlayerDeathEvent playerDeathEvent) {
        playerDeathHandler.execute(playerDeathEvent);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onEntityDeath(final EntityDeathEvent entityDeathEvent) {
        entityDeathHandler.execute(entityDeathEvent);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityResurrect(final EntityResurrectEvent entityResurrectEvent) {
        entityResurrectHandler.execute(entityResurrectEvent);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerJoin(final PlayerJoinEvent playerJoinEvent) {
        playerJoinHandler.execute(playerJoinEvent);
    }
}
