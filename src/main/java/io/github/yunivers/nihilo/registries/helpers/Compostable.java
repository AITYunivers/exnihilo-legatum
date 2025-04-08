package io.github.yunivers.nihilo.registries.helpers;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import org.jetbrains.annotations.Nullable;

public class Compostable
{
    public String unlocalizedName;
    private Item item = null;
    private Block block = null;
    public int meta;
    public float value;
    public Color color;

    public Compostable(Item item, int meta, float value, Color color)
    {
        this.item = item;
        this.meta = meta;
        this.value = value;
        this.color = color;
    }

    public Compostable(Block block, int meta, float value, Color color)
    {
        this.block = block;
        this.meta = meta;
        this.value = value;
        this.color = color;
    }

    public Item getItem()
    {
        if (item != null)
            return item;
        else
            return Item.BLOCK_ITEMS.get(block);
    }
}
