package io.github.yunivers.nihilo.registries.events;

import io.github.yunivers.nihilo.registries.CrucibleRegistry;
import io.github.yunivers.nihilo.registries.helpers.Meltable;
import net.mine_diver.unsafeevents.event.EventPhases;
import net.modificationstation.stationapi.api.StationAPI;
import net.modificationstation.stationapi.api.event.registry.RegistryEvent;

@EventPhases(StationAPI.INTERNAL_PHASE)
public class CrucibleRegistryEvent extends RegistryEvent.EntryTypeBound<Meltable, CrucibleRegistry>
{
    public CrucibleRegistryEvent()
    {
        super(CrucibleRegistry.INSTANCE);
    }
}
