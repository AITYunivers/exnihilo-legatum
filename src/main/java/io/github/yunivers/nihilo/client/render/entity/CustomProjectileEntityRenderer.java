package io.github.yunivers.nihilo.client.render.entity;

import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.entity.Entity;
import net.modificationstation.stationapi.api.client.texture.atlas.Atlases;
import net.modificationstation.stationapi.api.util.Identifier;

public class CustomProjectileEntityRenderer extends ProjectileEntityRenderer
{
    private Identifier texture;

    public CustomProjectileEntityRenderer(Identifier texture) {
        super(-1);
        this.texture = texture;
    }

    @Override
    public void render(Entity entity, double x, double y, double z, float yaw, float pitch)
    {
        if (itemTextureId == -1)
            itemTextureId = Atlases.getGuiItems().idToTex.get(texture).index;
        super.render(entity, x, y, z, yaw, pitch);
    }
}
