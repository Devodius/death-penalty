package fr.araxgaming.deathpenalty.recipes.totems;

import fr.araxgaming.deathpenalty.DeathPenaltyPlugin;
import fr.araxgaming.deathpenalty.Loadable;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class TotemOfKeepInventoryRecipes extends Loadable {

    private static final int MODEL_DATA = 3160002;
    private static final String NAMESPACE = "totem_of_keep_inventory";

    @Override
    public void load(final DeathPenaltyPlugin plugin) {
        super.load(plugin);

        final ItemStack item = getItemCrafted();
        final Recipe recipe = getRecipe(item);
        Bukkit.addRecipe(recipe);
    }

    private ItemStack getItemCrafted() {
        final ItemStack totem = new ItemStack(Material.TOTEM_OF_UNDYING);
        final ItemMeta meta = totem.getItemMeta();

        meta.setCustomModelData(MODEL_DATA);
        meta.displayName(Component.text("Totem of keep inventory"));
        meta.lore(List.of(
                Component.text("Keep your inventory on death"),
                Component.text("1 use totem, you do not need to have it in hand")
        ));

        totem.setItemMeta(meta);

        return totem;
    }

    private Recipe getRecipe(final ItemStack result) {
        final NamespacedKey namespacedKey = new NamespacedKey(plugin, NAMESPACE);
        final ShapelessRecipe recipe = new ShapelessRecipe(namespacedKey, result);

        recipe.addIngredient(4, Material.STICK);

        return recipe;
    }

}
