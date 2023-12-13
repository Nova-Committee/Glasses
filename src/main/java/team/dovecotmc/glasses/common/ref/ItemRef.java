package team.dovecotmc.glasses.common.ref;

import net.minecraft.ChatFormatting;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import team.dovecotmc.glasses.Glasses;
import team.dovecotmc.glasses.common.info.Artisan;
import team.dovecotmc.glasses.common.integration.trinkets.TrinketsComponentProvider;
import team.dovecotmc.glasses.common.item.base.GlassesItem;
import team.dovecotmc.glasses.common.item.impl.SunglassesItem;
import team.dovecotmc.glasses.common.item.properties.GlassesProperties;

import java.util.Locale;
import java.util.function.Supplier;

public enum ItemRef implements Supplier<Item> {
    GLASSES_0(sunGlasses(Artisan.GREYGOD)),
    GLASSES_1(commonGlasses(Artisan.GREYGOD, GlassesProperties.builder()
            .canUse(SoundEvents.STONE_BUTTON_CLICK_ON, SoundEvents.STONE_BUTTON_CLICK_OFF)
            .build())),
    GLASSES_2(commonGlasses(Artisan.GREYGOD)),
    GLASSES_3(commonGlasses(Artisan.GREYGOD, GlassesProperties.builder()
            .canUse(SoundEvents.SPYGLASS_USE, SoundEvents.SPYGLASS_STOP_USING)
            .build())),
    GLASSES_4(sunGlasses(Artisan.TAPIO)),
    GLASSES_5(sunGlasses(Artisan.GREYGOD)),
    GLASSES_6(sunGlasses(Artisan.GREYGOD)),
    GLASSES_7(sunGlasses(Artisan.GREYGOD)),
    GLASSES_8(sunGlasses(Artisan.GREYGOD)),
    GLASSES_9(sunGlasses(Artisan.GREYGOD, GlassesProperties.builder()
            .canUse(SoundEvents.STONE_BUTTON_CLICK_ON, SoundEvents.STONE_BUTTON_CLICK_OFF)
            .tooltip(() -> (s, w, l, f) -> l.add(Component.translatable("tooltips.glasses.technical")
                    .withStyle(ChatFormatting.GREEN)))
            .build())),
    GLASSES_10(sunGlasses(Artisan.GREYGOD)),
    GLASSES_11(sunGlasses(Artisan.SHOU_ZHANG, GlassesProperties.builder()
            .tooltip(() -> (s, w, l, f) -> l.add(Component.translatable("tooltips.glasses.shades_summer")))
            .properties(new Item.Properties().rarity(Rarity.UNCOMMON))
            .build())),
    GLASSES_12(sunGlasses(Artisan.SHOU_ZHANG, GlassesProperties.builder()
            .tooltip(() -> (s, w, l, f) -> l.add(Component.translatable("tooltips.glasses.shades_winter")))
            .properties(new Item.Properties().rarity(Rarity.UNCOMMON))
            .build()));

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

    private static Supplier<Item> commonGlasses(Artisan artisan) {
        return Glasses.isTrinketsLoaded() ? TrinketsComponentProvider.commonGlasses(artisan.getName()) : () -> new GlassesItem(artisan.getName());
    }

    private static Supplier<Item> commonGlasses(Artisan artisan, GlassesProperties properties) {
        return Glasses.isTrinketsLoaded() ? TrinketsComponentProvider.commonGlasses(artisan.getName(), properties)
                : () -> new GlassesItem(artisan.getName(), properties);
    }

    private static Supplier<Item> sunGlasses(Artisan artisan) {
        return Glasses.isTrinketsLoaded() ? TrinketsComponentProvider.sunGlasses(artisan.getName()) : () -> new SunglassesItem(artisan.getName());
    }

    private static Supplier<Item> sunGlasses(Artisan artisan, GlassesProperties properties) {
        return Glasses.isTrinketsLoaded() ? TrinketsComponentProvider.sunGlasses(artisan.getName(), properties)
                : () -> new SunglassesItem(artisan.getName(), properties);
    }
}
