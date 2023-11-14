package team.dovecotmc.glasses.common.ref;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;
import team.dovecotmc.glasses.Glasses;
import team.dovecotmc.glasses.client.keybinding.KeyBindingRef;
import team.dovecotmc.glasses.common.info.Artisans;
import team.dovecotmc.glasses.common.init.RegistryHandler;
import team.dovecotmc.glasses.common.integration.curios.CuriosComponentProvider;
import team.dovecotmc.glasses.common.item.base.GlassesItem;
import team.dovecotmc.glasses.common.item.impl.SunglassesItem;
import team.dovecotmc.glasses.common.item.properties.GlassesProperties;

import java.util.Locale;
import java.util.function.Supplier;

public enum ItemRef implements Supplier<Item> {
    BEACH_GLASSES_STYLE1(sunGlasses(Artisans.GREYGOD)),
    DETECTIVE_GLASSES(commonGlasses(Artisans.GREYGOD)),
    MONOCLE_STYLE1(commonGlasses(Artisans.GREYGOD)),
    MONOCULAR(commonGlasses(Artisans.GREYGOD, GlassesProperties.builder()
            .packetAction((p, m) -> {
                final CompoundTag tag = m.getOrCreateTag();
                final boolean newStatus = !tag.getBoolean("glasses_using");
                p.playNotifySound(newStatus ? SoundEvents.SPYGLASS_USE : SoundEvents.SPYGLASS_STOP_USING,
                        SoundSource.PLAYERS, 1.0F, 1.0F);
                tag.putBoolean("glasses_using", newStatus);
            })
            .tooltip(() -> (s, l, t, f) -> t.add(Component.translatable("tooltips.glasses.use",
                    KeyBindingRef.GLASSES_ACTION.get().getKey().getDisplayName(), s.getItem().getName(s))))
            .build())),
    SUNGLASSES_STYLE1(sunGlasses(Artisans.TAPIO)),
    SUNGLASSES_STYLE2(sunGlasses(Artisans.GREYGOD)),
    TACTICAL_GOGGLES_STYLE1(sunGlasses(Artisans.GREYGOD));

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
