package fr.araxgaming.deathpenalty.recipes;

import fr.araxgaming.deathpenalty.DeathPenaltyPlugin;
import fr.araxgaming.deathpenalty.LoadableManager;
import fr.araxgaming.deathpenalty.recipes.totems.TotemOfDropInventoryRecipes;
import fr.araxgaming.deathpenalty.recipes.totems.TotemOfKeepInventoryRecipes;

public class DeathPenaltyRecipesManager extends LoadableManager {

    private final TotemOfDropInventoryRecipes totemOfDropInventoryRecipes = new TotemOfDropInventoryRecipes();
    private final TotemOfKeepInventoryRecipes totemOfKeepInventoryRecipes = new TotemOfKeepInventoryRecipes();

    public DeathPenaltyRecipesManager() {
        registerLoadable(totemOfDropInventoryRecipes);
        registerLoadable(totemOfKeepInventoryRecipes);
    }

    @Override
    public void load(final DeathPenaltyPlugin plugin) {
        if (plugin.getPluginConfig().isEnableCustomTotemCraft()) {
            super.load(plugin);
        }
    }

    @Override
    public void enable(final DeathPenaltyPlugin plugin) {
        if (plugin.getPluginConfig().isEnableCustomTotemCraft()) {
            super.enable(plugin);
        }
    }
}
