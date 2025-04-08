package io.github.yunivers.nihilo.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.world.BlockView;
import net.modificationstation.stationapi.api.client.texture.atlas.Atlases;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;
import net.modificationstation.stationapi.api.util.Identifier;

public class TemplateTextureBlock extends TemplateBlock
{
    private final Identifier textureIdentifier;

    public TemplateTextureBlock(Identifier identifier, Material material, Identifier textureIdentifier)
    {
        super(identifier, material);
        this.textureIdentifier = textureIdentifier;
    }

    @Override
    public int getTexture(int side)
    {
        if (Atlases.getTerrain() == null)
            return 0;
        return Atlases.getTerrain().addTexture(textureIdentifier).index;
    }
}
