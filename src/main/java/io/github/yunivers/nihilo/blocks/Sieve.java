package io.github.yunivers.nihilo.blocks;

import io.github.yunivers.nihilo.blocks.entity.BarrelEntity;
import io.github.yunivers.nihilo.blocks.entity.SieveEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.template.block.TemplateBlockWithEntity;
import net.modificationstation.stationapi.api.util.Identifier;

public class Sieve extends TemplateBlockWithEntity
{
    public Sieve(Identifier identifier)
    {
        super(identifier, Material.WOOD);
        setHardness(2.0f);
        setResistance(5.0F);
    }

    @Override
    public boolean isOpaque()
    {
        return false;
    }

    @Override
    public boolean isFullCube()
    {
        return false;
    }

    @Override
    protected BlockEntity createBlockEntity() {
        return new SieveEntity();
    }

    @Override
    public boolean onUse(World world, int x, int y, int z, PlayerEntity player)
    {
        SieveEntity entity = (SieveEntity)world.getBlockEntity(x, y, z);
        entity.use(player);
        return true;
    }
}
