package io.github.yunivers.nihilo.items;

import io.github.yunivers.nihilo.entity.PebbleEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.template.item.TemplateItem;
import net.modificationstation.stationapi.api.util.Identifier;

public class Pebble extends TemplateItem
{
    public Pebble(Identifier identifier)
    {
        super(identifier);
    }

    public ItemStack use(ItemStack stack, World world, PlayerEntity user) {
        --stack.count;
        world.playSound(user, "random.bow", 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
        if (!world.isRemote) {
            world.spawnEntity(new PebbleEntity(world, user));
        }

        return stack;
    }
}
