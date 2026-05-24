package fr.araxgaming.deathpenalty.commands.settings;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import fr.araxgaming.deathpenalty.DeathPenaltyPlugin;
import fr.araxgaming.deathpenalty.commands.LoadableCommand;
import fr.araxgaming.deathpenalty.commands.arguments_types.InventoryOnDeathArgumentType;
import fr.araxgaming.deathpenalty.config.enums.InventoryOnDeath;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import org.bukkit.command.CommandSender;

public class SettingsCommandNew extends LoadableCommand {

    @Override
    public void registerCommand(final LiteralArgumentBuilder<CommandSourceStack> pluginCommand) {
        pluginCommand.then(
                Commands.literal("inventoryOnDeath")
                        .requires(sender -> sender.getSender().hasPermission("deathpenalty.settings"))
                        .then(
                                Commands
                                        .literal("get")
                                        .executes(SettingsCommandNew::executeGet)
                        ).then(
                                Commands.literal("set")
                                        .then(
                                                Commands
                                                        .argument("value", new InventoryOnDeathArgumentType())
                                                        .executes(SettingsCommandNew::executeSet)
                                        )
                        )
        );
    }

    private static int executeGet(final CommandContext<CommandSourceStack> context) {
        final CommandSender sender = context.getSource().getSender();

        final InventoryOnDeath inventoryOnDeath = DeathPenaltyPlugin.getInstance().getPluginConfig().getInventoryOnDeath();
        sender.sendMessage("InventoryOnDeath is set to " + inventoryOnDeath);
        return Command.SINGLE_SUCCESS;
    }

    private static int executeSet(final CommandContext<CommandSourceStack> context) {
        final InventoryOnDeath newSetting = context.getArgument("value", InventoryOnDeath.class);
        final CommandSender sender = context.getSource().getSender();

        DeathPenaltyPlugin.getInstance().getPluginConfig().setInventoryOnDeath(newSetting);
        sender.sendMessage("InventoryOnDeath has been set to " + newSetting);
        return Command.SINGLE_SUCCESS;
    }
}
