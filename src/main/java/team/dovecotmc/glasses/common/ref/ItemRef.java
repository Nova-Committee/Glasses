package team.dovecotmc.glasses.common.ref;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;
import team.dovecotmc.glasses.Glasses;
import team.dovecotmc.glasses.common.info.Artisans;
import team.dovecotmc.glasses.common.init.RegistryHandler;
import team.dovecotmc.glasses.common.integration.curios.CuriosComponentProvider;
import team.dovecotmc.glasses.common.item.base.GlassesItem;
import team.dovecotmc.glasses.common.item.impl.SunglassesItem;
import team.dovecotmc.glasses.common.item.properties.GlassesProperties;

import java.util.Locale;
import java.util.function.Supplier;

public enum ItemRef implements Supplier<Item> {
    GLASSES_0(sunGlasses(Artisans.GREYGOD)),
    GLASSES_1(commonGlasses(Artisans.GREYGOD)),
    GLASSES_2(commonGlasses(Artisans.GREYGOD)),
    GLASSES_3(commonGlasses(Artisans.GREYGOD, GlassesProperties.builder()
            .packetAction((p, m) -> {
                final CompoundTag tag = m.getOrCreateTag();
                final boolean newStatus = !tag.getBoolean("glasses_using");
                p.playNotifySound(newStatus ? SoundEvents.SPYGLASS_USE : SoundEvents.SPYGLASS_STOP_USING,
                        SoundSource.PLAYERS, 1.0F, 1.0F);
                tag.putBoolean("glasses_using", newStatus);
            })
            .build())),
    GLASSES_4(sunGlasses(Artisans.TAPIO)),
    GLASSES_5(sunGlasses(Artisans.GREYGOD)),
    GLASSES_6(sunGlasses(Artisans.GREYGOD)),
    GLASSES_7(sunGlasses(Artisans.GREYGOD)),
    GLASSES_8(sunGlasses(Artisans.GREYGOD)),
    GLASSES_9(sunGlasses(Artisans.GREYGOD, GlassesProperties.builder()
            .packetAction((p, m) -> {
                final CompoundTag tag = m.getOrCreateTag();
                final boolean newStatus = !tag.getBoolean("glasses_using");
                p.playNotifySound(newStatus ? SoundEvents.STONE_BUTTON_CLICK_ON : SoundEvents.STONE_BUTTON_CLICK_OFF,
                        SoundSource.PLAYERS, 1.0F, 1.0F);
                tag.putBoolean("glasses_using", newStatus);
            })
            .tooltip(() -> (s, w, l, f) -> l.add(Component.translatable("tooltips.glasses.technical")
                    .withStyle(ChatFormatting.GREEN)))
            .build())),
    GLASSES_10(sunGlasses(Artisans.GREYGOD));

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
        return Glasses.isCuriosLoaded() ? CuriosComponentProvider.commonGlasses(artisan) : () -> new GlassesItem(artisan);
    }

    private static Supplier<Item> commonGlasses(Component artisan, GlassesProperties properties) {
        return Glasses.isCuriosLoaded() ? CuriosComponentProvider.commonGlasses(artisan, properties)
                : () -> new GlassesItem(artisan, properties);
    }

    private static Supplier<Item> sunGlasses(Component artisan) {
        return Glasses.isCuriosLoaded() ? CuriosComponentProvider.sunGlasses(artisan) : () -> new SunglassesItem(artisan);
    }

    private static Supplier<Item> sunGlasses(Component artisan, GlassesProperties properties) {
        return Glasses.isCuriosLoaded() ? CuriosComponentProvider.sunGlasses(artisan, properties)
                : () -> new SunglassesItem(artisan, properties);
    }
}
