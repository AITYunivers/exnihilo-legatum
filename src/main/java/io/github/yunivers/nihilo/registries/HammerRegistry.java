package io.github.yunivers.nihilo.registries;

import com.mojang.serialization.Lifecycle;
import io.github.yunivers.nihilo.Nihilo;
import io.github.yunivers.nihilo.events.init.InitBlocks;
import io.github.yunivers.nihilo.events.init.InitItems;
import io.github.yunivers.nihilo.registries.events.HammerRegistryEvent;
import io.github.yunivers.nihilo.registries.helpers.Smashable;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.modificationstation.stationapi.api.StationAPI;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.mod.entrypoint.EventBusPolicy;
import net.modificationstation.stationapi.api.registry.*;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.Namespace;

import java.util.ArrayList;
import java.util.Objects;

@Entrypoint(eventBus = @EventBusPolicy(registerInstance = false))
@EventListener(phase = StationAPI.INTERNAL_PHASE)
public class HammerRegistry extends SimpleRegistry<Smashable>
{
    public static final RegistryKey<Registry<Smashable>> KEY = RegistryKey.ofRegistry(Nihilo.NAMESPACE.id("smashable"));
    public static final HammerRegistry INSTANCE = Registries.create(KEY, new HammerRegistry(), registry -> new Smashable(null, (Item)null, 0, 0), Lifecycle.experimental());

    public HammerRegistry() {
        super(KEY, Lifecycle.experimental(), false);
    }

    public static boolean containsBlock(Block block)
    {
        Identifier id = BlockRegistry.INSTANCE.getId(block);
        if (id == null)
            return false;
        for (int i = 0; i < INSTANCE.size(); i++)
            if (INSTANCE.containsId(Identifier.of(id + "." + i)))
                return true;
        return false;
    }

    public static ArrayList<Smashable> getRewards(Block block)
    {
        ArrayList<Smashable> rewardList = new ArrayList<>();

        for (Smashable reward : INSTANCE)
            if (reward.source == block && reward.hasOutput())
                rewardList.add(reward);

        return rewardList;
    }

    public static Block[] getBlocks()
    {
        ArrayList<Block> blocks = new ArrayList<>();

        for (Smashable reward : INSTANCE) {
            if (!blocks.contains(reward.source))
                blocks.add(reward.source);
        }

        return blocks.toArray(new Block[0]);
    }

    public static ArrayList<Smashable> getSmashables()
    {
        ArrayList<Smashable> smashables = new ArrayList<>();

        for (Smashable reward : INSTANCE) {
            smashables.add(reward);
        }

        return smashables;
    }

    public static String nameOf(Block block)
    {
        return Objects.requireNonNull(BlockRegistry.INSTANCE.getId(block)).path + "." + INSTANCE.size();
    }

    @EventListener
    public static void registerRewards(HammerRegistryEvent event)
    {
        event.register(Namespace.MINECRAFT)

        // Stone
            .accept(nameOf(Block.STONE), new Smashable(Block.STONE, InitItems.STONE_PEBBLE, 1.00f, 0.0f))
            .accept(nameOf(Block.STONE), new Smashable(Block.STONE, InitItems.STONE_PEBBLE, 0.75f, 0.1f))
            .accept(nameOf(Block.STONE), new Smashable(Block.STONE, InitItems.STONE_PEBBLE, 0.75f, 0.1f))
            .accept(nameOf(Block.STONE), new Smashable(Block.STONE, InitItems.STONE_PEBBLE, 0.50f, 0.1f))
            .accept(nameOf(Block.STONE), new Smashable(Block.STONE, InitItems.STONE_PEBBLE, 0.25f, 0.1f))
            .accept(nameOf(Block.STONE), new Smashable(Block.STONE, InitItems.STONE_PEBBLE, 0.05f, 0.1f))

        // Break Down Cobblestone -> Gravel -> Sand -> Dust
            .accept(nameOf(Block.COBBLESTONE), new Smashable(Block.COBBLESTONE, Block.GRAVEL, 1, 0))
            .accept(nameOf(Block.GRAVEL), new Smashable(Block.GRAVEL, Block.SAND, 1, 0))
            .accept(nameOf(Block.SAND), new Smashable(Block.SAND, InitBlocks.DUST, 1, 0))

        // Sandstone
            .accept(nameOf(Block.SANDSTONE), new Smashable(Block.SANDSTONE, Block.SAND, 1, 0))

        // TODO: Netherrack Gravel
        ;
    }
}
