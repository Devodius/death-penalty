package fr.araxgaming.deathpenalty;

import fr.araxgaming.deathpenalty.commands.DeathPenaltyCommandManager;
import fr.araxgaming.deathpenalty.config.DeathPenaltyConfig;
import fr.araxgaming.deathpenalty.listener.DeathPenaltyListener;
import fr.araxgaming.deathpenalty.scoreboard.DeathPenaltyScoreboardManager;
import org.bukkit.plugin.java.JavaPlugin;

public class DeathPenaltyPlugin extends JavaPlugin {

    private final DeathPenaltyConfig deathPenaltyConfig = new DeathPenaltyConfig();
    private final DeathPenaltyListener deathPenaltyListener = new DeathPenaltyListener();
    private final DeathPenaltyCommandManager deathPenaltyCommandManager = new DeathPenaltyCommandManager();
    private final DeathPenaltyScoreboardManager deathPenaltyScoreboardManager = new DeathPenaltyScoreboardManager();

    @Override
    public void onLoad() {
        super.onLoad();
        deathPenaltyConfig.load(this);
        deathPenaltyCommandManager.load(this);
        deathPenaltyScoreboardManager.load(this);
        deathPenaltyListener.load(this);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        deathPenaltyConfig.enable(this);
        deathPenaltyCommandManager.enable(this);
        deathPenaltyScoreboardManager.enable(this);
        deathPenaltyListener.enable(this);
        getServer().getPluginManager().registerEvents(deathPenaltyListener, this);
    }

    @Override
    public void onDisable() {
        super.onDisable();
        deathPenaltyConfig.disable(this);
        deathPenaltyCommandManager.disable(this);
        deathPenaltyScoreboardManager.disable(this);
        deathPenaltyListener.disable(this);
    }

    public DeathPenaltyConfig getPluginConfig() {
        return deathPenaltyConfig;
    }

    public DeathPenaltyScoreboardManager getDeathPenaltyScoreboardManager() {
        return deathPenaltyScoreboardManager;
    }
}
