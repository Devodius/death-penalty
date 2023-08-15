package fr.araxgaming.deathpenalty.listener.playerjoin;

import fr.araxgaming.deathpenalty.DeathPenaltyPlugin;
import fr.araxgaming.deathpenalty.Loadable;
import fr.araxgaming.deathpenalty.listener.PluginEventHandler;
import fr.araxgaming.deathpenalty.scoreboard.DeathPenaltyScoreboardManager;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinHandler extends Loadable implements PluginEventHandler<PlayerJoinEvent> {

    private DeathPenaltyScoreboardManager scoreboardManager;

    @Override
    public void load(final DeathPenaltyPlugin plugin) {
        super.load(plugin);
        scoreboardManager = this.plugin.getDeathPenaltyScoreboardManager();
    }

    @Override
    public void execute(final PlayerJoinEvent event) {
        scoreboardManager.onPlayerJoin(event);
    }
}
