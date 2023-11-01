package team.dovecotmc.glasses.util;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import team.dovecotmc.glasses.common.item.base.GlassesItemCurios;
import team.dovecotmc.glasses.common.item.impl.SunglassesItemCurios;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Predicate;

public class Utilities {
    public static final Predicate<ItemStack> GLASSES = s -> s.getItem() instanceof GlassesItemCurios;
    public static final Predicate<ItemStack> SUNGLASSES = s -> s.getItem() instanceof SunglassesItemCurios;

    public static boolean isWearingOnCuriosSlot(LivingEntity entity, Predicate<ItemStack> filter) {
        final AtomicBoolean b = new AtomicBoolean(false);
        CuriosApi.getCuriosInventory(entity).ifPresent(h -> b.set(h.findFirstCurio(filter).isPresent()));
        return b.get();
    }
}
