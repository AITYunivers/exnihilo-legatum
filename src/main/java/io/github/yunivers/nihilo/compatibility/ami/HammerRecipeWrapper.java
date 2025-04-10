package io.github.yunivers.nihilo.compatibility.ami;

import io.github.yunivers.nihilo.Nihilo;
import io.github.yunivers.nihilo.items.tools.Hammer;
import io.github.yunivers.nihilo.mixin.RecipesGuiAccessor;
import io.github.yunivers.nihilo.registries.helpers.Smashable;
import net.glasslauncher.mods.alwaysmoreitems.api.gui.AMIDrawable;
import net.glasslauncher.mods.alwaysmoreitems.api.recipe.RecipeWrapper;
import net.glasslauncher.mods.alwaysmoreitems.gui.DrawableHelper;
import net.glasslauncher.mods.alwaysmoreitems.gui.screen.OverlayScreen;
import net.glasslauncher.mods.alwaysmoreitems.recipe.Focus;
import net.glasslauncher.mods.alwaysmoreitems.util.AlwaysMoreItems;
import net.glasslauncher.mods.alwaysmoreitems.util.RecipeGuiLogic;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.util.Rectangle;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class HammerRecipeWrapper implements RecipeWrapper
{
    private final Smashable smashable;
    private static final Rectangle clickArea = new Rectangle(74, 21, 16, 15);
    private static final AMIDrawable focusHighlight = DrawableHelper.createDrawable("assets/nihilo/stationapi/textures/gui/ami/ami_hammer.png", 166, 0, 18, 18);

    public HammerRecipeWrapper(Smashable smashable)
    {
        this.smashable = smashable;
    }

    @Override
    public List<ItemStack> getInputs() {
        return List.of(smashable.getSourceItemStack());
    }

    @Override
    public List<ItemStack> getOutputs() {
        return smashable.getResults();
    }

    @Override
    public void drawInfo(@NotNull Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
        String conditions = smashable.getConditions();
        if (conditions != null && !conditions.isEmpty()) {
            minecraft.textRenderer.drawWithShadow(conditions, 83 - (minecraft.textRenderer.getWidth(conditions) / 2), 31 + 18 + 2, -1);
        }
        ItemStack focus = ((RecipesGuiAccessor) OverlayScreen.INSTANCE.recipesGui).getLogic().getFocus().getStack();
        if (focus == null) {
            return;
        }
        int xOffset = -1;
        int yOffset = 0;
        if (focus.getItem() instanceof BlockItem blockItem && smashable.source == blockItem.getBlock())
            xOffset = 71;
        else
        {
            List<ItemStack> results = smashable.getResults();
            for (int i = 0; i < results.size(); i++)
            {
                if (results.get(i).isItemEqual(focus))
                {
                    xOffset = 18 * i;
                    break;
                }
            }
            yOffset = 33;
        }
        if (xOffset == -1)
            return;
        focusHighlight.draw(minecraft, xOffset, yOffset);
    }

    @Override
    public void drawAnimations(@NotNull Minecraft minecraft, int recipeWidth, int recipeHeight) {

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
