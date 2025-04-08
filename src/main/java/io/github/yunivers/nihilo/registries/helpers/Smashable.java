package io.github.yunivers.nihilo.registries.helpers;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class Smashable
{
    public Block source;
    private Item item;
    private Block block;
    public float chance;
    public float luckMultiplier;

    public Smashable(Block source, Item item, float chance, float luckMultiplier)
    {
        this.source = source;
        this.item = item;
        this.chance = chance;
        this.luckMultiplier = luckMultiplier;
    }

    public Smashable(Block source, Block block, float chance, float luckMultiplier)
    {
        this.source = source;
        this.block = block;
        this.chance = chance;
        this.luckMultiplier = luckMultiplier;
    }

    public boolean hasItem()
    {
        return item != null || block != null;
    }

    public Item getItem()
    {
        if (item != null)
            return item;
        else
            return Item.BLOCK_ITEMS.get(block);
    }
}
