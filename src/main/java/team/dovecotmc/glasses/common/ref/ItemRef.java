package team.dovecotmc.glasses.common.ref;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.RegistryObject;
import team.dovecotmc.glasses.Glasses;
import team.dovecotmc.glasses.common.info.Artisan;
import team.dovecotmc.glasses.common.init.RegistryHandler;
import team.dovecotmc.glasses.common.integration.curios.CuriosComponentProvider;
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
            .tooltip(() -> (s, w, l, f) -> l.add(new TranslatableComponent("tooltips.glasses.technical")
                    .withStyle(ChatFormatting.GREEN)))
            .build())),
    GLASSES_10(sunGlasses(Artisan.GREYGOD)),
    GLASSES_11(sunGlasses(Artisan.SHOU_ZHANG, GlassesProperties.builder()
            .tooltip(() -> (s, w, l, f) -> l.add(new TranslatableComponent("tooltips.glasses.shades_summer")))
            .properties(new Item.Properties().rarity(Rarity.UNCOMMON))
            .build())),
    GLASSES_12(sunGlasses(Artisan.SHOU_ZHANG, GlassesProperties.builder()
            .tooltip(() -> (s, w, l, f) -> l.add(new TranslatableComponent("tooltips.glasses.shades_winter")))
            .properties(new Item.Properties().rarity(Rarity.UNCOMMON))
            .build()));

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

    private static Supplier<Item> commonGlasses(Artisan artisan) {
        return Glasses.isCuriosLoaded() ? CuriosComponentProvider.commonGlasses(artisan.getName()) : () -> new GlassesItem(artisan.getName());
    }

    private static Supplier<Item> commonGlasses(Artisan artisan, GlassesProperties properties) {
        return Glasses.isCuriosLoaded() ? CuriosComponentProvider.commonGlasses(artisan.getName(), properties)
                : () -> new GlassesItem(artisan.getName(), properties);
    }

    private static Supplier<Item> sunGlasses(Artisan artisan) {
        return Glasses.isCuriosLoaded() ? CuriosComponentProvider.sunGlasses(artisan.getName()) : () -> new SunglassesItem(artisan.getName());
    }

    private static Supplier<Item> sunGlasses(Artisan artisan, GlassesProperties properties) {
        return Glasses.isCuriosLoaded() ? CuriosComponentProvider.sunGlasses(artisan.getName(), properties)
                : () -> new SunglassesItem(artisan.getName(), properties);
    }
}
