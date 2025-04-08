package io.github.yunivers.nihilo.registries.events;

import io.github.yunivers.nihilo.registries.HeatRegistry;
import io.github.yunivers.nihilo.registries.helpers.HeatSource;
import net.mine_diver.unsafeevents.event.EventPhases;
import net.modificationstation.stationapi.api.StationAPI;
import net.modificationstation.stationapi.api.event.registry.RegistryEvent;

@EventPhases(StationAPI.INTERNAL_PHASE)
public class HeatRegistryEvent extends RegistryEvent.EntryTypeBound<HeatSource, HeatRegistry>
{
    public HeatRegistryEvent()
    {
        super(HeatRegistry.INSTANCE);
    }
}
