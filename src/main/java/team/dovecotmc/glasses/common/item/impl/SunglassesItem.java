package team.dovecotmc.glasses.common.item.impl;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import team.dovecotmc.glasses.common.item.base.GlassesItem;
import team.dovecotmc.glasses.common.item.properties.GlassesProperties;

public class SunglassesItem extends GlassesItem {
    public SunglassesItem(Component artisan, GlassesProperties properties) {
        super(artisan, properties);
    }

    public SunglassesItem(Component artisan) {
        this(artisan, GlassesProperties.builder().build());
    }

    @Override
    public boolean isEnderMask(ItemStack stack, Player player, EnderMan endermanEntity) {
        return true;
    }
}
