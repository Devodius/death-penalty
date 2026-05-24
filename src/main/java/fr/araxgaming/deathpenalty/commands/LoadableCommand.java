package fr.araxgaming.deathpenalty.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import io.papermc.paper.command.brigadier.CommandSourceStack;

public abstract class LoadableCommand {

    public abstract void registerCommand(final LiteralArgumentBuilder<CommandSourceStack> pluginCommand);
}
