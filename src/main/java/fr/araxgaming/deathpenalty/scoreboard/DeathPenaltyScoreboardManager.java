package fr.araxgaming.deathpenalty.scoreboard;

import fr.araxgaming.deathpenalty.DeathPenaltyPlugin;
import fr.araxgaming.deathpenalty.LoadableManager;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class DeathPenaltyScoreboardManager extends LoadableManager {

    private final DeathCountScoreboard deathCountScoreboard = new DeathCountScoreboard();

    private Scoreboard scoreboard;

    public DeathPenaltyScoreboardManager() {
        registerLoadable(deathCountScoreboard);
    }

    @Override
    public void enable(final DeathPenaltyPlugin plugin) {
        super.enable(plugin);

        setupScoreboard();
    }

    public void onPlayerJoin(final PlayerJoinEvent playerJoinEvent) {
        deathCountScoreboard.onPlayerJoin(playerJoinEvent);

        playerJoinEvent.getPlayer().setScoreboard(scoreboard);
    }

    private void setupScoreboard() {
        scoreboard = plugin.getServer().getScoreboardManager().getNewScoreboard();

        final Objective deathCountScoreboardObjective = deathCountScoreboard.generateObjective(scoreboard);
        deathCountScoreboardObjective.setDisplaySlot(DisplaySlot.PLAYER_LIST);

    }
}
