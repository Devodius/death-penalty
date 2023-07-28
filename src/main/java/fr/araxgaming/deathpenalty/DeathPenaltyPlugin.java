package fr.araxgaming.deathpenalty;

import fr.araxgaming.deathpenalty.commands.DeathPenaltyCommandManager;
import fr.araxgaming.deathpenalty.config.DeathPenaltyConfig;
import fr.araxgaming.deathpenalty.listener.DeathPenaltyListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class DeathPenaltyPlugin extends JavaPlugin {

    private final Logger logger = getLogger();
    private final DeathPenaltyConfig deathPenaltyConfig = new DeathPenaltyConfig();
    private final DeathPenaltyListener deathPenaltyListener = new DeathPenaltyListener();
    private final DeathPenaltyCommandManager deathPenaltyCommandManager = new DeathPenaltyCommandManager();

    @Override
    public void onLoad() {
        super.onLoad();
        deathPenaltyConfig.load(this);
        deathPenaltyCommandManager.load(this);
        deathPenaltyListener.load(this);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        deathPenaltyConfig.enable(this);
        deathPenaltyCommandManager.enable(this);
        deathPenaltyListener.enable(this);
        getServer().getPluginManager().registerEvents(deathPenaltyListener, this);
    }

    @Override
    public void onDisable() {
        super.onDisable();
        deathPenaltyConfig.disable(this);
        deathPenaltyCommandManager.disable(this);
        deathPenaltyListener.disable(this);
    }

    public DeathPenaltyConfig getPluginConfig() {
        return deathPenaltyConfig;
    }
}
