package io.github.yunivers.nihilo.items.tools;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import io.github.yunivers.nihilo.Nihilo;
import io.github.yunivers.nihilo.registries.HammerRegistry;
import io.github.yunivers.nihilo.registries.helpers.Smashable;
import net.minecraft.block.Block;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.math.Box;
import net.modificationstation.stationapi.api.template.item.TemplateToolItem;
import net.modificationstation.stationapi.api.util.Identifier;

public class Hammer extends TemplateToolItem
{
    public Hammer(Identifier identifier, ToolMaterial toolMaterial)
    {
        super(identifier, 3, toolMaterial, new Block[0]);
    }

    @Override
    public boolean isSuitableFor(Block block) {
        Block[] blocks = HammerRegistry.getBlocks();

        for (int i = 0; i < blocks.length; ++i)
            if (blocks[i] == block)
                return true;

        return false;
    }

    @Override
    public float getMiningSpeedMultiplier(ItemStack stack, Block block) {
        Block[] blocks = HammerRegistry.getBlocks();

        for (int i = 0; i < blocks.length; ++i)
            if (blocks[i] == block)
                return this.miningSpeed * 0.75f;

        return 0.8f;
    }

    @Override
    public boolean postMine(ItemStack stack, int blockId, int x, int y, int z, LivingEntity miner) {
        if (HammerRegistry.containsBlock(Block.BLOCKS[blockId]))
        {
            ArrayList<Smashable> rewards = HammerRegistry.getRewards(Block.BLOCKS[blockId]);
            if (!rewards.isEmpty()) {
                Nihilo.IgnoreNextItemEntity = blockId;
                for (Smashable reward : rewards) {
                    if (!miner.world.isRemote && miner.world.random.nextFloat() <= reward.chance) {
                        ItemEntity newItemEntity = new ItemEntity(miner.world, (double) x + 0.5D, (double) y + 0.5D, (double) z + 0.5D, new ItemStack(reward.getItem(), 1));

                        double f3 = 0.05F;
                        newItemEntity.velocityX = miner.world.random.nextGaussian() * f3;
                        newItemEntity.velocityY = 0.2d;
                        newItemEntity.velocityZ = miner.world.random.nextGaussian() * f3;
                        newItemEntity.pickupDelay = 10;

                        miner.world.spawnEntity(newItemEntity);
                    }
                }
            }
        }

        return super.postMine(stack, blockId, x, y, z, miner);
    }
}
