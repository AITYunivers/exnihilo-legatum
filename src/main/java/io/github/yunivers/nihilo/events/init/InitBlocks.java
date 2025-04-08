package io.github.yunivers.nihilo.events.init;

import io.github.yunivers.nihilo.blocks.Barrel;
import io.github.yunivers.nihilo.blocks.Crucible;
import io.github.yunivers.nihilo.blocks.CrucibleUnfired;
import io.github.yunivers.nihilo.blocks.StoneBarrel;
import io.github.yunivers.nihilo.registries.events.CrucibleRegistryEvent;
import io.github.yunivers.nihilo.registries.events.HammerRegistryEvent;
import io.github.yunivers.nihilo.registries.events.HeatRegistryEvent;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.modificationstation.stationapi.api.StationAPI;
import net.modificationstation.stationapi.api.event.registry.BlockRegistryEvent;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;

import static io.github.yunivers.nihilo.Nihilo.NAMESPACE;

public class InitBlocks
{
    // Blocks
    public static Block BARREL_WOOD;
    public static Block BARREL_STONE;
    public static Block CRUCIBLE;
    public static Block CRUCIBLE_UNFIRED;
    public static Block DUST;

    @EventListener
    public void registerBlocks(BlockRegistryEvent event)
    {
        BARREL_WOOD = new Barrel(NAMESPACE.id("barrel"))
                .setTranslationKey(NAMESPACE, "barrel");

        BARREL_STONE = new StoneBarrel(NAMESPACE.id("stone_barrel"))
                .setTranslationKey(NAMESPACE, "stone_barrel");

        CRUCIBLE = new Crucible(NAMESPACE.id("crucible"))
                .setTranslationKey(NAMESPACE, "crucible");

        CRUCIBLE_UNFIRED = new CrucibleUnfired(NAMESPACE.id("crucible_unfired"))
                .setTranslationKey(NAMESPACE, "crucible_unfired");

        DUST = new TemplateBlock(NAMESPACE.id("dust"), Material.CLAY)
                .setHardness(0.4F)
                .setSoundGroup(Block.SAND_SOUND_GROUP)
                .setTranslationKey(NAMESPACE, "dust");

        // Call Registries
        StationAPI.EVENT_BUS.post(new HeatRegistryEvent());
        StationAPI.EVENT_BUS.post(new CrucibleRegistryEvent());
        StationAPI.EVENT_BUS.post(new HammerRegistryEvent());
    }
}
