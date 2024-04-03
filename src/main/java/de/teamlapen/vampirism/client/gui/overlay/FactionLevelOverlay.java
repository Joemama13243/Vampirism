package de.teamlapen.vampirism.client.gui.overlay;

import de.teamlapen.vampirism.api.entity.factions.IPlayableFaction;
import de.teamlapen.vampirism.config.VampirismConfig;
import de.teamlapen.vampirism.entity.factions.FactionPlayerHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.client.gui.overlay.ExtendedGui;
import net.neoforged.neoforge.client.gui.overlay.IGuiOverlay;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class FactionLevelOverlay implements IGuiOverlay {
    private final Minecraft mc = Minecraft.getInstance();

    @Override
    public void render(@NotNull ExtendedGui gui, @NotNull GuiGraphics graphics, float partialTicks, int width, int height) {
        if (this.mc.player != null && this.mc.player.isAlive() && this.mc.player.jumpableVehicle() == null && !this.mc.options.hideGui) {
            gui.setupOverlayRenderState(true, false);

            FactionPlayerHandler handler = FactionPlayerHandler.get(this.mc.player);
            IPlayableFaction<?> faction = handler.getCurrentFaction();
            if (this.mc.gameMode != null && this.mc.gameMode.hasExperience() && faction != null) {
                // boolean flag1 = false;
                int color = faction.getColor();
                int lord = handler.getLordLevel();
                String text = null;
                if (lord > 0) {
                    text = Optional.ofNullable(handler.getLordTitleShort()).map(Component::getString).map(x -> x.substring(0, Math.min(3, x.length()))).orElse(null);
                }

                if (text == null) {
                    text = String.valueOf(handler.getCurrentLevel());
                }

                int x = (this.mc.getWindow().getGuiScaledWidth() - this.mc.font.width(text)) / 2 + VampirismConfig.CLIENT.guiLevelOffsetX.get();
                int y = this.mc.getWindow().getGuiScaledHeight() - VampirismConfig.CLIENT.guiLevelOffsetY.get();
                graphics.drawString(this.mc.font, text, x + 1, y, 0, false);
                graphics.drawString(this.mc.font, text, x - 1, y, 0, false);
                graphics.drawString(this.mc.font, text, x, y + 1, 0, false);
                graphics.drawString(this.mc.font, text, x, y - 1, 0, false);
                graphics.drawString(this.mc.font, text, x, y, color, false);
            }
        }
    }
}
