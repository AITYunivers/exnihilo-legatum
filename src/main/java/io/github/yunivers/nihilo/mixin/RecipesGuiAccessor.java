package io.github.yunivers.nihilo.mixin;

import net.glasslauncher.mods.alwaysmoreitems.gui.screen.RecipesGui;
import net.glasslauncher.mods.alwaysmoreitems.util.RecipeGuiLogic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = RecipesGui.class, remap = false)
public interface RecipesGuiAccessor {

    @Accessor
    RecipeGuiLogic getLogic();
}
