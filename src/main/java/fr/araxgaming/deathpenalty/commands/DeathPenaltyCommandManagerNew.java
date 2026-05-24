package fr.araxgaming.deathpenalty.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.tree.LiteralCommandNode;
import fr.araxgaming.deathpenalty.commands.settings.SettingsCommandNew;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.lifecycle.event.registrar.ReloadableRegistrarEvent;

import java.util.ArrayList;
import java.util.List;

public class DeathPenaltyCommandManagerNew {

    private final List<LoadableCommand> loadables = new ArrayList<>();

    public DeathPenaltyCommandManagerNew() {
        registerCommand(new SettingsCommandNew());
    }

    public void bootstrapCommand(final ReloadableRegistrarEvent<Commands> commands) {
        final LiteralArgumentBuilder<CommandSourceStack> pluginCommandsBuilder = Commands.literal("deathpenaltysettings");
        bootstrapChildCommand(pluginCommandsBuilder);
        final LiteralCommandNode<CommandSourceStack> pluginCommandNode = pluginCommandsBuilder.build();

        final LiteralCommandNode<CommandSourceStack> aliasNode = Commands.literal("dpsettings").redirect(pluginCommandNode).build();

        commands.registrar().register(pluginCommandNode);
        commands.registrar().register(aliasNode);
    }

    private void registerCommand(final LoadableCommand command) {
        loadables.add(command);
    }

    private void bootstrapChildCommand(final LiteralArgumentBuilder<CommandSourceStack> pluginCommandsBuilder) {
        loadables.forEach((loadable) -> loadable.registerCommand(pluginCommandsBuilder));
    }
}
