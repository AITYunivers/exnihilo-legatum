package io.github.yunivers.nihilo.registries.helpers;

import net.minecraft.block.Block;

public class HeatSource
{
    public Block block;
    public int meta;
    public float value;

    public HeatSource(Block block, int meta, float value)
    {
        this.block = block;
        this.meta = meta;
        this.value = value;
    }
}
