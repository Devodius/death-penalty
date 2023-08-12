package fr.araxgaming.deathpenalty.scoreboard;

import fr.araxgaming.deathpenalty.DeathPenaltyPlugin;
import fr.araxgaming.deathpenalty.Loadable;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class DeathPenaltyScoreboardManager implements Loadable {

    private final DeathCountScoreboard deathCountScoreboard = new DeathCountScoreboard();

    private DeathPenaltyPlugin plugin;
    private Scoreboard scoreboard;

    @Override
    public void load(final DeathPenaltyPlugin plugin) {
        this.plugin = plugin;
        deathCountScoreboard.load(plugin);
    }

    @Override
    public void enable(final DeathPenaltyPlugin plugin) {
        deathCountScoreboard.enable(plugin);

        setupScoreboard();
    }

    @Override
    public void disable(final DeathPenaltyPlugin plugin) {
        deathCountScoreboard.disable(plugin);
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
