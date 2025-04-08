package io.github.yunivers.nihilo.registries;

import com.mojang.serialization.Lifecycle;
import io.github.yunivers.nihilo.Nihilo;
import io.github.yunivers.nihilo.registries.events.HeatRegistryEvent;
import io.github.yunivers.nihilo.registries.helpers.HeatSource;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.modificationstation.stationapi.api.StationAPI;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.mod.entrypoint.EventBusPolicy;
import net.modificationstation.stationapi.api.registry.*;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.function.BulkBiConsumer;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

@Entrypoint(eventBus = @EventBusPolicy(registerInstance = false))
@EventListener(phase = StationAPI.INTERNAL_PHASE)
public class HeatRegistry extends SimpleRegistry<HeatSource>
{
    public static final RegistryKey<Registry<HeatSource>> KEY = RegistryKey.ofRegistry(Nihilo.NAMESPACE.id("heat"));
    public static final HeatRegistry INSTANCE = Registries.create(KEY, new HeatRegistry(), registry -> new HeatSource(null, 0, 0), Lifecycle.experimental());

    public HeatRegistry() {
        super(KEY, Lifecycle.experimental(), false);
    }

    public static void register(BulkBiConsumer<String, HeatSource> registry, Block block, float value)
    {
        for(int x = 0; x <= 15; x++)
        {
            registry.accept(BlockRegistry.INSTANCE.getId(block).path + ":" + x, new HeatSource(Block.TORCH, x, value));
        }
    }

    public static boolean containsBlock(Block block, int meta) {
        Identifier id = BlockRegistry.INSTANCE.getId(block);
        if (id == null)
            return false;
        return HeatRegistry.INSTANCE.containsId(Identifier.of(id + ":" + meta));
    }

    public static @Nullable HeatSource getBlock(Block block, int meta) {
        Identifier id = BlockRegistry.INSTANCE.getId(block);
        if (id == null)
            return null;
        Optional<HeatSource> heat = HeatRegistry.INSTANCE.getOrEmpty(Identifier.of(id + ":" + meta));
        return heat.orElseGet(() -> null);
    }

    @EventListener
    public static void registerHeat(HeatRegistryEvent event)
    {
        var register = event.register(Namespace.MINECRAFT);
        register(register, Block.TORCH, 0.1f);
        register(register, Block.LAVA, 0.2f);
        register(register, Block.FLOWING_LAVA, 0.1f);
        register(register, Block.LIT_FURNACE, 0.15f);
        register(register, Block.FIRE, 0.3f);
    }
}
