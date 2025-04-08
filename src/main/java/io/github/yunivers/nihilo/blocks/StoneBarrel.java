package io.github.yunivers.nihilo.blocks;

import net.minecraft.block.material.Material;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;
import net.modificationstation.stationapi.api.util.Identifier;

public class StoneBarrel extends Barrel
{
    public StoneBarrel(Identifier identifier) {
        super(identifier, Material.STONE);
        setHardness(4.0F);
        setResistance(10.0F);
        setSoundGroup(TemplateBlock.STONE_SOUND_GROUP);
    }
}
