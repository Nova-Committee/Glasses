package team.dovecotmc.glasses.common.integration.trinkets;

import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import team.dovecotmc.glasses.common.item.base.GlassesItemTrinkets;
import team.dovecotmc.glasses.common.item.impl.SunglassesItemTrinkets;
import team.dovecotmc.glasses.common.item.properties.GlassesProperties;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class TrinketsComponentProvider {
    public static Supplier<Item> commonGlasses(Component artisan) {
        return () -> new GlassesItemTrinkets(artisan);
    }

    public static Supplier<Item> sunGlasses(Component artisan) {
        return () -> new SunglassesItemTrinkets(artisan);
    }

    public static Supplier<Item> commonGlasses(Component artisan, GlassesProperties properties) {
        return () -> new GlassesItemTrinkets(artisan, properties);
    }

    public static Supplier<Item> sunGlasses(Component artisan, GlassesProperties properties) {
        return () -> new SunglassesItemTrinkets(artisan, properties);
    }

    public static boolean isWearingOnTrinketsSlot(LivingEntity entity, Predicate<ItemStack> filter) {
        return getMatchedWearingItem(entity, filter).isPresent();
    }

    public static Optional<ItemStack> getMatchedWearingItem(LivingEntity entity, Predicate<ItemStack> filter) {
        return TrinketsApi.getTrinketComponent(entity)
                .map(i -> i.getEquipped(filter))
                .map(r -> !r.isEmpty() ? r.get(0).getB() : ItemStack.EMPTY);
    }
}
