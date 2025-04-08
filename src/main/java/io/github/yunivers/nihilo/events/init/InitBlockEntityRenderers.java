package io.github.yunivers.nihilo.events.init;

import io.github.yunivers.nihilo.blocks.entity.*;
import io.github.yunivers.nihilo.client.render.block.entity.*;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.client.event.block.entity.BlockEntityRendererRegisterEvent;

public class InitBlockEntityRenderers
{
    // Block Entity Renderers

    @EventListener
    public static void registerBlockEntityRenderers(BlockEntityRendererRegisterEvent event) {
        event.renderers.put(BarrelEntity.class, new BarrelRenderer());
        event.renderers.put(CrucibleEntity.class, new CrucibleRenderer());
        event.renderers.put(SieveEntity.class, new SieveRenderer());
    }
}
