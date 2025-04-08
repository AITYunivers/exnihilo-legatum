package io.github.yunivers.nihilo.registries.events;

import io.github.yunivers.nihilo.registries.SieveRegistry;
import io.github.yunivers.nihilo.registries.helpers.SiftReward;
import net.mine_diver.unsafeevents.event.EventPhases;
import net.modificationstation.stationapi.api.StationAPI;
import net.modificationstation.stationapi.api.event.registry.RegistryEvent;

@EventPhases(StationAPI.INTERNAL_PHASE)
public class SieveRegistryEvent extends RegistryEvent.EntryTypeBound<SiftReward, SieveRegistry>
{
    public SieveRegistryEvent()
    {
        super(SieveRegistry.INSTANCE);
    }
}
