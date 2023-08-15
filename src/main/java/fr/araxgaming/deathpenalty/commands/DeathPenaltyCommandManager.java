package fr.araxgaming.deathpenalty.commands;

import cloud.commandframework.CommandTree;
import cloud.commandframework.annotations.AnnotationParser;
import cloud.commandframework.arguments.parser.ParserParameters;
import cloud.commandframework.arguments.parser.StandardParameters;
import cloud.commandframework.execution.CommandExecutionCoordinator;
import cloud.commandframework.meta.CommandMeta;
import cloud.commandframework.paper.PaperCommandManager;
import fr.araxgaming.deathpenalty.DeathPenaltyPlugin;
import fr.araxgaming.deathpenalty.LoadableManager;
import fr.araxgaming.deathpenalty.commands.settings.SettingsCommand;
import org.bukkit.command.CommandSender;

import java.util.function.Function;

public class DeathPenaltyCommandManager extends LoadableManager {

    private final SettingsCommand settingsCommand = new SettingsCommand();
    private PaperCommandManager<CommandSender> commandManager;
    private AnnotationParser<CommandSender> annotationParser;

    public DeathPenaltyCommandManager() {
        registerLoadable(settingsCommand);
    }

    @Override
    public void enable(final DeathPenaltyPlugin plugin) {
        super.enable(plugin);

        final Function<CommandSender, CommandSender> mapperFunction = Function.identity();
        final Function<CommandTree<CommandSender>, CommandExecutionCoordinator<CommandSender>> executionCoordinatorFunction = CommandExecutionCoordinator.simpleCoordinator();

        try {
            commandManager = new PaperCommandManager<>(plugin, executionCoordinatorFunction, mapperFunction, mapperFunction);
        } catch (final Exception e) {
            plugin.getLogger().severe("Failed to initialize the command manager: " + e);
            plugin.getServer().getPluginManager().disablePlugin(plugin);
            return;
        }

        final Function<ParserParameters, CommandMeta> commandMetaFunction = p ->
                CommandMeta.simple()
                        .with(CommandMeta.DESCRIPTION, p.get(StandardParameters.DESCRIPTION, "No description"))
                        .build();
        annotationParser = new AnnotationParser<>(commandManager, CommandSender.class, commandMetaFunction);

        annotationParser.parse(settingsCommand);
    }

}
