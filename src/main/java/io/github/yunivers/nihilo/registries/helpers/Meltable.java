package io.github.yunivers.nihilo.registries.helpers;

import net.minecraft.block.Block;
import net.minecraft.block.LiquidBlock;

public class Meltable
{
    public Block block;
    public int meta;
    public float solidVolume;
    public LiquidBlock fluid;
    public float fluidVolume;
    public Block appearance;

    public Meltable(Block block, int meta, float solidAmount, LiquidBlock fluid, float fluidAmount, Block appearance)
    {
        this.block = block;
        this.meta = meta;
        this.solidVolume = solidAmount;
        this.fluid = fluid;
        this.fluidVolume = fluidAmount;

        this.appearance = appearance;
    }
}
