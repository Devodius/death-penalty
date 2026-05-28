package fr.araxgaming.deathpenalty.services;

import fr.araxgaming.deathpenalty.DeathPenaltyPlugin;
import fr.araxgaming.deathpenalty.Loadable;
import org.bukkit.NamespacedKey;

public class NamespaceKeyService extends Loadable {

    private static final String KEY_TOTEM_OF_DROP_INVENTORY = "totem_of_drop_inventory";
    private static final String KEY_TOTEM_OF_KEEP_INVENTORY = "totem_of_keep_inventory";
    private static final String KEY_TOTEM_OF_UNDYING = "totem_of_undying";
    private static final String KEY_CUSTOM_TOTEM = "custom_totem";

    private NamespacedKey totemOfDropInventory;
    private NamespacedKey totemOfKeepInventory;
    private NamespacedKey totemOfUndying;
    private NamespacedKey customTotem;

    @Override
    public void load(final DeathPenaltyPlugin plugin) {
        super.load(plugin);

        totemOfDropInventory = new NamespacedKey(plugin, KEY_TOTEM_OF_DROP_INVENTORY);
        totemOfKeepInventory = new NamespacedKey(plugin, KEY_TOTEM_OF_KEEP_INVENTORY);
        totemOfUndying = new NamespacedKey(plugin, KEY_TOTEM_OF_UNDYING);
        customTotem = new NamespacedKey(plugin, KEY_CUSTOM_TOTEM);
    }

    public NamespacedKey getTotemOfDropInventory() {
        return totemOfDropInventory;
    }

    public NamespacedKey getTotemOfKeepInventory() {
        return totemOfKeepInventory;
    }

    public NamespacedKey getTotemOfUndying() {
        return totemOfUndying;
    }

    public NamespacedKey getCustomTotem() {
        return customTotem;
    }
}
