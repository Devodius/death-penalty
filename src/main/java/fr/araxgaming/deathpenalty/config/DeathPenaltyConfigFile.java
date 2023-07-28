package fr.araxgaming.deathpenalty.config;

import fr.araxgaming.deathpenalty.DeathPenaltyPlugin;
import fr.araxgaming.deathpenalty.Loadable;
import fr.araxgaming.deathpenalty.config.enums.InventoryOnDeath;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Logger;

public class DeathPenaltyConfigFile implements Loadable {

    private static final String INVENTORY_ON_DEATH_PATH = "inventoryOnDeath";

    private DeathPenaltyPlugin plugin;

    private Logger logger;

    @Override
    public void load(DeathPenaltyPlugin plugin) {
        logger = plugin.getLogger();
        this.plugin = plugin;

        this.plugin.saveDefaultConfig();
    }

    public InventoryOnDeath getInventoryOnDeath() {
        return getInventoryOnDeath(InventoryOnDeath.DEFAULT);
    }

    public void setInventoryOnDeath(InventoryOnDeath inventoryOnDeath) {
        FileConfiguration config = plugin.getConfig();
        config.set(INVENTORY_ON_DEATH_PATH, inventoryOnDeath.toString());
        plugin.saveConfig();
    }

    public InventoryOnDeath getInventoryOnDeath(InventoryOnDeath defaultValue) {
        FileConfiguration config = plugin.getConfig();
        String textValue = Objects.requireNonNullElse(config.getString(INVENTORY_ON_DEATH_PATH), "");
        try {
            return InventoryOnDeath.valueOf(textValue);
        } catch (IllegalArgumentException exception) {
            logger.severe("DeathPenaltyPlugin: invalid value for config " + INVENTORY_ON_DEATH_PATH + " using " + defaultValue.toString());
            return defaultValue;
        }
    }

}
