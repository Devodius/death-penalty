package fr.araxgaming.deathpenalty.recipes.totems;

import fr.araxgaming.deathpenalty.DeathPenaltyPlugin;
import fr.araxgaming.deathpenalty.Loadable;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;

public class TotemOfUndyingRecipes extends Loadable {

    private final TotemOfDropInventoryRecipes totemOfDropInventoryRecipes;
    private final TotemOfKeepInventoryRecipes totemOfKeepInventoryRecipes;

    public TotemOfUndyingRecipes(final TotemOfDropInventoryRecipes totemOfDropInventoryRecipes, final TotemOfKeepInventoryRecipes totemOfKeepInventoryRecipes) {
        this.totemOfDropInventoryRecipes = totemOfDropInventoryRecipes;
        this.totemOfKeepInventoryRecipes = totemOfKeepInventoryRecipes;
    }

    @Override
    public void load(final DeathPenaltyPlugin plugin) {
        super.load(plugin);

        final ItemStack item = getItemCrafted();
        final Recipe recipe = getRecipe(item);
        Bukkit.addRecipe(recipe);
    }

    public ItemStack getItemCrafted() {
        return new ItemStack(Material.TOTEM_OF_UNDYING);
    }

    private Recipe getRecipe(final ItemStack result) {
        final ShapedRecipe recipe = new ShapedRecipe(plugin.getNamespaceKeyService().getTotemOfUndying(), result);

        recipe.shape("ABC", "DEF", "GHI");
        recipe.setIngredient('A', Material.RESIN_BLOCK);
        recipe.setIngredient('B', Material.TURTLE_HELMET);
        recipe.setIngredient('C', Material.HONEY_BLOCK);
        recipe.setIngredient('D', totemOfDropInventoryRecipes.getItemCrafted());
        recipe.setIngredient('E', Material.BEACON);
        recipe.setIngredient('F', totemOfKeepInventoryRecipes.getItemCrafted());
        recipe.setIngredient('G', Material.OCHRE_FROGLIGHT);
        recipe.setIngredient('H', Material.PITCHER_PLANT);
        recipe.setIngredient('I', Material.YELLOW_SHULKER_BOX);

        return recipe;
    }

}
