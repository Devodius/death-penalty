package fr.araxgaming.deathpenalty.config;

import fr.araxgaming.deathpenalty.DeathPenaltyPlugin;
import fr.araxgaming.deathpenalty.Loadable;
import fr.araxgaming.deathpenalty.config.enums.InventoryOnDeath;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.Objects;

public class DeathPenaltyConfigFile extends Loadable {

    private static final String INVENTORY_ON_DEATH_PATH = "inventoryOnDeath";

    @Override
    public void load(final DeathPenaltyPlugin plugin) {
        super.load(plugin);

        this.plugin.saveDefaultConfig();
    }

    InventoryOnDeath getInventoryOnDeath() {
        return getInventoryOnDeath(InventoryOnDeath.DEFAULT);
    }

    void setInventoryOnDeath(final InventoryOnDeath inventoryOnDeath) {
        final FileConfiguration config = plugin.getConfig();
        config.set(INVENTORY_ON_DEATH_PATH, inventoryOnDeath.toString());
        plugin.saveConfig();
    }

    InventoryOnDeath getInventoryOnDeath(final InventoryOnDeath defaultValue) {
        final FileConfiguration config = plugin.getConfig();
        final String textValue = Objects.requireNonNullElse(config.getString(INVENTORY_ON_DEATH_PATH), "");
        try {
            return InventoryOnDeath.valueOf(textValue);
        } catch (final IllegalArgumentException exception) {
            logger.severe("DeathPenaltyPlugin: invalid value for config " + INVENTORY_ON_DEATH_PATH + " using " + defaultValue.toString());
            return defaultValue;
        }
    }

}
