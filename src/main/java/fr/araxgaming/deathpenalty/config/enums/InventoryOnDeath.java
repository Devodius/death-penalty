package fr.araxgaming.deathpenalty.config.enums;

import java.util.List;

public enum InventoryOnDeath {

    DEFAULT("DEFAULT"),
    CLEAR("CLEAR"),
    KEEP("KEEP"),
    ;

    private final String text;

    InventoryOnDeath(final String text) {
        this.text = text;
    }

    public static List<String> getDescription() {
        return List.of(
                "DEFAULT : Minecraft default",
                "CLEAR : Clear inventory on death",
                "KEEP : Keep inventory on death"
        );
    }

    @Override
    public String toString() {
        return text;
    }
}
