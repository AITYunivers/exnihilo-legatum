package io.github.yunivers.nihilo;

import io.github.yunivers.nihilo.blocks.Barrel;
import io.github.yunivers.nihilo.blocks.Crucible;
import io.github.yunivers.nihilo.blocks.CrucibleUnfired;
import io.github.yunivers.nihilo.blocks.StoneBarrel;
import io.github.yunivers.nihilo.blocks.entity.BarrelEntity;
import io.github.yunivers.nihilo.blocks.entity.CrucibleEntity;
import io.github.yunivers.nihilo.client.render.block.entity.BarrelRenderer;
import io.github.yunivers.nihilo.client.render.block.entity.CrucibleRenderer;
import io.github.yunivers.nihilo.client.render.entity.CustomProjectileEntityRenderer;
import io.github.yunivers.nihilo.entity.PebbleEntity;
import io.github.yunivers.nihilo.items.Pebble;
import io.github.yunivers.nihilo.registries.events.*;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.modificationstation.stationapi.api.StationAPI;
import net.modificationstation.stationapi.api.client.event.block.entity.BlockEntityRendererRegisterEvent;
import net.modificationstation.stationapi.api.client.event.render.entity.EntityRendererRegisterEvent;
import net.modificationstation.stationapi.api.client.event.texture.TextureRegisterEvent;
import net.modificationstation.stationapi.api.client.texture.atlas.Atlases;
import net.modificationstation.stationapi.api.client.texture.atlas.ExpandableAtlas;
import net.modificationstation.stationapi.api.event.block.entity.BlockEntityRegisterEvent;
import net.modificationstation.stationapi.api.event.mod.InitEvent;
import net.modificationstation.stationapi.api.event.registry.BlockRegistryEvent;
import net.modificationstation.stationapi.api.event.registry.ItemRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.EntrypointManager;
import net.modificationstation.stationapi.api.template.item.TemplateItem;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.Namespace;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;

public class Nihilo
{
    @SuppressWarnings("UnstableApiUsage")
    public static final Namespace NAMESPACE = Namespace.resolve();
    public static final Logger LOGGER = NAMESPACE.getLogger();
    public static int IgnoreNextItemEntity = 0;

    static
    {
        EntrypointManager.registerLookup(MethodHandles.lookup());
    }
}
