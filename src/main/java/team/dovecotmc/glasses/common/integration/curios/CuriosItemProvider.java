package team.dovecotmc.glasses.common.integration.curios;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import team.dovecotmc.glasses.common.item.base.GlassesItemCurios;
import team.dovecotmc.glasses.common.item.impl.SunglassesItemCurios;

import java.util.function.Supplier;

public class CuriosItemProvider {
    public static Supplier<Item> commonGlasses(Component artisan) {
        return () -> new GlassesItemCurios(artisan);
    }

    public static Supplier<Item> sunGlasses(Component artisan) {
        return () -> new SunglassesItemCurios(artisan);
    }
}
