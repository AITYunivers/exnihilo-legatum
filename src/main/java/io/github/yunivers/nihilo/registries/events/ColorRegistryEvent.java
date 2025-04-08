package io.github.yunivers.nihilo.registries.events;

import io.github.yunivers.nihilo.registries.ColorRegistry;
import io.github.yunivers.nihilo.registries.helpers.Color;
import net.mine_diver.unsafeevents.event.EventPhases;
import net.modificationstation.stationapi.api.StationAPI;
import net.modificationstation.stationapi.api.event.registry.RegistryEvent;

@EventPhases(StationAPI.INTERNAL_PHASE)
public class ColorRegistryEvent extends RegistryEvent.EntryTypeBound<Color, ColorRegistry>
{
    public ColorRegistryEvent()
    {
        super(ColorRegistry.INSTANCE);
    }
}
