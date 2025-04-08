package io.github.yunivers.nihilo.registries.events;

import io.github.yunivers.nihilo.registries.HammerRegistry;
import io.github.yunivers.nihilo.registries.helpers.Smashable;
import net.mine_diver.unsafeevents.event.EventPhases;
import net.modificationstation.stationapi.api.StationAPI;
import net.modificationstation.stationapi.api.event.registry.RegistryEvent;

@EventPhases(StationAPI.INTERNAL_PHASE)
public class HammerRegistryEvent extends RegistryEvent.EntryTypeBound<Smashable, HammerRegistry>
{
    public HammerRegistryEvent()
    {
        super(HammerRegistry.INSTANCE);
    }
}
