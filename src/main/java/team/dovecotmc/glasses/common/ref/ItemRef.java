package team.dovecotmc.glasses.common.ref;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;
import team.dovecotmc.glasses.Glasses;
import team.dovecotmc.glasses.common.info.Artisans;
import team.dovecotmc.glasses.common.init.RegistryHandler;
import team.dovecotmc.glasses.common.integration.curios.CuriosItemProvider;
import team.dovecotmc.glasses.common.item.base.GlassesItem;
import team.dovecotmc.glasses.common.item.impl.SunglassesItem;

import java.util.Locale;
import java.util.function.Supplier;

public enum ItemRef implements Supplier<Item> {
    BEACH_GLASSES(sunGlasses(Artisans.GREYGOD)),
    DETECTIVE_GLASSES(commonGlasses(Artisans.GREYGOD)),
    MONOCLE(commonGlasses(Artisans.GREYGOD)),
    SUNGLASSES_STYLE1(sunGlasses(Artisans.TAPIO)),
    SUNGLASSES_STYLE2(sunGlasses(Artisans.GREYGOD)),
    TACTICAL_GOGGLES(sunGlasses(Artisans.GREYGOD));

    ItemRef(Supplier<Item> sup) {
        this.reg = RegistryHandler.ITEMS.register(this.name().toLowerCase(Locale.ROOT), sup);
    }

    private final RegistryObject<Item> reg;

    @Override
    public Item get() {
        return reg.get();
    }

    public static void init() {
    }

    private static Supplier<Item> commonGlasses(Component artisan) {
        return Glasses.isCuriosLoaded() ? CuriosItemProvider.commonGlasses(artisan) : () -> new GlassesItem(artisan);
    }

    private static Supplier<Item> sunGlasses(Component artisan) {
        return Glasses.isCuriosLoaded() ? CuriosItemProvider.sunGlasses(artisan) : () -> new SunglassesItem(artisan);
    }
}
