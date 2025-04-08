package io.github.yunivers.nihilo.events.init;

import io.github.yunivers.nihilo.client.render.entity.CustomProjectileEntityRenderer;
import io.github.yunivers.nihilo.entity.PebbleEntity;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.client.event.render.entity.EntityRendererRegisterEvent;

import static io.github.yunivers.nihilo.Nihilo.NAMESPACE;

public class InitEntityRenderers
{
    // Entity Renderers

    @EventListener
    public static void registerEntityRenderers(EntityRendererRegisterEvent event) {
        event.renderers.put(PebbleEntity.class, new CustomProjectileEntityRenderer(NAMESPACE.id("item/stone_pebble")));
    }
}
