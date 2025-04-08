package io.github.yunivers.nihilo.items.tools;

import io.github.yunivers.nihilo.registries.HammerRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.LeavesBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.item.UseOnEntityFirst;
import net.modificationstation.stationapi.api.template.item.TemplateToolItem;
import net.modificationstation.stationapi.api.util.Identifier;

public class Crook extends TemplateToolItem implements UseOnEntityFirst
{
    public Crook(Identifier identifier, ToolMaterial arg)
    {
        super(identifier, 0, arg, new Block[0]);

        this.setMaxDamage((int)(this.getMaxDamage() * (arg == ToolMaterial.STONE ? 3.1d : 3d)));
    }

    @Override
    public boolean isSuitableFor(Block block)
    {
        return block instanceof LeavesBlock;
    }

    @Override
    public float getMiningSpeedMultiplier(ItemStack stack, Block block)
    {
        if (block instanceof LeavesBlock)
            return this.miningSpeed + 1;

        return 1;
    }
    @Override
    public boolean postMine(ItemStack stack, int blockId, int x, int y, int z, LivingEntity miner)
    {
        World world = miner.world;
        Block block = Block.BLOCKS[world.getBlockId(x, y, z)];
        int meta = world.getBlockMeta(x, y, z);
        boolean validTarget = false;

        if (block instanceof LeavesBlock leaves)
        {
            if (!world.isRemote)
            {
                // Call it once here and it gets called again when it breaks.
                block.dropStacks(world, x, y, z, meta);
            }

            validTarget = true;
        }

        if (validTarget)
        {
            stack.damage(1, miner);
        }

        return super.postMine(stack, blockId, x, y, z, miner);
    }

    public void onLeftClickEntity(ItemStack item, PlayerEntity player, Entity target)
    {
        if (!player.world.isRemote)
        {
            double distance = Math.sqrt(Math.pow(player.x - target.x, 2) + Math.pow(player.z - target.z, 2));

            double scalarX = (player.x - target.x) / distance;
            double scalarZ = (player.z - target.z) / distance;

            double velX = 0 - scalarX * 1.5d;
            double velZ = 0 - scalarZ * 1.5d;
            double velY = 0; //- (player.posY - target.posY);

            target.addVelocity(velX, velY, velZ);
        }

        item.damage(1, player);
    }

    @Override
    public boolean onUseOnEntityFirst(ItemStack stack, PlayerEntity player, World world, Entity entity)
    {
        double distance = Math.sqrt(Math.pow(player.x - entity.x, 2) + Math.pow(player.z - entity.z, 2));

        double scalarX = (player.x - entity.x) / distance;
        double scalarZ = (player.z - entity.z) / distance;

        double velX = scalarX * 1.5d;
        double velZ = scalarZ * 1.5d;
        double velY = 0; //- (player.posY - entity.posY);

        entity.addVelocity(velX, velY, velZ);

        stack.damage(1, player);
        return true;
    }
}
