package io.github.yunivers.nihilo.config;

import net.glasslauncher.mods.gcapi3.api.ConfigEntry;
import net.glasslauncher.mods.gcapi3.api.ConfigRoot;

public class Config
{
    @ConfigRoot(value = "config", visibleName = "Ex Nihilio: Legatum Config")
    public static final ConfigFields config = new ConfigFields();

    public static class ConfigFields
    {
        @ConfigEntry(
            name = "Compost Time",
            description = "The amount of time (in ticks) dirt takes to compost",
            minLength = 0,
            maxLength = Integer.MAX_VALUE
        )
        public Integer compostTime = 1000;

        @ConfigEntry(
            name = "Rain Barrel Fill Rate",
            description = "The amount of rain gets added to the barrel every other tick",
            minLength = 0,
            maxLength = Integer.MAX_VALUE
        )
        public Integer rainFillRate = 1;

        @ConfigEntry(
            name = "Wooden Barrel Burn Time",
            description = "The amount of time (in tick) it takes for a wooden barrel to burn",
            minLength = 0,
            maxLength = Integer.MAX_VALUE
        )
        public Integer barrelBurnRate = 400;

        @ConfigEntry(
            name = "Crucible Melting Rate",
            description = "A multiplier for the crucible's melting rate",
            minLength = 0,
            maxLength = 500
        )
        public Float crucibleMeltingRate = 1f;
    }
}
