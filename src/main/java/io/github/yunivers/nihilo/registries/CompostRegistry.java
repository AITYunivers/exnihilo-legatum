package io.github.yunivers.nihilo.registries;

import com.mojang.serialization.Lifecycle;
import io.github.yunivers.nihilo.Nihilo;
import io.github.yunivers.nihilo.registries.events.CompostRegistryEvent;
import io.github.yunivers.nihilo.registries.helpers.Compostable;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
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
public class CompostRegistry extends SimpleRegistry<Compostable>
{
    public static final RegistryKey<Registry<Compostable>> KEY = RegistryKey.ofRegistry(Nihilo.NAMESPACE.id("compostables"));
    public static final CompostRegistry INSTANCE = Registries.create(KEY, new CompostRegistry(), registry -> new Compostable((Item) null, 0, 0, ColorRegistry.color("white")), Lifecycle.experimental());

    public CompostRegistry() {
        super(KEY, Lifecycle.experimental(), false);
    }

    public static boolean containsItem(Item item, int meta) {
        Identifier id = ItemRegistry.INSTANCE.getId(item);
        if (id == null)
            return false;
        return CompostRegistry.INSTANCE.containsId(Identifier.of(id + ":" + meta));
    }

    public static @Nullable Compostable getItem(Item item, int meta) {
        Identifier id = ItemRegistry.INSTANCE.getId(item);
        if (id == null)
            return null;
        Optional<Compostable> compostable = CompostRegistry.INSTANCE.getOrEmpty(Identifier.of(id + ":" + meta));
        return compostable.orElseGet(() -> null);
    }

    @EventListener
    public static void registerCompostables(CompostRegistryEvent event)
    {
        event.register(Namespace.MINECRAFT)

        // Saplings
            .accept("sapling:0", new Compostable(Block.SAPLING, 0, 0.125f, ColorRegistry.color("oak")))
            .accept("sapling:1", new Compostable(Block.SAPLING, 1, 0.125f, ColorRegistry.color("spruce")))
            .accept("sapling:2", new Compostable(Block.SAPLING, 2, 0.125f, ColorRegistry.color("birch")))

        // Leaves
            .accept("leaves:0", new Compostable(Block.LEAVES, 0, 0.125f, ColorRegistry.color("oak")))
            .accept("leaves:1", new Compostable(Block.LEAVES, 1, 0.125f, ColorRegistry.color("spruce")))
            .accept("leaves:2", new Compostable(Block.LEAVES, 2, 0.125f, ColorRegistry.color("birch")))

        // Wheat
            .accept("wheat:0", new Compostable(Item.WHEAT, 0, 0.08f, ColorRegistry.color("wheat")))
        // Bread
            .accept("bread:0", new Compostable(Item.BREAD, 0, 0.16f, ColorRegistry.color("bread")))

        // Flowers
            .accept("dandelion:0", new Compostable(Block.DANDELION, 0, 0.1f, ColorRegistry.color("oak")))
            .accept("rose:0", new Compostable(Block.ROSE, 0, 0.1f, ColorRegistry.color("oak")))

        // Mushrooms
            .accept("brown_mushroom:0", new Compostable(Block.BROWN_MUSHROOM, 0, 0.1f, ColorRegistry.color("dandelion")))
            .accept("red_mushroom:0", new Compostable(Block.RED_MUSHROOM, 0, 0.1f, ColorRegistry.color("rose")))

        // Porkchops
            .accept("porkchop:0", new Compostable(Item.RAW_PORKCHOP, 0, 0.2f, ColorRegistry.color("pork_raw")))
            .accept("cooked_porkchop:0", new Compostable(Item.COOKED_PORKCHOP, 0, 0.2f, ColorRegistry.color("pork_cooked")))

        // Fish
            .accept("cod:0", new Compostable(Item.RAW_FISH, 0, 0.15f, ColorRegistry.color("fish_raw")))
            .accept("cooked_cod:0", new Compostable(Item.COOKED_FISH, 0, 0.15f, ColorRegistry.color("fish_cooked")))

        // Apple
            .accept("apple:0", new Compostable(Item.APPLE, 0, 0.1f, ColorRegistry.color("apple")))
        // Pumpkin
            .accept("carved_pumpkin:0", new Compostable(Block.PUMPKIN, 0, 1f / 6, ColorRegistry.color("pumpkin")))
        // Jack-O-Lantern
            .accept("jack_o_lantern:0", new Compostable(Block.JACK_O_LANTERN, 0, 1f / 6, ColorRegistry.color("pumpkin")))
        // Cactus
            .accept("cactus:0", new Compostable(Block.CACTUS, 0, 0.1f, ColorRegistry.color("cactus")))
        // Tall Grass
            .accept("grass:0", new Compostable(Block.GRASS, 0, 0.08f, ColorRegistry.color("tall_grass")))
        // Egg
            .accept("egg:0", new Compostable(Item.EGG, 0, 0.08f, ColorRegistry.color("egg")))
        // Sugar Cane
            .accept("sugar_cane:0", new Compostable(Block.SUGAR_CANE, 0, 0.08f, ColorRegistry.color("sugar_cane")))
        // String
            .accept("string:0", new Compostable(Item.STRING, 0, 0.04f, ColorRegistry.color("white")));
    }
}
