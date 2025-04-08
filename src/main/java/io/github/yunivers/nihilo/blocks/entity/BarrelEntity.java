package io.github.yunivers.nihilo.blocks.entity;

import io.github.yunivers.nihilo.config.Config;
import io.github.yunivers.nihilo.events.init.InitBlocks;
import io.github.yunivers.nihilo.registries.ColorRegistry;
import io.github.yunivers.nihilo.registries.CompostRegistry;
import io.github.yunivers.nihilo.registries.helpers.Color;
import io.github.yunivers.nihilo.registries.helpers.Compostable;
import io.github.yunivers.nihilo.util.BarrelState;
import io.github.yunivers.stationfluidapi.api.FluidStack;
import net.minecraft.block.Block;
import net.minecraft.block.LiquidBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class BarrelEntity extends BlockEntity implements Inventory
{
    public ItemStack slotStack;
    public double compostMaterial;
    public FluidStack fluid;
    public Color color = ColorRegistry.color("white");
    public Color colorBase;
    public BarrelState state = BarrelState.EMPTY;
    private int timer;

    @Override
    public void tick() {
        super.tick();

        switch (state)
        {
            case EMPTY:
                // Handle Rain
                if (!world.isRemote && world.isRaining(x, y + 1, z))
                {
                    fluid = new FluidStack((LiquidBlock)Block.WATER, 0);
                    state = BarrelState.FLUID;
                }
                timer = 0;
                break;
            case FLUID:
                // Water
                if (fluid.fluid == Block.WATER)
                {
                    timer++;

                    //Handle Rain
                    if (!world.isRemote && timer > 1 && fluid.amount < 1000 && world.isRaining(x, y + 1, z))
                    {
                        fluid.amount += Config.config.rainFillRate;

                        if (fluid.amount > 1000)
                            fluid.amount = 1000;

                        timer = 0;
                    }

                    //Turn into cobblestone?
                    int topBlockId = world.getBlockId(x, y + 1, z);
                    if (fluid.amount >= 1000 && (topBlockId == Block.LAVA.id || topBlockId == Block.FLOWING_LAVA.id))
                    {
                        state = BarrelState.COBBLESTONE;
                        setStack(0, new ItemStack(Block.COBBLESTONE, 1));
                    }
                }
                else if (fluid.fluid == Block.LAVA)
                {
                    // Check if barrel is flammable
                    if (Block.BLOCKS[world.getBlockId(x, y, z)].material.isBurnable())
                    {
                        timer++;
                        if (timer % 30 == 0)
                            world.addParticle("largesmoke", x + Math.random(), y + 1.2d, z + Math.random(), 0, 0, 0);

                        if (timer % 5 == 0)
                            world.addParticle("smoke", x + Math.random(), y + 1.2d, z + Math.random(), 0, 0, 0);

                        if (timer >= Config.config.barrelBurnRate)
                        {
                            timer = 0;
                            if (fluid.amount < 1000)
                            {
                                //burn
                                world.setBlock(x, y + 2, z, Block.FIRE.id);
                                return;
                            }
                            else
                            {
                                //spit lava on the ground
                                world.setBlock(x, y, z, Block.FLOWING_LAVA.id);
                                return;
                            }
                        }
                    }

                    //Turn into obsidian?
                    int topBlockId = world.getBlockId(x, y + 1, z);
                    if (fluid.amount >= 1000 && (topBlockId == Block.WATER.id || topBlockId == Block.FLOWING_WATER.id))
                    {
                        state = BarrelState.OBSIDIAN;
                        setStack(0, new ItemStack(Block.OBSIDIAN, 1));
                    }
                }
                break;
            case COMPOST:
                if (compostMaterial >= 1.0F)
                {
                    timer++;

                    //Change color
                    Color colorDirt = ColorRegistry.color("dirt");
                    color = Color.average(colorBase, colorDirt, (float)timer / (float)Config.config.compostTime);

                    //Are we done yet?
                    if(timer >= Config.config.compostTime)
                    {
                        state = BarrelState.DIRT;
                        timer = 0;
                        color = ColorRegistry.color("white");
                        setStack(0, new ItemStack(Block.DIRT, 1));
                    }
                }
                break;
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
            slotStack = stack;
        }
    }

    @Override
    public String getName() {
        return "Barrel";
    }

    @Override
    public int getMaxCountPerStack() {
        return 1;
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return true;
    }

    public boolean tryCompost(ItemStack stack)
    {
        if ((state == BarrelState.EMPTY || state == BarrelState.COMPOST) && compostMaterial < 1.0d && CompostRegistry.containsItem(stack.getItem(), stack.getDamage()))
        {
            @NotNull Compostable compostable = Objects.requireNonNull(CompostRegistry.getItem(stack.getItem(), stack.getDamage()));
            compostMaterial = Math.min(compostMaterial + compostable.value, 1d);
            stack.split(1);
            if (compostMaterial == compostable.value)
                color = compostable.color;
            else
            {
                //Calculate the average of the colors
                float weightA = (float)(compostable.value / compostMaterial);
                float weightB = 1.0f - weightA;

                float r = weightA * compostable.color.r + weightB * color.r;
                float g = weightA * compostable.color.g + weightB * color.g;
                float b = weightA * compostable.color.b + weightB * color.b;
                float a = weightA * compostable.color.a + weightB * color.a;

                color = new Color(r,g,b,a);
            }

            if (compostMaterial == 1.0d)
                colorBase = color;

            state = BarrelState.COMPOST;
            return true;
        }
        return false;
    }

    public boolean tryExtractByHand()
    {
        if (getStack(0) != null)
        {
            ItemEntity itemEntity = new ItemEntity(world, x, y + 1, z, removeStack(0, 1));
            itemEntity.velocityY = 0.2F;
            world.spawnEntity(itemEntity);
            state = BarrelState.EMPTY;
            return true;
        }
        return false;
    }

    public boolean tryBucket(PlayerEntity player, ItemStack heldItem)
    {
        if (state == BarrelState.FLUID && heldItem.getItem().id == Item.BUCKET.id && fluid.amount >= 1000)
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
            fluid = null;
            state = BarrelState.EMPTY;
            return true;
        }
        else if (state == BarrelState.EMPTY && (heldItem.getItem().id == Item.WATER_BUCKET.id || heldItem.getItem().id == Item.LAVA_BUCKET.id))
        {
            if (heldItem.count == 1)
            {
                if (heldItem.getItem() instanceof BucketItem && heldItem.itemId != Item.BUCKET.id)
                    player.inventory.setStack(player.inventory.selectedSlot, new ItemStack(Item.BUCKET));
            }
            else
                player.inventory.setStack(player.inventory.selectedSlot, heldItem.split(1));

            fluid = new FluidStack(heldItem);
            state = BarrelState.FLUID;
            return true;
        }
        return false;
    }

    public boolean tryCustomRecipe(PlayerEntity player, ItemStack heldItem)
    {
        if (fluid.amount >= 1000)
        {
            if (fluid.fluid == Block.WATER)
            {
                if (heldItem.itemId == Item.BLOCK_ITEMS.get(InitBlocks.DUST).id);
                {
                    heldItem.count -= 1;
                    ItemEntity itemEntity = new ItemEntity(world, x, y + 1, z, new ItemStack(Block.CLAY, 1));
                    itemEntity.velocityY = 0.2F;
                    world.spawnEntity(itemEntity);
                    fluid.amount -= 1000;
                    if (fluid.amount <= 0)
                    {
                        state = BarrelState.EMPTY;
                        fluid = null;
                    }
                    return true;
                }
            }
        }
        return false;
    }
}
