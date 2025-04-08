package io.github.yunivers.nihilo.mixin;

import io.github.yunivers.nihilo.events.init.InitItems;
import io.github.yunivers.nihilo.items.tools.Crook;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin
{
    @Inject(
        method = "attack",
        at = @At("HEAD"),
        cancellable = true
    )
    public void attack(Entity target, CallbackInfo ci)
    {
        PlayerEntity player = (PlayerEntity)(Object)this;
        ItemStack heldItem = player.inventory.getSelectedItem();
        if (heldItem.getItem() instanceof Crook crook)
        {
            crook.onLeftClickEntity(heldItem, player, target);
            ci.cancel();
        }
    }
}
