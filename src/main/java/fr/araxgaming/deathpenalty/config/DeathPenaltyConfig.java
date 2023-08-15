package fr.araxgaming.deathpenalty.config;

import fr.araxgaming.deathpenalty.DeathPenaltyPlugin;
import fr.araxgaming.deathpenalty.Loadable;
import fr.araxgaming.deathpenalty.config.enums.InventoryOnDeath;

public class DeathPenaltyConfig extends Loadable {

    private final DeathPenaltyConfigFile deathPenaltyConfigFile = new DeathPenaltyConfigFile();

    private InventoryOnDeath inventoryOnDeath;

    @Override
    public void load(final DeathPenaltyPlugin plugin) {
        deathPenaltyConfigFile.load(plugin);
        loadAll();
    }

    @Override
    public void enable(final DeathPenaltyPlugin plugin) {
        deathPenaltyConfigFile.enable(plugin);
    }

    @Override
    public void disable(final DeathPenaltyPlugin plugin) {
        deathPenaltyConfigFile.disable(plugin);
    }

    private void loadAll() {
        loadInventoryOnDeath();
    }

    private void loadInventoryOnDeath() {
        inventoryOnDeath = deathPenaltyConfigFile.getInventoryOnDeath();
    }

    public InventoryOnDeath getInventoryOnDeath() {
        return inventoryOnDeath;
    }

    public void setInventoryOnDeath(final InventoryOnDeath inventoryOnDeath) {
        this.inventoryOnDeath = inventoryOnDeath;
        deathPenaltyConfigFile.setInventoryOnDeath(inventoryOnDeath);
    }

}
