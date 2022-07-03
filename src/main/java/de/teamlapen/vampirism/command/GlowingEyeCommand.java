package de.teamlapen.vampirism.command;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import de.teamlapen.lib.lib.util.BasicCommand;
import de.teamlapen.vampirism.player.vampire.VampirePlayer;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class GlowingEyeCommand extends BasicCommand {

    public static ArgumentBuilder<CommandSourceStack, ?> register() {
        return Commands.literal("glowingEye")
                .then(Commands.argument("on", BoolArgumentType.bool())
                        .executes(context -> {
                            return setGlowingEye(context, context.getSource().getPlayerOrException(), BoolArgumentType.getBool(context, "on"));
                        }));
    }

    private static int setGlowingEye(CommandContext<CommandSourceStack> context, Player player, boolean on) {
        VampirePlayer.getOpt(player).ifPresent(vampire -> vampire.setGlowingEyes(on));
        if (on) {
            context.getSource().sendSuccess(Component.translatable("command.vampirism.base.glowing_eyes.enabled", on), false);
        } else {
            context.getSource().sendSuccess(Component.translatable("command.vampirism.base.glowing_eyes.disabled", on), false);
        }
        return 0;
    }
}
