package fr.araxgaming.deathpenalty.scoreboard;

import net.kyori.adventure.text.Component;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public interface DeathPenaltyScoreboard {

    default void onPlayerJoin(final PlayerJoinEvent playerJoinEvent) {
    }

    Objective generateObjective(final Scoreboard scoreboard);

    String getObjectiveName();

    Component getObjectiveDisplayName();

}
