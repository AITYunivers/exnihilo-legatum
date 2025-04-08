package io.github.yunivers.nihilo.registries.events;

import io.github.yunivers.nihilo.registries.CompostRegistry;
import io.github.yunivers.nihilo.registries.helpers.Compostable;
import net.mine_diver.unsafeevents.event.EventPhases;
import net.modificationstation.stationapi.api.StationAPI;
import net.modificationstation.stationapi.api.event.registry.RegistryEvent;

@EventPhases(StationAPI.INTERNAL_PHASE)
public class CompostRegistryEvent extends RegistryEvent.EntryTypeBound<Compostable, CompostRegistry>
{
    public CompostRegistryEvent()
    {
        super(CompostRegistry.INSTANCE);
    }
}
