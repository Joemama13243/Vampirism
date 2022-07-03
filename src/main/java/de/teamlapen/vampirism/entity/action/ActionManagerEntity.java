package de.teamlapen.vampirism.entity.action;

import com.google.common.collect.Lists;
import de.teamlapen.vampirism.api.VampirismRegistries;
import de.teamlapen.vampirism.api.entity.EntityClassType;
import de.teamlapen.vampirism.api.entity.actions.EntityActionTier;
import de.teamlapen.vampirism.api.entity.actions.IEntityAction;
import de.teamlapen.vampirism.api.entity.actions.IEntityActionManager;
import de.teamlapen.vampirism.api.entity.factions.IFaction;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.commons.lang3.ArrayUtils;

import javax.annotation.Nonnull;
import java.util.List;

public class ActionManagerEntity implements IEntityActionManager {

    @Override
    public List<IEntityAction> getAllEntityActions() {
        return Lists.newArrayList(getRegistry().getValues());
    }

    @Override
    public List<IEntityAction> getAllEntityActionsByTierAndClassType(IFaction<?> faction, @Nonnull EntityActionTier tier, EntityClassType classType) {
        List<IEntityAction> actions = getAllEntityActions();
        actions.removeIf(action -> action.getFaction() != faction || action.getTier().getId() > tier.getId() || !ArrayUtils.contains(action.getClassTypes(), classType));
        return actions;
    }

    @Override
    public IForgeRegistry<IEntityAction> getRegistry() {
        return VampirismRegistries.ENTITY_ACTIONS.get();
    }
}
