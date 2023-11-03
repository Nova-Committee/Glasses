package team.dovecotmc.glasses.mixin.common;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import team.dovecotmc.glasses.Glasses;
import team.dovecotmc.glasses.common.item.base.GlassesItem;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity {
    @Inject(method = "getEquipmentSlotForItem", at = @At("HEAD"), cancellable = true)
    private static void inject$getEquipmentSlotForItem(ItemStack itemStack, CallbackInfoReturnable<EquipmentSlot> cir) {
        if (Glasses.isTrinketsLoaded()) return;
        if (itemStack.getItem() instanceof GlassesItem) cir.setReturnValue(EquipmentSlot.HEAD);
    }
}
