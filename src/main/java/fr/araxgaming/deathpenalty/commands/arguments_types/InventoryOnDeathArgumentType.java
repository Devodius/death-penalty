package fr.araxgaming.deathpenalty.commands.arguments_types;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import fr.araxgaming.deathpenalty.config.enums.InventoryOnDeath;
import io.papermc.paper.command.brigadier.MessageComponentSerializer;
import io.papermc.paper.command.brigadier.argument.CustomArgumentType;
import net.kyori.adventure.text.Component;
import org.jspecify.annotations.NullMarked;

import java.util.Locale;
import java.util.concurrent.CompletableFuture;

@NullMarked
public final class InventoryOnDeathArgumentType implements CustomArgumentType.Converted<InventoryOnDeath, String> {

    private static final DynamicCommandExceptionType ERROR_INVALID_INVENTORY_ON_DEATH = new DynamicCommandExceptionType(setting -> MessageComponentSerializer.message().serialize(Component.text(setting + " is not a valid setting value!")));

    @Override
    public InventoryOnDeath convert(final String nativeType) throws CommandSyntaxException {
        try {
            return InventoryOnDeath.valueOf(nativeType.toUpperCase(Locale.ROOT));
        } catch (final IllegalArgumentException ignored) {
            throw ERROR_INVALID_INVENTORY_ON_DEATH.create(nativeType);
        }
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(final CommandContext<S> context, final SuggestionsBuilder builder) {
        for (final InventoryOnDeath setting : InventoryOnDeath.values()) {
            final String name = setting.toString();

            // Only suggest if the setting name matches the user input
            if (name.startsWith(builder.getRemaining())) {
                builder.suggest(setting.toString());
            }
        }

        return builder.buildFuture();
    }

    @Override
    public ArgumentType<String> getNativeType() {
        return StringArgumentType.word();
    }

}
