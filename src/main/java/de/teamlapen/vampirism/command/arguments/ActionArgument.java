package de.teamlapen.vampirism.command.arguments;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import de.teamlapen.vampirism.api.VampirismRegistries;
import de.teamlapen.vampirism.api.entity.player.actions.IAction;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;

public class ActionArgument implements ArgumentType<IAction<?>> {
    public static final DynamicCommandExceptionType ACTION_NOT_FOUND = new DynamicCommandExceptionType((particle) -> {
        return Component.translatable("command.vampirism.argument.action.notfound", particle);
    });
    private static final Collection<String> EXAMPLES = Arrays.asList("action", "modid:action");

    public static ActionArgument actions() {
        return new ActionArgument();
    }

    public static IAction<?> getAction(CommandContext<CommandSourceStack> context, String name) {
        return context.getArgument(name, IAction.class);
    }

    @Override
    public Collection<String> getExamples() {
        return EXAMPLES;
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        return SharedSuggestionProvider.suggestResource(VampirismRegistries.ACTIONS.get().getKeys(), builder);
    }

    @Override
    public IAction<?> parse(StringReader reader) throws CommandSyntaxException {
        ResourceLocation id = ResourceLocation.read(reader);
        IAction<?> action = VampirismRegistries.ACTIONS.get().getValue(id);
        if (action == null)
            throw ACTION_NOT_FOUND.create(id);
        return action;
    }
}
