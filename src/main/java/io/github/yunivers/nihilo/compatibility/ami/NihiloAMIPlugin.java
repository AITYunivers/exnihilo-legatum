package io.github.yunivers.nihilo.compatibility.ami;

import io.github.yunivers.nihilo.Nihilo;
import io.github.yunivers.nihilo.registries.HammerRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.glasslauncher.mods.alwaysmoreitems.api.*;
import net.minecraft.nbt.NbtCompound;
import net.modificationstation.stationapi.api.util.Identifier;

public class NihiloAMIPlugin implements ModPluginProvider
{
    public static String HammerCategoryUID = "nihilo_hammer";

    public static AMIHelpers helpers;

    @Override
    public String getName() {
        return "Ex Nihilo: Legatum";
    }

    @Override
    public Identifier getId() {
        return Nihilo.NAMESPACE.id("nihilo_ami");
    }

    @Override
    public void onAMIHelpersAvailable(AMIHelpers amiHelpers) {
        helpers = amiHelpers;
    }

    @Override
    public void onItemRegistryAvailable(ItemRegistry itemRegistry) {

    }

    @Override
    public void register(ModRegistry modRegistry) {
        modRegistry.addRecipeCategories(new HammerRecipeCategory());
        modRegistry.addRecipeHandlers(new HammerRecipeHandler());
        modRegistry.addRecipes(HammerRegistry.getSmashables());
    }

    @Override
    public void onRecipeRegistryAvailable(RecipeRegistry recipeRegistry) {

    }

    @Override
    public SyncableRecipe deserializeRecipe(NbtCompound nbtCompound) {
        return null;
    }
}
