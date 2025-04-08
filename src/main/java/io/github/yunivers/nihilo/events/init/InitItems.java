package io.github.yunivers.nihilo.events.init;

import io.github.yunivers.nihilo.items.Pebble;
import io.github.yunivers.nihilo.items.tools.Hammer;
import io.github.yunivers.nihilo.registries.events.ColorRegistryEvent;
import io.github.yunivers.nihilo.registries.events.CompostRegistryEvent;
import io.github.yunivers.nihilo.registries.events.HammerRegistryEvent;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.modificationstation.stationapi.api.StationAPI;
import net.modificationstation.stationapi.api.event.registry.ItemRegistryEvent;
import net.modificationstation.stationapi.api.template.item.TemplateItem;

import static io.github.yunivers.nihilo.Nihilo.NAMESPACE;

public class InitItems
{
    // Items

    public static Item PORCELAIN_CLAY;
    public static Item STONE_PEBBLE;
    public static Item HAMMER_WOOD;
    public static Item HAMMER_STONE;
    public static Item HAMMER_IRON;
    public static Item HAMMER_GOLD;
    public static Item HAMMER_DIAMOND;

    @EventListener
    public void registerItems(ItemRegistryEvent event) {
        PORCELAIN_CLAY = new TemplateItem(NAMESPACE.id("porcelain_clay"))
                .setTranslationKey(NAMESPACE, "porcelain_clay");
        STONE_PEBBLE = new Pebble(NAMESPACE.id("stone_pebble"))
                .setTranslationKey(NAMESPACE, "stone_pebble");

        HAMMER_WOOD = new Hammer(NAMESPACE.id("hammer_wood"), ToolMaterial.WOOD)
                .setTranslationKey(NAMESPACE, "hammer_wood");
        HAMMER_STONE = new Hammer(NAMESPACE.id("hammer_stone"), ToolMaterial.STONE)
                .setTranslationKey(NAMESPACE, "hammer_stone");
        HAMMER_IRON = new Hammer(NAMESPACE.id("hammer_iron"), ToolMaterial.IRON)
                .setTranslationKey(NAMESPACE, "hammer_iron");
        HAMMER_GOLD = new Hammer(NAMESPACE.id("hammer_gold"), ToolMaterial.GOLD)
                .setTranslationKey(NAMESPACE, "hammer_gold");
        HAMMER_DIAMOND = new Hammer(NAMESPACE.id("hammer_diamond"), ToolMaterial.DIAMOND)
                .setTranslationKey(NAMESPACE, "hammer_diamond");


        // Call Registries
        StationAPI.EVENT_BUS.post(new ColorRegistryEvent());
        StationAPI.EVENT_BUS.post(new CompostRegistryEvent());
    }
}
