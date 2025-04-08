package io.github.yunivers.nihilo.events;

import io.github.yunivers.nihilo.entity.PebbleEntity;
import io.github.yunivers.nihilo.events.init.InitItems;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.dispenser.DispenseEvent;
import net.modificationstation.stationapi.api.dispenser.ItemDispenseContext;
import net.modificationstation.stationapi.api.util.math.Direction;

public class DispenserListener
{
    @EventListener
    public static void changeDispenseBehavior(DispenseEvent event)
    {
        ItemDispenseContext context = event.context;
        World world = context.dispenser.world;

        if (context.itemStack != null)
        {
            Direction facing = context.direction;

            // Why is everything here flipped?!?!?!
            double xOffset = 0;
            double zOffset = 0;
            if (facing == Direction.NORTH)
                xOffset = -1;
            else if (facing == Direction.SOUTH)
                xOffset = 1;
            else if (facing == Direction.WEST)
                zOffset = 1;
            else if (facing == Direction.EAST)
                zOffset = -1;

            double thrownEntityX = (double)context.dispenser.x + xOffset * 0.6 + 0.5d;
            double thrownEntityY = (double)context.dispenser.y + 0.5d;
            double thrownEntityZ = (double)context.dispenser.z + zOffset * 0.6 + 0.5d;

            // Throw Stone Pebble
            if (context.itemStack.itemId == InitItems.STONE_PEBBLE.id)
            {
                PebbleEntity pebble = new PebbleEntity(world, thrownEntityX, thrownEntityY, thrownEntityZ);
                pebble.setVelocity(xOffset, 0.1d, zOffset, 1.1F, 6.0F);
                world.spawnEntity(pebble);
                world.worldEvent(1002, context.dispenser.x, context.dispenser.y, context.dispenser.z, 0);
                event.cancel();
            }
        }
    }
}