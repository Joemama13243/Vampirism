package de.teamlapen.vampirism.api.entity.player;

import de.teamlapen.vampirism.api.entity.factions.ILordTitleProvider;
import de.teamlapen.vampirism.api.entity.factions.IPlayableFaction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

/**
 * Interface for the player lord related data.
 */
public interface ILordPlayer {

    /**
     * @return The faction of this lord player or null if not currently a lord
     */
    @Nullable
    IPlayableFaction<?> getLordFaction();

    int getLordLevel();

    /**
     * @return Null, if level ==0
     */
    @Nullable
    Component getLordTitle();

    /**
     * @return Null, if level ==0
     */
    @Nullable
    Component getLordTitleShort();

    @NotNull
    Player getPlayer();

    /**
     * If the lord titles should use female versions (if available)
     */
    IPlayableFaction.TitleGender titleGender();
}
