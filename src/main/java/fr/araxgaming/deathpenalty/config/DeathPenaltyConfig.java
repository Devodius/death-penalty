package fr.araxgaming.deathpenalty.config;

import fr.araxgaming.deathpenalty.DeathPenaltyPlugin;
import fr.araxgaming.deathpenalty.Loadable;
import fr.araxgaming.deathpenalty.config.enums.InventoryOnDeath;

public class DeathPenaltyConfig implements Loadable {

    private final DeathPenaltyConfigFile deathPenaltyConfigFile = new DeathPenaltyConfigFile();

    private InventoryOnDeath inventoryOnDeath;

    @Override
    public void load(DeathPenaltyPlugin plugin) {
        deathPenaltyConfigFile.load(plugin);
        loadAll();
    }

    @Override
    public void enable(DeathPenaltyPlugin plugin) {
        deathPenaltyConfigFile.enable(plugin);
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

    public void setInventoryOnDeath(InventoryOnDeath inventoryOnDeath) {
        this.inventoryOnDeath = inventoryOnDeath;
        deathPenaltyConfigFile.setInventoryOnDeath(inventoryOnDeath);
    }

}
