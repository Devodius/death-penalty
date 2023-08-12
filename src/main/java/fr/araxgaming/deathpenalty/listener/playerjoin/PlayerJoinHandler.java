package fr.araxgaming.deathpenalty.listener.playerjoin;

import fr.araxgaming.deathpenalty.DeathPenaltyPlugin;
import fr.araxgaming.deathpenalty.listener.PluginEventHandler;
import fr.araxgaming.deathpenalty.scoreboard.DeathPenaltyScoreboardManager;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinHandler implements PluginEventHandler<PlayerJoinEvent> {

    private DeathPenaltyPlugin plugin;
    private DeathPenaltyScoreboardManager scoreboardManager;

    @Override
    public void load(final DeathPenaltyPlugin plugin) {
        this.plugin = plugin;
        scoreboardManager = this.plugin.getDeathPenaltyScoreboardManager();
    }

    @Override
    public void execute(final PlayerJoinEvent event) {
        scoreboardManager.onPlayerJoin(event);
    }
}
