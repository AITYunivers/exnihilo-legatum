package io.github.yunivers.nihilo.client.render.block.entity;

import io.github.yunivers.nihilo.blocks.entity.BarrelEntity;
import io.github.yunivers.nihilo.events.init.InitTextures;
import io.github.yunivers.nihilo.util.BarrelState;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.modificationstation.stationapi.api.client.StationRenderAPI;
import net.modificationstation.stationapi.api.client.texture.Sprite;
import net.modificationstation.stationapi.api.client.texture.atlas.Atlases;
import org.lwjgl.opengl.GL11;

public class BarrelRenderer extends BlockEntityRenderer
{
    public double PIXEL_SIZE = 0.0625d; // 1/16

    @Override
    public void render(BlockEntity blockEntity, double x, double y, double z, float tickDelta)
    {
        if (blockEntity instanceof BarrelEntity barrel)
        {
            if (barrel.state != BarrelState.EMPTY)
            {
                GL11.glPushMatrix();
                GL11.glTranslated(x, y, z);
                GL11.glEnable(GL11.GL_TEXTURE_2D);
                GL11.glEnable(GL11.GL_BLEND);
                GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

                float light = barrel.getBlock().getLuminance(blockEntity.world, blockEntity.x, blockEntity.y, blockEntity.z);
                StationRenderAPI.getBakedModelManager().getAtlas(Atlases.GAME_ATLAS_TEXTURE).bindTexture();
                Sprite sprite = Atlases.getTerrain().getTexture(InitTextures.BARREL_COMPOST_TEX).getSprite();;
                double height = 0.9;

                switch (barrel.state)
                {
                    case FLUID:
                        sprite = barrel.fluid.getSprite(1, 0);
                        height = (barrel.fluid.amount / 1000d) * 0.8 + 0.1;
                        break;
                    case COMPOST:
                        height = barrel.compostMaterial * 0.8 + 0.1;
                        break;
                    case DIRT:
                        sprite = Atlases.getTerrain().getTexture(Block.DIRT.textureId).getSprite();
                        break;
                    case CLAY:
                        sprite = Atlases.getTerrain().getTexture(Block.CLAY.textureId).getSprite();
                        break;
                    case NETHERRACK:
                        sprite = Atlases.getTerrain().getTexture(Block.NETHERRACK.textureId).getSprite();
                        break;
                    case SOULSAND:
                        sprite = Atlases.getTerrain().getTexture(Block.SOUL_SAND.textureId).getSprite();
                        break;
                    case OBSIDIAN:
                        sprite = Atlases.getTerrain().getTexture(Block.OBSIDIAN.textureId).getSprite();
                        break;
                    case COBBLESTONE:
                        sprite = Atlases.getTerrain().getTexture(Block.COBBLESTONE.textureId).getSprite();
                        break;
                }

                double min = PIXEL_SIZE * 3;
                double max = 1F - min;

                Tessellator t = Tessellator.INSTANCE;
                t.startQuads();
                t.color(1F * light * barrel.color.r, 1F * light * barrel.color.g, 1F * light * barrel.color.b);
                t.vertex(min, height, min, sprite.getMaxU(), sprite.getMaxV());
                t.vertex(min, height, max, sprite.getMaxU(), sprite.getMinV());
                t.vertex(max, height, max, sprite.getMinU(), sprite.getMinV());
                t.vertex(max, height, min, sprite.getMinU(), sprite.getMaxV());
                t.draw();
                GL11.glDisable(GL11.GL_BLEND);
                GL11.glPopMatrix();
            }
        }
    }
}
