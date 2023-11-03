package team.dovecotmc.glasses.common.item.impl;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.Trinket;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import team.dovecotmc.glasses.common.item.properties.GlassesProperties;

public class SunglassesItemTrinkets extends SunglassesItem implements Trinket {
    public SunglassesItemTrinkets(Component artisan, GlassesProperties properties) {
        super(artisan, properties);
    }

    public SunglassesItemTrinkets(Component artisan) {
        this(artisan, GlassesProperties.builder().build());
    }

    @Override
    public void onEquip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        entity.level().playLocalSound(entity.getX(), entity.getY(), entity.getZ(),
                SoundEvents.ARMOR_EQUIP_GOLD, SoundSource.PLAYERS, 1.0F, 1.0F, false);
    }
}
