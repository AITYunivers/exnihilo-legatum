package io.github.yunivers.nihilo.events.init;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.client.event.texture.TextureRegisterEvent;
import net.modificationstation.stationapi.api.client.texture.atlas.Atlases;
import net.modificationstation.stationapi.api.client.texture.atlas.ExpandableAtlas;

import static io.github.yunivers.nihilo.Nihilo.NAMESPACE;

public class InitTextures
{
    // Textures

    public static int BARREL_COMPOST_TEX;
    public static int DUST;
    public static int STONE_PEBBEL;

    @EventListener
    public void registerTextures(TextureRegisterEvent event) {
        ExpandableAtlas terrainAtlas = Atlases.getTerrain();
        ExpandableAtlas guiItemsAtlas = Atlases.getGuiItems();

        BARREL_COMPOST_TEX = terrainAtlas.addTexture(NAMESPACE.id("block/barrel_compost")).index;
        DUST = guiItemsAtlas.addTexture(NAMESPACE.id("block/dust")).index;
        STONE_PEBBEL = guiItemsAtlas.addTexture(NAMESPACE.id("item/stone_pebble")).index;
    }
}
