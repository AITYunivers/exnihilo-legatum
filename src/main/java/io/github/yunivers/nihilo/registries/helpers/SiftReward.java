package io.github.yunivers.nihilo.registries.helpers;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class SiftReward
{
    public Block source;
    private Item item;
    private Block block;
    public int meta;
    public int rarity;

    public SiftReward(Block source, Item item, int rarity)
    {
        this.source = source;
        this.item = item;
        this.rarity = calculateRarity(rarity);
    }

    public SiftReward(Block source, Item item, int meta, int rarity)
    {
        this.source = source;
        this.item = item;
        this.meta = meta;
        this.rarity = calculateRarity(rarity);
    }

    public SiftReward(Block source, Block block, int rarity)
    {
        this.source = source;
        this.block = block;
        this.rarity = calculateRarity(rarity);
    }

    private static int calculateRarity(int base)
    {
        /*int multiplier = ModData.SIEVE_PAIN_MULTIPLIER + 1;
        int rarity = (base * multiplier) + (int)((float)multiplier / 2.0f);*/

        return base; //rarity;
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
