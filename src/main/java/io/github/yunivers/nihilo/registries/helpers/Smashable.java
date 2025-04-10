package io.github.yunivers.nihilo.registries.helpers;

import lombok.Getter;
import net.minecraft.block.Block;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Smashable
{
    public final Block source;
    @Getter
    private final ItemStack sourceItemStack;
    @Getter
    public final List<ItemStack> results;
    public final float chance;
    public final float luckMultiplier;

    public Smashable(Block source, ItemStack result, float chance, float luckMultiplier)
    {
        this.source = source;
        sourceItemStack = new ItemStack(source);
        this.results = List.of(result);
        this.chance = chance;
        this.luckMultiplier = luckMultiplier;
    }

    public Smashable(Block source, List<ItemStack> results, float chance, float luckMultiplier)
    {
        this.source = source;
        sourceItemStack = new ItemStack(source);
        this.results = results;
        this.chance = chance;
        this.luckMultiplier = luckMultiplier;
    }

    public boolean isValid()
    {
        return results != null && !results.isEmpty();
    }

    /**
     * Override to make this recipe conditional.
     */
    public boolean check(LivingEntity entity, int x, int y, int z) {
        return true;
    }

    /**
     * Override to show some extra text under the recipe explaining its conditions.
     */
    public @Nullable String getConditions() {
        return null;
    }
}
