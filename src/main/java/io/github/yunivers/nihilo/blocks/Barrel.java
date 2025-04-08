package io.github.yunivers.nihilo.blocks;

import io.github.yunivers.nihilo.blocks.entity.BarrelEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;
import net.modificationstation.stationapi.api.template.block.TemplateBlockWithEntity;
import net.modificationstation.stationapi.api.util.Identifier;

public class Barrel extends TemplateBlockWithEntity
{
    public Barrel(Identifier identifier) {
        this(identifier, Material.WOOD);
        setHardness(2.0F);
        setResistance(5.0F);
        setSoundGroup(TemplateBlock.WOOD_SOUND_GROUP);
    }

    public Barrel(Identifier identifier, Material material)
    {
        super(identifier, material);
        setBoundingBox(0.125F, 0, 0.125F, 0.875F, 1, 0.875F); // 2/16
    }

    @Override
    public boolean isOpaque() {
        return false;
    }

    @Override
    public boolean isFullCube() {
        return false;
    }

    @Override
    protected BlockEntity createBlockEntity() {
        return new BarrelEntity();
    }

    @Override
    public boolean onUse(World world, int x, int y, int z, PlayerEntity player)
    {
        BarrelEntity entity = (BarrelEntity)world.getBlockEntity(x, y, z);
        ItemStack heldItem = player.inventory.getSelectedItem();
        if (heldItem != null) {
            if (entity.tryCompost(heldItem))
                return true;
            if (entity.tryBucket(player, heldItem))
                return true;
            if (entity.tryCustomRecipe(player, heldItem))
                return true;
        }
        if (entity.tryExtractByHand())
            return true;
        return true;
    }
}
