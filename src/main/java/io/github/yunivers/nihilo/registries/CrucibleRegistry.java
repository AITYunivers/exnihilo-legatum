package io.github.yunivers.nihilo.registries;

import com.mojang.serialization.Lifecycle;
import io.github.yunivers.nihilo.Nihilo;
import io.github.yunivers.nihilo.registries.events.CrucibleRegistryEvent;
import io.github.yunivers.nihilo.registries.helpers.Meltable;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.minecraft.block.LiquidBlock;
import net.minecraft.item.Item;
import net.modificationstation.stationapi.api.StationAPI;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.mod.entrypoint.EventBusPolicy;
import net.modificationstation.stationapi.api.registry.*;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.Namespace;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

@Entrypoint(eventBus = @EventBusPolicy(registerInstance = false))
@EventListener(phase = StationAPI.INTERNAL_PHASE)
public class CrucibleRegistry extends SimpleRegistry<Meltable>
{
    public static final RegistryKey<Registry<Meltable>> KEY = RegistryKey.ofRegistry(Nihilo.NAMESPACE.id("meltable"));
    public static final CrucibleRegistry INSTANCE = Registries.create(KEY, new CrucibleRegistry(), registry -> new Meltable(null, 0, 0, null, 0, null), Lifecycle.experimental());

    public CrucibleRegistry() {
        super(KEY, Lifecycle.experimental(), false);
    }

    public static boolean containsItem(Item item, int meta) {
        Identifier id = ItemRegistry.INSTANCE.getId(item);
        if (id == null)
            return false;
        return CrucibleRegistry.INSTANCE.containsId(Identifier.of(id + ":" + meta));
    }

    public static @Nullable Meltable getItem(Item item, int meta) {
        Identifier id = ItemRegistry.INSTANCE.getId(item);
        if (id == null)
            return null;
        Optional<Meltable> meltable = CrucibleRegistry.INSTANCE.getOrEmpty(Identifier.of(id + ":" + meta));
        return meltable.orElseGet(() -> null);
    }

    @EventListener
    public static void registerMeltables(CrucibleRegistryEvent event)
    {
        event.register(Namespace.MINECRAFT)

        // Lava
            .accept("cobblestone:0", new Meltable(Block.COBBLESTONE, 0, 2000, (LiquidBlock)Block.LAVA, 250, Block.COBBLESTONE))
            .accept("stone:0", new Meltable(Block.STONE, 0, 2000, (LiquidBlock)Block.LAVA, 250, Block.STONE))
            .accept("gravel:0", new Meltable(Block.GRAVEL, 0, 2000, (LiquidBlock)Block.LAVA, 250, Block.GRAVEL))
            .accept("netherrack:0", new Meltable(Block.NETHERRACK, 0, 2000, (LiquidBlock)Block.LAVA, 1000, Block.NETHERRACK))

        // Water
            .accept("snow_block:0", new Meltable(Block.SNOW_BLOCK, 0, 2000, (LiquidBlock)Block.WATER, 500, Block.SNOW_BLOCK))
            .accept("ice:0", new Meltable(Block.ICE, 0, 2000, (LiquidBlock)Block.WATER, 1000, Block.ICE))

        ;
    }
}
