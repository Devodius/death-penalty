package fr.araxgaming.deathpenalty.scoreboard;

import fr.araxgaming.deathpenalty.DeathPenaltyPlugin;
import net.kyori.adventure.text.Component;
import org.bukkit.Statistic;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.Criteria;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;


public class DeathCountScoreboard implements DeathPenaltyScoreboard {

    private static final String OBJECTIF_NAME = "DeathCount";
    private static final Component OBJECTIF_DISPLAY_NAME = Component.text("Death count");

    private Objective objective;
    private Criteria criteria;

    @Override
    public void enable(final DeathPenaltyPlugin plugin) {
        criteria = Criteria.statistic(Statistic.DEATHS);
    }

    @Override
    public Objective generateObjective(final Scoreboard scoreboard) {
        objective = scoreboard.registerNewObjective(getObjectiveName(), criteria, getObjectiveDisplayName());

        return objective;
    }

    @Override
    public void onPlayerJoin(final PlayerJoinEvent playerJoinEvent) {
        final Score playerScore = objective.getScore(playerJoinEvent.getPlayer());
        final int playerDeathNumber = playerJoinEvent.getPlayer().getStatistic(Statistic.DEATHS);

        playerScore.setScore(playerDeathNumber);
    }

    @Override
    public String getObjectiveName() {
        return OBJECTIF_NAME;
    }

    @Override
    public Component getObjectiveDisplayName() {
        return OBJECTIF_DISPLAY_NAME;
    }
}
