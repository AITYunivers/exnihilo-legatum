package io.github.yunivers.nihilo.events.init;

import io.github.yunivers.nihilo.items.Pebble;
import io.github.yunivers.nihilo.items.tools.Crook;
import io.github.yunivers.nihilo.items.tools.Hammer;
import io.github.yunivers.nihilo.registries.events.ColorRegistryEvent;
import io.github.yunivers.nihilo.registries.events.CompostRegistryEvent;
import io.github.yunivers.nihilo.registries.events.HammerRegistryEvent;
import io.github.yunivers.nihilo.registries.events.SieveRegistryEvent;
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
    public static Item SILK_MESH;
    public static Item HAMMER_WOOD;
    public static Item HAMMER_STONE;
    public static Item HAMMER_IRON;
    public static Item HAMMER_GOLD;
    public static Item HAMMER_DIAMOND;
    public static Item CROOK_WOOD;
    public static Item CROOK_BONE;

    @EventListener
    public void registerItems(ItemRegistryEvent event) {
        PORCELAIN_CLAY = new TemplateItem(NAMESPACE.id("porcelain_clay"))
                .setTranslationKey(NAMESPACE, "porcelain_clay");
        STONE_PEBBLE = new Pebble(NAMESPACE.id("stone_pebble"))
                .setTranslationKey(NAMESPACE, "stone_pebble");
        SILK_MESH = new TemplateItem(NAMESPACE.id("mesh"))
                .setTranslationKey(NAMESPACE, "mesh");

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

        CROOK_WOOD = new Crook(NAMESPACE.id("crook_wood"), ToolMaterial.WOOD)
                .setTranslationKey(NAMESPACE, "crook_wood");
        CROOK_BONE = new Crook(NAMESPACE.id("crook_bone"), ToolMaterial.STONE)
                .setTranslationKey(NAMESPACE, "crook_bone");


        // Call Registries
        StationAPI.EVENT_BUS.post(new ColorRegistryEvent());
        StationAPI.EVENT_BUS.post(new CompostRegistryEvent());
        StationAPI.EVENT_BUS.post(new SieveRegistryEvent());
    }
}
