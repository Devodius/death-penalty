package fr.araxgaming.deathpenalty.commands.settings;

import cloud.commandframework.annotations.Argument;
import cloud.commandframework.annotations.CommandDescription;
import cloud.commandframework.annotations.CommandMethod;
import cloud.commandframework.annotations.CommandPermission;
import fr.araxgaming.deathpenalty.DeathPenaltyPlugin;
import fr.araxgaming.deathpenalty.Loadable;
import fr.araxgaming.deathpenalty.config.enums.InventoryOnDeath;
import org.bukkit.command.CommandSender;

import javax.annotation.Nonnull;

@SuppressWarnings("unused")
@CommandPermission("deathpenalty.settings")
public class SettingsCommand implements Loadable {

    private DeathPenaltyPlugin deathPenaltyPlugin;

    @Override
    public void load(DeathPenaltyPlugin plugin) {
        deathPenaltyPlugin = plugin;
    }

    @Override
    public void enable(DeathPenaltyPlugin plugin) {
    }

    @Override
    public void disable(DeathPenaltyPlugin plugin) {
    }

    @CommandMethod("deathpenaltysettings|dpsettings inventoryOnDeath set <value>")
    public void setInventoryOnDeath(
            final @Nonnull CommandSender sender,
            final @Nonnull @Argument("value") InventoryOnDeath value
    ) {
        deathPenaltyPlugin.getPluginConfig().setInventoryOnDeath(value);
        sender.sendMessage("InventoryOnDeath has been set to " + value);
    }

    @CommandMethod("deathpenaltysettings|dpsettings inventoryOnDeath get")
    @CommandDescription("Used to get/set the setting of what happen on death")
    public void getInventoryOnDeath(
            final @Nonnull CommandSender sender
    ) {
        InventoryOnDeath inventoryOnDeath = deathPenaltyPlugin.getPluginConfig().getInventoryOnDeath();
        sender.sendMessage("InventoryOnDeath is set to " + inventoryOnDeath);
    }
}
