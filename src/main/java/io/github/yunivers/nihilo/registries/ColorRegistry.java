package io.github.yunivers.nihilo.registries;

import com.mojang.serialization.Lifecycle;
import io.github.yunivers.nihilo.Nihilo;
import io.github.yunivers.nihilo.registries.events.ColorRegistryEvent;
import io.github.yunivers.nihilo.registries.helpers.Color;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.StationAPI;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.mod.entrypoint.EventBusPolicy;
import net.modificationstation.stationapi.api.registry.Registries;
import net.modificationstation.stationapi.api.registry.Registry;
import net.modificationstation.stationapi.api.registry.RegistryKey;
import net.modificationstation.stationapi.api.registry.SimpleRegistry;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.Namespace;

import java.util.Optional;

@Entrypoint(eventBus = @EventBusPolicy(registerInstance = false))
@EventListener(phase = StationAPI.INTERNAL_PHASE)
public class ColorRegistry extends SimpleRegistry<Color>
{
    public static final RegistryKey<Registry<Color>> KEY = RegistryKey.ofRegistry(Nihilo.NAMESPACE.id("colors"));
    public static final ColorRegistry INSTANCE = Registries.create(KEY, new ColorRegistry(), registry -> new Color(1f, 1f, 1f, 1f), Lifecycle.experimental());

    public ColorRegistry() {
        super(KEY, Lifecycle.experimental(), false);
    }

    public static Color color(String name) {
        Optional<Color> color = ColorRegistry.INSTANCE.getOrEmpty(Identifier.of(name));
        return color.orElseGet(() -> ColorRegistry.INSTANCE.get(Identifier.of("white")));
    }

    @EventListener
    public static void registerColors(ColorRegistryEvent event)
    {
        event.register(Namespace.MINECRAFT)
            .accept("white", new Color(1f, 1f, 1f, 1f))
            .accept("black", new Color(0f, 0f, 0f, 1f))

        // Block Colors
            .accept("dirt", new Color(0xEEA96D))
            .accept("gravel", new Color(0xE3E3E3))
            .accept("sand", new Color(0xFFFEA8))
            .accept("dust", new Color(0xFFFEDB))
            .accept("soul_sand", new Color(0x8F5B4D))

        // Saplings
            .accept("oak", new Color(0x35A82A))
            .accept("birch", new Color(0x6CC449))
            .accept("spruce", new Color(0x2E8042))

        // Meats
            .accept("pork_raw", new Color(0xFFA091))
            .accept("pork_cooked", new Color(0xFFFDBD))
            .accept("fish_raw", new Color(0x6DCFB3))
            .accept("fish_cooked", new Color(0xD8EBE5))

        // Fruits/Veggies/Crops
            .accept("apple", new Color(0xFFF68F))
            .accept("pumpkin", new Color(0xFFDB66))
            .accept("wheat", new Color(0xE3E162))
            .accept("bread", new Color(0xD1AF60))
            .accept("cactus", new Color(0xDEFFB5))
            .accept("tall_grass", new Color(0x23630E))
            .accept("egg", new Color(0xFFFA66))
            .accept("sugar_cane", new Color(0x9BFF8A))

        // Flowers/Mushrooms
            .accept("mushroom_brown", new Color(0xCFBFB6))
            .accept("mushroom_red", new Color(0xD6A8A5))
            .accept("dandelion", new Color(0xFFF461))
            .accept("rose", new Color(0xFF1212));
    }
}
