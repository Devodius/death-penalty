package fr.araxgaming.deathpenalty.recipes.totems;

import fr.araxgaming.deathpenalty.DeathPenaltyPlugin;
import fr.araxgaming.deathpenalty.Loadable;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;

public class TotemOfKeepInventoryRecipes extends Loadable {

    public static final String CUSTOM_TOTEM_NAME = "totem_of_keep_inventory";

    @Override
    public void load(final DeathPenaltyPlugin plugin) {
        super.load(plugin);

        final ItemStack item = getItemCrafted();
        final Recipe recipe = getRecipe(item);
        Bukkit.addRecipe(recipe);
    }

    public ItemStack getItemCrafted() {
        final ItemStack totem = new ItemStack(Material.TOTEM_OF_UNDYING);
        final ItemMeta meta = totem.getItemMeta();

        meta.itemName(Component.text("Totem of keep inventory"));
        meta.setItemModel(plugin.getNamespaceKeyService().getTotemOfKeepInventory());
        meta.lore(List.of(
            Component.text("Keep your inventory on death"),
            Component.text("1 use totem, you do not need to have it in hand")
        ));

        final PersistentDataContainer pdc = meta.getPersistentDataContainer();
        pdc.set(plugin.getNamespaceKeyService().getCustomTotem(), PersistentDataType.STRING, CUSTOM_TOTEM_NAME);

        totem.setItemMeta(meta);

        return totem;
    }

    private Recipe getRecipe(final ItemStack result) {
        final ShapedRecipe recipe = new ShapedRecipe(plugin.getNamespaceKeyService().getTotemOfKeepInventory(), result);

        recipe.shape("QGQ", "DWS", "QNQ");

        recipe.setIngredient('Q', Material.QUARTZ_BLOCK);
        recipe.setIngredient('G', Material.GOLDEN_APPLE);
        recipe.setIngredient('D', Material.DRAGON_BREATH);
        recipe.setIngredient('W', Material.WITHER_SKELETON_SKULL);
        recipe.setIngredient('S', Material.NETHERITE_UPGRADE_SMITHING_TEMPLATE);
        recipe.setIngredient('N', Material.NETHERITE_INGOT);

        return recipe;
    }

}
