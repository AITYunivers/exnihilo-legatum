package io.github.yunivers.nihilo.compatibility.ami;

import io.github.yunivers.nihilo.registries.helpers.Smashable;
import net.glasslauncher.mods.alwaysmoreitems.api.recipe.RecipeHandler;
import net.glasslauncher.mods.alwaysmoreitems.api.recipe.RecipeWrapper;
import org.jetbrains.annotations.NotNull;

public class HammerRecipeHandler implements RecipeHandler<Smashable>
{
    @Override
    public Class<Smashable> getRecipeClass() {
        return Smashable.class;
    }

    @Override
    public @NotNull String getRecipeCategoryUid() {
        return NihiloAMIPlugin.HammerCategoryUID;
    }

    @Override
    public @NotNull RecipeWrapper getRecipeWrapper(@NotNull Smashable recipe) {
        return new HammerRecipeWrapper(recipe);
    }

    @Override
    public boolean isRecipeValid(@NotNull Smashable recipe) {
        return recipe.hasItem();
    }
}
