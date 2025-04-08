package io.github.yunivers.nihilo.blocks.entity;

import io.github.yunivers.nihilo.registries.SieveRegistry;
import io.github.yunivers.nihilo.registries.helpers.SiftReward;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Iterator;

public class SieveEntity extends BlockEntity implements Inventory
{
    public ItemStack slotStack;
    public double volume;
    private int clicks; // How DARE you auto-click?!?!?!
    private int timer;

    @Override
    public void tick()
    {
        timer++;
        if (timer >= 20)
        {
            clicks = 0;
            timer = 0;
        }
    }

    public void use(PlayerEntity player)
    {
        if (slotStack == null)
        {
            ItemStack heldItem = player.inventory.getSelectedItem();
            if (heldItem != null && SieveRegistry.containsItem(heldItem.getItem()))
            {
                slotStack = heldItem.split(1);
                volume = 1d;
            }
        }
        else if (volume > 0)
        {
            clicks++;
            if (clicks <= 6)
                volume -= 0.075;

            if (volume <= 0)
            {
                Item item = slotStack.getItem();
                slotStack = null;
                if (!world.isRemote)
                {
                    ArrayList<SiftReward> rewards = SieveRegistry.getRewards(item);
                    if (!rewards.isEmpty())
                    {
                        for (SiftReward reward : rewards)
                        {
                            if (world.random.nextInt(reward.rarity) == 0)
                            {
                                ItemEntity itemEntity = new ItemEntity(world, (double) x + 0.5D, (double) y + 1.5D, (double) z + 0.5D, new ItemStack(reward.getItem(), 1, reward.meta));

                                double f3 = 0.05F;
                                itemEntity.velocityX = world.random.nextGaussian() * f3;
                                itemEntity.velocityY = (0.2d);
                                itemEntity.velocityZ = world.random.nextGaussian() * f3;

                                world.spawnEntity(itemEntity);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public int size() {
        return 1;
    }

    @Override
    public ItemStack getStack(int slot) {
        if (slot == 0)
            return slotStack;
        return null;
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        if (slot == 0 && slotStack != null)
        {
            if (slotStack.count <= amount) {
                ItemStack stack = slotStack;
                slotStack = null;
                return stack;
            }
            else {
                ItemStack stack = slotStack.split(amount);
                if (slotStack.count == 0)
                    slotStack = null;
                return stack;
            }
        }
        return null;
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        if (slot == 0) {
            slotStack = stack.split(1);
        }
    }

    @Override
    public String getName()
    {
        return "Sieve";
    }

    @Override
    public int getMaxCountPerStack()
    {
        return 1;
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player)
    {
        return true;
    }
}
