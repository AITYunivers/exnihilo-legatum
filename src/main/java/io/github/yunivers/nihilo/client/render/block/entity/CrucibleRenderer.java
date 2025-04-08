package io.github.yunivers.nihilo.client.render.block.entity;

import io.github.yunivers.nihilo.blocks.entity.CrucibleEntity;
import io.github.yunivers.nihilo.util.CrucibleState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.modificationstation.stationapi.api.client.StationRenderAPI;
import net.modificationstation.stationapi.api.client.texture.Sprite;
import net.modificationstation.stationapi.api.client.texture.atlas.Atlases;
import org.lwjgl.opengl.GL11;

public class CrucibleRenderer extends BlockEntityRenderer
{
    public double PIXEL_SIZE = 0.0625d; // 1/16

    @Override
    public void render(BlockEntity blockEntity, double x, double y, double z, float tickDelta)
    {
        if (blockEntity instanceof CrucibleEntity crucible)
        {
            if (crucible.state != CrucibleState.EMPTY || crucible.solidVolume > 0)
            {
                GL11.glPushMatrix();
                GL11.glTranslated(x, y, z);
                GL11.glEnable(GL11.GL_TEXTURE_2D);
                GL11.glEnable(GL11.GL_BLEND);
                GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                float light = crucible.getBlock().getLuminance(crucible.world, crucible.x, crucible.y, crucible.z);
                StationRenderAPI.getBakedModelManager().getAtlas(Atlases.GAME_ATLAS_TEXTURE).bindTexture();

                if (crucible.fluid != null)
                    renderFluid(crucible, light);

                if (crucible.solidVolume > 0)
                    renderBlock(crucible, light);

                GL11.glDisable(GL11.GL_BLEND);
                GL11.glPopMatrix();
            }
        }
    }

    private void renderFluid(CrucibleEntity crucible, float light)
    {
        Sprite sprite = crucible.fluid.getSprite(1, 0);
        double height = ((double)crucible.fluid.amount / crucible.getCapacity()) * 0.75 + 0.2;

        double min = PIXEL_SIZE * 1;
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

    private void renderBlock(CrucibleEntity crucible, float light)
    {
        Sprite sprite = Atlases.getTerrain().getTexture(crucible.solidData.block.textureId).getSprite();
        double height = (crucible.solidVolume / 8000d) * 0.75 + 0.2;

        double min = PIXEL_SIZE * 1;
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
}
