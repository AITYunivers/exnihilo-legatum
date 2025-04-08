package io.github.yunivers.nihilo.blocks;

import io.github.yunivers.nihilo.blocks.entity.CrucibleEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;
import net.modificationstation.stationapi.api.template.block.TemplateBlockWithEntity;
import net.modificationstation.stationapi.api.util.Identifier;

public class Crucible extends TemplateBlockWithEntity
{
    public Crucible(Identifier identifier) {
        super(identifier, Material.STONE);
        setHardness(2.0F);
        setResistance(5.0F);
        setSoundGroup(TemplateBlock.STONE_SOUND_GROUP);
    }

    @Override
    public boolean isOpaque() {
        return false;
    }

    @Override
    protected BlockEntity createBlockEntity() {
        return new CrucibleEntity();
    }

    @Override
    public boolean onUse(World world, int x, int y, int z, PlayerEntity player)
    {
        CrucibleEntity entity = (CrucibleEntity)world.getBlockEntity(x, y, z);
        ItemStack heldItem = player.inventory.getSelectedItem();
        if (heldItem != null) {
            entity.itemUse(player, heldItem);
        }
        return true;
    }
}
