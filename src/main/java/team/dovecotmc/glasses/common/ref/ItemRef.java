package team.dovecotmc.glasses.common.ref;

import net.minecraft.ChatFormatting;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import team.dovecotmc.glasses.Glasses;
import team.dovecotmc.glasses.common.info.Artisans;
import team.dovecotmc.glasses.common.integration.trinkets.TrinketsComponentProvider;
import team.dovecotmc.glasses.common.item.base.GlassesItem;
import team.dovecotmc.glasses.common.item.impl.SunglassesItem;
import team.dovecotmc.glasses.common.item.properties.GlassesProperties;

import java.util.Locale;
import java.util.function.Supplier;

public enum ItemRef implements Supplier<Item> {
    GLASSES_0(sunGlasses(Artisans.GREYGOD)),
    GLASSES_1(commonGlasses(Artisans.GREYGOD, GlassesProperties.builder()
            .canUse(SoundEvents.STONE_BUTTON_CLICK_ON, SoundEvents.STONE_BUTTON_CLICK_OFF)
            .build())),
    GLASSES_2(commonGlasses(Artisans.GREYGOD)),
    GLASSES_3(commonGlasses(Artisans.GREYGOD, GlassesProperties.builder()
            .canUse(SoundEvents.SPYGLASS_USE, SoundEvents.SPYGLASS_STOP_USING)
            .build())),
    GLASSES_4(sunGlasses(Artisans.TAPIO)),
    GLASSES_5(sunGlasses(Artisans.GREYGOD)),
    GLASSES_6(sunGlasses(Artisans.GREYGOD)),
    GLASSES_7(sunGlasses(Artisans.GREYGOD)),
    GLASSES_8(sunGlasses(Artisans.GREYGOD)),
    GLASSES_9(sunGlasses(Artisans.GREYGOD, GlassesProperties.builder()
            .canUse(SoundEvents.STONE_BUTTON_CLICK_ON, SoundEvents.STONE_BUTTON_CLICK_OFF)
            .tooltip(() -> (s, w, l, f) -> l.add(Component.translatable("tooltips.glasses.technical")
                    .withStyle(ChatFormatting.GREEN)))
            .build())),
    GLASSES_10(sunGlasses(Artisans.GREYGOD));

    ItemRef(Supplier<Item> sup) {
        this.obj = Registry.register(BuiltInRegistries.ITEM,
                new ResourceLocation(Glasses.MODID, this.name().toLowerCase(Locale.ROOT)), sup.get());
    }

    private final Item obj;

    @Override
    public Item get() {
        return obj;
    }

    public static void init() {
    }

    private static Supplier<Item> commonGlasses(Component artisan) {
        return Glasses.isTrinketsLoaded() ? TrinketsComponentProvider.commonGlasses(artisan) : () -> new GlassesItem(artisan);
    }

    private static Supplier<Item> commonGlasses(Component artisan, GlassesProperties properties) {
        return Glasses.isTrinketsLoaded() ? TrinketsComponentProvider.commonGlasses(artisan, properties)
                : () -> new GlassesItem(artisan, properties);
    }

    private static Supplier<Item> sunGlasses(Component artisan) {
        return Glasses.isTrinketsLoaded() ? TrinketsComponentProvider.sunGlasses(artisan) : () -> new SunglassesItem(artisan);
    }

    private static Supplier<Item> sunGlasses(Component artisan, GlassesProperties properties) {
        return Glasses.isTrinketsLoaded() ? TrinketsComponentProvider.sunGlasses(artisan, properties)
                : () -> new SunglassesItem(artisan, properties);
    }
}
