package io.github.yunivers.nihilo.registries.helpers;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Smashable
{
    public final Block source;
    private final ItemStack result;
    public final float chance;
    public final float luckMultiplier;

    public Smashable(Block source, ItemStack result, float chance, float luckMultiplier)
    {
        this.source = source;
        this.result = result;
        this.chance = chance;
        this.luckMultiplier = luckMultiplier;
    }

    public boolean hasOutput()
    {
        return result != null;
    }

    public Item getItem()
    {
        return result.getItem();
    }
}
