package team.dovecotmc.glasses.common.item.base;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class GlassesItemCurios extends GlassesItem implements ICurioItem {

    public GlassesItemCurios(Component artisan, Properties properties) {
        super(artisan, properties);
    }

    public GlassesItemCurios(Component artisan) {
        this(artisan, new Properties());
    }

    @NotNull
    @Override
    public ICurio.SoundInfo getEquipSound(SlotContext slotContext, ItemStack stack) {
        return new ICurio.SoundInfo(SoundEvents.ARMOR_EQUIP_GOLD, .8F, .8F + (float) Math.random() * .2F);
    }
}
