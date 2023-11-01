package team.dovecotmc.glasses.common.item.impl;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class SunglassesItemCurios extends SunglassesItem implements ICurioItem {
    public SunglassesItemCurios(Component artisan) {
        super(artisan);
    }

    @NotNull
    @Override
    public ICurio.SoundInfo getEquipSound(SlotContext slotContext, ItemStack stack) {
        return new ICurio.SoundInfo(SoundEvents.ARMOR_EQUIP_GOLD, .8F, .8F + (float) Math.random() * .2F);
    }

    @Override
    public boolean isEnderMask(SlotContext slotContext, EnderMan enderMan, ItemStack stack) {
        return true;
    }
}
