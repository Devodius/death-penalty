package fr.araxgaming.deathpenalty.recipes;

import fr.araxgaming.deathpenalty.recipes.totems.TotemOfDropInventoryRecipes;
import fr.araxgaming.deathpenalty.recipes.totems.TotemOfKeepInventoryRecipes;
import fr.araxgaming.deathpenalty.LoadableManager;

public class DeathPenaltyRecipesManager extends LoadableManager {

    private final TotemOfDropInventoryRecipes totemOfDropInventoryRecipes = new TotemOfDropInventoryRecipes();
    private final TotemOfKeepInventoryRecipes totemOfKeepInventoryRecipes = new TotemOfKeepInventoryRecipes();

    public DeathPenaltyRecipesManager() {
        registerLoadable(totemOfDropInventoryRecipes);
        registerLoadable(totemOfKeepInventoryRecipes);
    }
}
