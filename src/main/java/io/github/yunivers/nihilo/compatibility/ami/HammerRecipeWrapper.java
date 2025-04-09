package io.github.yunivers.nihilo.compatibility.ami;

import io.github.yunivers.nihilo.Nihilo;
import io.github.yunivers.nihilo.items.tools.Hammer;
import io.github.yunivers.nihilo.registries.helpers.Smashable;
import net.glasslauncher.mods.alwaysmoreitems.api.recipe.RecipeWrapper;
import net.glasslauncher.mods.alwaysmoreitems.util.AlwaysMoreItems;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.util.Rectangle;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class HammerRecipeWrapper implements RecipeWrapper
{
    private final Smashable smashable;
    private static Rectangle clickArea = new Rectangle(74, 21, 16, 15);

    public HammerRecipeWrapper(Smashable smashable)
    {
        this.smashable = smashable;
    }

    @Override
    public List<Block> getInputs() {
        return List.of(smashable.source);
    }

    @Override
    public List<Item> getOutputs() {
        return List.of(smashable.getItem());
    }

    @Override
    public void drawInfo(@NotNull Minecraft minecraft, int i, int i1, int i2, int i3) {

    }

    @Override
    public void drawAnimations(@NotNull Minecraft minecraft, int i, int i1) {

    }

    @Override
    public @Nullable ArrayList<Object> getTooltip(int mouseX, int mouseY) {
        return null;
    }

    @Override
    public boolean handleClick(@NotNull Minecraft minecraft, int mouseX, int mouseY, int mouseButton) {
        if (clickArea.contains(mouseX, mouseY))
        {
            Nihilo.LOGGER.info("haha, no, fuck you.");
        }
        return false;
    }
}
