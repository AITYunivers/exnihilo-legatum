package io.github.yunivers.nihilo.client.render.block.entity;

import io.github.yunivers.nihilo.blocks.entity.SieveEntity;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.modificationstation.stationapi.api.client.StationRenderAPI;
import net.modificationstation.stationapi.api.client.texture.Sprite;
import net.modificationstation.stationapi.api.client.texture.atlas.Atlases;
import org.lwjgl.opengl.GL11;

public class SieveRenderer extends BlockEntityRenderer
{
    public static double PIXEL_SIZE = 0.0625d; // 1/16

    @Override
    public void render(BlockEntity blockEntity, double x, double y, double z, float tickDelta)
    {
        if (blockEntity instanceof SieveEntity sieve)
        {
            if (sieve.slotStack != null && sieve.volume > 0)
            {
                GL11.glPushMatrix();
                GL11.glTranslated(x, y, z);
                GL11.glEnable(GL11.GL_TEXTURE_2D);
                GL11.glEnable(GL11.GL_BLEND);
                GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                StationRenderAPI.getBakedModelManager().getAtlas(Atlases.GAME_ATLAS_TEXTURE).bindTexture();

                renderTop(sieve);
                renderBottom(sieve);

                GL11.glDisable(GL11.GL_BLEND);
                GL11.glPopMatrix();
            }
        }
    }

    private void renderTop(SieveEntity sieve)
    {
        float light = sieve.getBlock().getLuminance(sieve.world, sieve.x, sieve.y + 1, sieve.z);
        Sprite sprite = Atlases.getTerrain().getTexture(Block.BLOCKS[sieve.slotStack.itemId].getTexture(0)).getSprite();
        double height = sieve.volume * 0.2 + 0.7;

        double min = PIXEL_SIZE;
        double max = 1F - min;

        Tessellator t = Tessellator.INSTANCE;
        t.startQuads();
        t.color(light, light,light);
        t.vertex(min, height, min, sprite.getMaxU(), sprite.getMaxV());
        t.vertex(min, height, max, sprite.getMaxU(), sprite.getMinV());
        t.vertex(max, height, max, sprite.getMinU(), sprite.getMinV());
        t.vertex(max, height, min, sprite.getMinU(), sprite.getMaxV());
        t.draw();
    }

    private void renderBottom(SieveEntity sieve)
    {
        float light = sieve.getBlock().getLuminance(sieve.world, sieve.x, sieve.y, sieve.z);
        Sprite sprite = Atlases.getTerrain().getTexture(Block.BLOCKS[sieve.slotStack.getItem().id].getTexture(1)).getSprite();

        double min = PIXEL_SIZE * 1;
        double max = 1F - min;
        double height = PIXEL_SIZE * 11 + 0.01;

        Tessellator t = Tessellator.INSTANCE;
        t.startQuads();
        t.color(light, light,light);
        t.vertex(min, height, min, sprite.getMaxU(), sprite.getMinV());
        t.vertex(max, height, min, sprite.getMinU(), sprite.getMinV());
        t.vertex(max, height, max, sprite.getMinU(), sprite.getMaxV());
        t.vertex(min, height, max, sprite.getMaxU(), sprite.getMaxV());
        t.draw();
    }
}
