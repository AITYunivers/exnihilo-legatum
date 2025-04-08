package io.github.yunivers.nihilo.mixin;

import io.github.yunivers.nihilo.Nihilo;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(World.class)
public class WorldMixin
{
    @Inject(
        method = "spawnEntity",
        at = @At("HEAD"),
        cancellable = true
    )
    public void spawnEntity(Entity entity, CallbackInfoReturnable<Boolean> cir)
    {
        if (entity instanceof ItemEntity itemEntity && itemEntity.stack.itemId == Nihilo.IgnoreNextItemEntity)
        {
            cir.cancel();
            Nihilo.IgnoreNextItemEntity = 0;
        }
    }
}
