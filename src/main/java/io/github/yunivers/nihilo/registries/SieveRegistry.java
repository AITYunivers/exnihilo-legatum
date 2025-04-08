package io.github.yunivers.nihilo.registries;

import com.mojang.serialization.Lifecycle;
import io.github.yunivers.nihilo.Nihilo;
import io.github.yunivers.nihilo.events.init.InitBlocks;
import io.github.yunivers.nihilo.events.init.InitItems;
import io.github.yunivers.nihilo.registries.events.HammerRegistryEvent;
import io.github.yunivers.nihilo.registries.events.SieveRegistryEvent;
import io.github.yunivers.nihilo.registries.helpers.SiftReward;
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
public class SieveRegistry extends SimpleRegistry<SiftReward> {
    public static final RegistryKey<Registry<SiftReward>> KEY = RegistryKey.ofRegistry(Nihilo.NAMESPACE.id("siftable"));
    public static final SieveRegistry INSTANCE = Registries.create(KEY, new SieveRegistry(), registry -> new SiftReward(null, (Item)null, 0), Lifecycle.experimental());

    public SieveRegistry() {
        super(KEY, Lifecycle.experimental(), false);
    }

    public static boolean containsItem(Item item)
    {
        Identifier id = ItemRegistry.INSTANCE.getId(item);
        if (id == null)
            return false;
        for (int i = 0; i < INSTANCE.size(); i++)
            if (INSTANCE.containsId(Identifier.of(id + "." + i)))
                return true;
        return false;
    }

    public static ArrayList<SiftReward> getRewards(Item item)
    {
        ArrayList<SiftReward> rewardList = new ArrayList<>();

        for (SiftReward reward : INSTANCE)
            if (item == Item.BLOCK_ITEMS.get(reward.source) && reward.hasItem())
                rewardList.add(reward);

        return rewardList;
    }

    public static Item[] getItems()
    {
        ArrayList<Item> items = new ArrayList<>();

        for (SiftReward reward : INSTANCE)
        {
            Item item = Item.BLOCK_ITEMS.get(reward.source);
            if (!items.contains(item))
                items.add(item);
        }

        return items.toArray(new Item[0]);
    }

    public static String nameOf(Block block)
    {
        return Objects.requireNonNull(BlockRegistry.INSTANCE.getId(block)).path + "." + INSTANCE.size();
    }

    @EventListener
    public static void registerRewards(SieveRegistryEvent event)
    {
        event.register(Namespace.MINECRAFT)

        // Dirt
            .accept(nameOf(Block.DIRT), new SiftReward(Block.DIRT, InitItems.STONE_PEBBLE, 1))
            .accept(nameOf(Block.DIRT), new SiftReward(Block.DIRT, InitItems.STONE_PEBBLE, 1))
            .accept(nameOf(Block.DIRT), new SiftReward(Block.DIRT, InitItems.STONE_PEBBLE, 2))
            .accept(nameOf(Block.DIRT), new SiftReward(Block.DIRT, InitItems.STONE_PEBBLE, 2))
            .accept(nameOf(Block.DIRT), new SiftReward(Block.DIRT, InitItems.STONE_PEBBLE, 3))
            .accept(nameOf(Block.DIRT), new SiftReward(Block.DIRT, InitItems.STONE_PEBBLE, 3))
            .accept(nameOf(Block.DIRT), new SiftReward(Block.DIRT, Item.SEEDS, 15))
            // TODO: Grass Seeds
            // TODO: Sugarcane Seeds
            // TODO: Oak Seeds/Acorn
            // TODO: Spruce Seeds
            // TODO: Birch Seeds

        // Gravel
            .accept(nameOf(Block.GRAVEL), new SiftReward(Block.GRAVEL, Item.FLINT, 4))
            .accept(nameOf(Block.GRAVEL), new SiftReward(Block.GRAVEL, Item.COAL, 8))
            .accept(nameOf(Block.GRAVEL), new SiftReward(Block.GRAVEL, Item.DYE, 4, 20)) // Lapis Lazuli
            .accept(nameOf(Block.GRAVEL), new SiftReward(Block.GRAVEL, Item.DIAMOND, 128))

        // Sand
            .accept(nameOf(Block.SAND), new SiftReward(Block.SAND, Item.DYE, 3, 32)); // Cocoa Beans
            // TODO: Cactus Seeds
            // TODO: Spores? Maybe not

        event.register(Nihilo.NAMESPACE)
        // Dust
            .accept(nameOf(InitBlocks.DUST), new SiftReward(InitBlocks.DUST, Item.DYE, 15, 5)) // Bone Meal
            .accept(nameOf(InitBlocks.DUST), new SiftReward(InitBlocks.DUST, Item.REDSTONE, 8))
            .accept(nameOf(InitBlocks.DUST), new SiftReward(InitBlocks.DUST, Item.GUNPOWDER, 15))
            .accept(nameOf(InitBlocks.DUST), new SiftReward(InitBlocks.DUST, Item.GLOWSTONE_DUST, 16));
    }
}
