package fr.araxgaming.deathpenalty;

import fr.araxgaming.deathpenalty.config.DeathPenaltyConfig;
import fr.araxgaming.deathpenalty.listener.DeathPenaltyListener;
import fr.araxgaming.deathpenalty.recipes.DeathPenaltyRecipesManager;
import fr.araxgaming.deathpenalty.scoreboard.DeathPenaltyScoreboardManager;
import fr.araxgaming.deathpenalty.services.NamespaceKeyService;
import org.bukkit.plugin.java.JavaPlugin;

public class DeathPenaltyPlugin extends JavaPlugin {

    private static DeathPenaltyPlugin instance;

    private final DeathPenaltyConfig deathPenaltyConfig = new DeathPenaltyConfig();
    private final NamespaceKeyService namespaceKeyService = new NamespaceKeyService();
    private final DeathPenaltyListener deathPenaltyListener = new DeathPenaltyListener();
    private final DeathPenaltyScoreboardManager deathPenaltyScoreboardManager = new DeathPenaltyScoreboardManager();
    private final DeathPenaltyRecipesManager deathPenaltyRecipesManager = new DeathPenaltyRecipesManager();

    public static DeathPenaltyPlugin getInstance() {
        return DeathPenaltyPlugin.instance;
    }

    @Override
    public void onLoad() {
        super.onLoad();

        deathPenaltyConfig.load(this);
        namespaceKeyService.load(this);
        deathPenaltyScoreboardManager.load(this);
        deathPenaltyListener.load(this);
        deathPenaltyRecipesManager.load(this);
    }

    @Override
    public void onEnable() {
        super.onEnable();

        deathPenaltyConfig.enable(this);
        namespaceKeyService.enable(this);
        deathPenaltyScoreboardManager.enable(this);
        deathPenaltyListener.enable(this);
        deathPenaltyRecipesManager.enable(this);
        getServer().getPluginManager().registerEvents(deathPenaltyListener, this);

        instance = this;
    }

    @Override
    public void onDisable() {
        super.onDisable();
        
        deathPenaltyConfig.disable(this);
        namespaceKeyService.disable(this);
        deathPenaltyScoreboardManager.disable(this);
        deathPenaltyListener.disable(this);
        deathPenaltyRecipesManager.disable(this);
    }

    public DeathPenaltyConfig getPluginConfig() {
        return deathPenaltyConfig;
    }

    public DeathPenaltyScoreboardManager getDeathPenaltyScoreboardManager() {
        return deathPenaltyScoreboardManager;
    }

    public NamespaceKeyService getNamespaceKeyService() {
        return namespaceKeyService;
    }

}
