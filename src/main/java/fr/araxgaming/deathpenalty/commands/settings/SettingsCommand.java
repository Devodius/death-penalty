package fr.araxgaming.deathpenalty.commands.settings;

import cloud.commandframework.annotations.Argument;
import cloud.commandframework.annotations.CommandDescription;
import cloud.commandframework.annotations.CommandMethod;
import cloud.commandframework.annotations.CommandPermission;
import fr.araxgaming.deathpenalty.Loadable;
import fr.araxgaming.deathpenalty.config.enums.InventoryOnDeath;
import org.bukkit.command.CommandSender;

import javax.annotation.Nonnull;

@CommandPermission("deathpenalty.settings")
public class SettingsCommand extends Loadable {

    @CommandMethod("deathpenaltysettings|dpsettings inventoryOnDeath set <value>")
    public void setInventoryOnDeath(
            final @Nonnull CommandSender sender,
            final @Nonnull @Argument("value") InventoryOnDeath value
    ) {
        plugin.getPluginConfig().setInventoryOnDeath(value);
        sender.sendMessage("InventoryOnDeath has been set to " + value);
    }

    @CommandMethod("deathpenaltysettings|dpsettings inventoryOnDeath get")
    @CommandDescription("Used to get/set the setting of what happen on death")
    public void getInventoryOnDeath(
            final @Nonnull CommandSender sender
    ) {
        final InventoryOnDeath inventoryOnDeath = plugin.getPluginConfig().getInventoryOnDeath();
        sender.sendMessage("InventoryOnDeath is set to " + inventoryOnDeath);
    }

}
