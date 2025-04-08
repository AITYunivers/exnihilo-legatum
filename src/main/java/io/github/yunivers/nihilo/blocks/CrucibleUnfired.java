package io.github.yunivers.nihilo.blocks;

import net.minecraft.block.material.Material;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;
import net.modificationstation.stationapi.api.util.Identifier;

public class CrucibleUnfired extends TemplateBlock
{
    public CrucibleUnfired(Identifier identifier) {
        super(identifier, Material.CLAY);
        setHardness(2.0F);
        setResistance(5.0F);
        setSoundGroup(TemplateBlock.STONE_SOUND_GROUP);
    }

    @Override
    public boolean isOpaque() {
        return false;
    }
}
