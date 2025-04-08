package io.github.yunivers.nihilo.events.init;

import io.github.yunivers.nihilo.blocks.entity.BarrelEntity;
import io.github.yunivers.nihilo.blocks.entity.CrucibleEntity;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.event.block.entity.BlockEntityRegisterEvent;
import net.modificationstation.stationapi.api.util.Identifier;

import static io.github.yunivers.nihilo.Nihilo.NAMESPACE;

public class InitBlockEntities
{
    // Block Entities

    @EventListener
    public static void registerBlockEntities(BlockEntityRegisterEvent event) {
        event.register(BarrelEntity.class, String.valueOf(Identifier.of(NAMESPACE, "barrel")));
        event.register(CrucibleEntity.class, String.valueOf(Identifier.of(NAMESPACE, "crucible")));
    }
}
