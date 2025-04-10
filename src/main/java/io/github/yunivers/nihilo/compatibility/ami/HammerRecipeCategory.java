package io.github.yunivers.nihilo.compatibility.ami;

import io.github.yunivers.nihilo.registries.helpers.Smashable;
import net.glasslauncher.mods.alwaysmoreitems.api.gui.AMIDrawable;
import net.glasslauncher.mods.alwaysmoreitems.api.gui.GuiItemStackGroup;
import net.glasslauncher.mods.alwaysmoreitems.api.gui.RecipeLayout;
import net.glasslauncher.mods.alwaysmoreitems.api.recipe.RecipeCategory;
import net.glasslauncher.mods.alwaysmoreitems.api.recipe.RecipeWrapper;
import net.glasslauncher.mods.alwaysmoreitems.gui.DrawableHelper;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resource.language.TranslationStorage;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class HammerRecipeCategory implements RecipeCategory
{
    private final String recipeTitle;

    public HammerRecipeCategory()
    {
        recipeTitle = TranslationStorage.getInstance().get("gui.nihilo.category.hammer");
    }

    @Override
    public @NotNull String getUid() {
        return NihiloAMIPlugin.HammerCategoryUID;
    }

    @Override
    public @NotNull String getTitle() {
        return recipeTitle;
    }

    @Override
    public @NotNull AMIDrawable getBackground() {
        return DrawableHelper.createDrawable("assets/nihilo/stationapi/textures/gui/ami/ami_hammer.png", 2, 3, 160, 54);
    }

    @Override
    public void drawExtras(Minecraft minecraft) {
        // TODO: Render focus
    }

    @Override
    public void drawAnimations(Minecraft minecraft) {

    }

    @Override
    public void setRecipe(@NotNull RecipeLayout recipeLayout, @NotNull RecipeWrapper recipeWrapper) {
        if (recipeWrapper instanceof HammerRecipeWrapper hammerWrapper)
        {
            GuiItemStackGroup itemStacks = recipeLayout.getItemStacks();
            itemStacks.init(0, true, 71, 0);

            int x = 0;
            for (int i = 0; i < hammerWrapper.getOutputs().size(); i++)
            {
                itemStacks.init(i + 1, false, x, 33);
                x += 18;
            }

            itemStacks.set(0, hammerWrapper.getInputs().get(0));
            for (int i = 0; i < hammerWrapper.getOutputs().size(); i++)
                itemStacks.set(i + 1, hammerWrapper.getOutputs().get(i));
        }
    }
}
