package io.github.yunivers.nihilo.blocks.entity;

import io.github.yunivers.nihilo.config.Config;
import io.github.yunivers.nihilo.registries.CrucibleRegistry;
import io.github.yunivers.nihilo.registries.HeatRegistry;
import io.github.yunivers.nihilo.registries.helpers.Meltable;
import io.github.yunivers.nihilo.util.CrucibleState;
import io.github.yunivers.stationfluidapi.api.FluidStack;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.Objects;

public class CrucibleEntity extends BlockEntity implements Inventory
{
    public Meltable solidData;
    public float solidVolume;
    public float fluidVolume;
    public float expectedFluidVolume;
    public FluidStack fluid;
    public CrucibleState state = CrucibleState.EMPTY;

    @Override
    public void tick() {
        super.tick();

        if (state == CrucibleState.EMPTY && fluid == null && solidData == null)
            return;

        if (fluid == null && solidData != null) {
            fluid = new FluidStack(solidData.fluid, 0);
        }
        else if (fluid == null)
            return;

        float speed = this.getMeltSpeed() * 9f * Config.config.crucibleMeltingRate;

        if (this.solidVolume > 0)
        {
            if (this.solidVolume - speed >= 0)
            {
                this.solidVolume -= speed;
                this.fluidVolume += speed / (solidData.solidVolume / solidData.fluidVolume);
                this.fluid.amount = (int)Math.ceil(fluidVolume);
            }
            else
            {
                this.solidVolume = 0;
                this.fluidVolume = fluid.amount = (int)expectedFluidVolume;
            }
        }
        else if (fluid.amount == 0)
        {
            state = CrucibleState.EMPTY;
            fluid = null;
            solidData = null;
        }
    }

    public float getMeltSpeed()
    {
        Block targetBlock = Block.BLOCKS[world.getBlockId(x, y - 1, z)];
        int targetMeta = world.getBlockMeta(x, y - 1, z);

        if (HeatRegistry.containsBlock(targetBlock, targetMeta))
            return Objects.requireNonNull(HeatRegistry.getBlock(targetBlock, targetMeta)).value;

        return 0.0f;
    }

    @Override
    public int size() {
        return 1;
    }

    @Override
    public ItemStack getStack(int slot) {
        if (slot == 0)
            return new ItemStack(solidData.block, (int)Math.ceil(solidVolume / 1000d));
        return null;
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        return null;
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        if (slot == 0 && CrucibleRegistry.containsItem(stack.getItem(), stack.getDamage()))
        {
            float availableSpace = 8000 - solidVolume;
            Meltable meltable = CrucibleRegistry.getItem(stack.getItem(), stack.getDamage());
            if (availableSpace >= Objects.requireNonNull(meltable).solidVolume)
            {
                stack.count--;
                solidVolume += (int)meltable.solidVolume;
                expectedFluidVolume += (int)meltable.fluidVolume;
                solidData = meltable;
                state = CrucibleState.USED;
            }

            if (stack.count < 0)
                stack = null;
        }
    }

    @Override
    public String getName() {
        return "Crucible";
    }

    @Override
    public int getMaxCountPerStack() {
        return 64;
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return true;
    }

    public int getCapacity() {
        return 10000;
    }

    public void itemUse(PlayerEntity player, ItemStack heldItem) {
        if (state == CrucibleState.USED && heldItem.getItem().id == Item.BUCKET.id && fluid.amount >= 1000)
        {
            ItemStack stack = new ItemStack(Item.ITEMS[fluid.getBucketId()]);
            if (heldItem.count == 1)
                player.inventory.setStack(player.inventory.selectedSlot, stack);
            else
            {
                player.inventory.setStack(player.inventory.selectedSlot, stack.split(1));
                if (!player.inventory.addStack(stack))
                    player.dropItem(stack);
            }
            fluidVolume -= 1000;
            fluid.amount = (int)Math.ceil(fluidVolume);
            if (fluid.amount == 0) {
                fluid = null;
                state = CrucibleState.EMPTY;
            }
        }
        else if (heldItem.getItem() instanceof BucketItem bucket && bucket.id != Item.BUCKET.id && (fluid == null || fluid.amount < getCapacity()))
        {
            if (fluid != null && !fluid.isFluidEqual(heldItem))
                return;

            if (heldItem.count == 1)
                player.inventory.setStack(player.inventory.selectedSlot, new ItemStack(Item.BUCKET));
            else
                player.inventory.setStack(player.inventory.selectedSlot, heldItem.split(1));

            if (fluid == null || state == CrucibleState.EMPTY) {
                fluid = new FluidStack(heldItem);
                fluidVolume = fluid.amount;
                state = CrucibleState.USED;
            }
            else
            {
                fluidVolume += 1000;
                fluid.amount = (int)Math.ceil(fluidVolume);
            }
        }
        else if (CrucibleRegistry.containsItem(heldItem.getItem(), heldItem.getDamage()))
            setStack(0, heldItem);
    }
}
