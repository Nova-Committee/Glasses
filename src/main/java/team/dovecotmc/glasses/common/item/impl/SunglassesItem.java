package team.dovecotmc.glasses.common.item.impl;

import net.minecraft.network.chat.Component;
import team.dovecotmc.glasses.common.item.base.GlassesItem;
import team.dovecotmc.glasses.common.item.properties.GlassesProperties;

public class SunglassesItem extends GlassesItem {
    public SunglassesItem(Component artisan, GlassesProperties properties) {
        super(artisan, properties);
    }

    public SunglassesItem(Component artisan) {
        this(artisan, GlassesProperties.builder().build());
    }
}
