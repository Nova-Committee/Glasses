package team.dovecotmc.glasses.common.integration.curios;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import team.dovecotmc.glasses.common.item.base.GlassesItemCurios;
import team.dovecotmc.glasses.common.item.impl.SunglassesItemCurios;
import team.dovecotmc.glasses.common.item.properties.GlassesProperties;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class CuriosComponentProvider {
    public static Supplier<Item> commonGlasses(Component artisan) {
        return () -> new GlassesItemCurios(artisan);
    }

    public static Supplier<Item> sunGlasses(Component artisan) {
        return () -> new SunglassesItemCurios(artisan);
    }

    public static Supplier<Item> commonGlasses(Component artisan, GlassesProperties properties) {
        return () -> new GlassesItemCurios(artisan, properties);
    }

    public static Supplier<Item> sunGlasses(Component artisan, GlassesProperties properties) {
        return () -> new SunglassesItemCurios(artisan, properties);
    }

    public static boolean isWearingOnCuriosSlot(LivingEntity entity, Predicate<ItemStack> filter) {
        return getMatchedWearingItem(entity, filter).isPresent();
    }

    public static Optional<ItemStack> getMatchedWearingItem(LivingEntity entity, Predicate<ItemStack> filter) {
        return CuriosApi.getCuriosInventory(entity)
                .map(i -> i.findFirstCurio(filter))
                .map(r -> r.isPresent() ? r.get().stack() : ItemStack.EMPTY);
    }
}
