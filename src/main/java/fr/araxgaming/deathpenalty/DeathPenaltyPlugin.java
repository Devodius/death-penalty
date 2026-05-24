package fr.araxgaming.deathpenalty;

import fr.araxgaming.deathpenalty.config.DeathPenaltyConfig;
import fr.araxgaming.deathpenalty.listener.DeathPenaltyListener;
import fr.araxgaming.deathpenalty.scoreboard.DeathPenaltyScoreboardManager;
import org.bukkit.plugin.java.JavaPlugin;

public class DeathPenaltyPlugin extends JavaPlugin {

    private static DeathPenaltyPlugin instance;

    private final DeathPenaltyConfig deathPenaltyConfig = new DeathPenaltyConfig();
    private final DeathPenaltyListener deathPenaltyListener = new DeathPenaltyListener();
    private final DeathPenaltyScoreboardManager deathPenaltyScoreboardManager = new DeathPenaltyScoreboardManager();

    @Override
    public void onLoad() {
        super.onLoad();
        deathPenaltyConfig.load(this);
        deathPenaltyScoreboardManager.load(this);
        deathPenaltyListener.load(this);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        deathPenaltyConfig.enable(this);
        deathPenaltyScoreboardManager.enable(this);
        deathPenaltyListener.enable(this);
        getServer().getPluginManager().registerEvents(deathPenaltyListener, this);

        instance = this;
    }

    @Override
    public void onDisable() {
        super.onDisable();
        deathPenaltyConfig.disable(this);
        deathPenaltyScoreboardManager.disable(this);
        deathPenaltyListener.disable(this);
    }

    public DeathPenaltyConfig getPluginConfig() {
        return deathPenaltyConfig;
    }

    public DeathPenaltyScoreboardManager getDeathPenaltyScoreboardManager() {
        return deathPenaltyScoreboardManager;
    }

    public static DeathPenaltyPlugin getInstance() {
        return DeathPenaltyPlugin.instance;
    }
}
