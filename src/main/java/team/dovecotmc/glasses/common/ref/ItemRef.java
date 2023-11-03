package team.dovecotmc.glasses.common.ref;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.Item;
import team.dovecotmc.glasses.Glasses;
import team.dovecotmc.glasses.client.keybinding.KeyBindings;
import team.dovecotmc.glasses.common.info.Artisans;
import team.dovecotmc.glasses.common.integration.trinkets.TrinketsComponentProvider;
import team.dovecotmc.glasses.common.item.base.GlassesItem;
import team.dovecotmc.glasses.common.item.impl.SunglassesItem;
import team.dovecotmc.glasses.common.item.properties.GlassesProperties;
import team.dovecotmc.glasses.mixin.client.AccessorKeyMapping;

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
                    ((AccessorKeyMapping) KeyBindings.GLASSES_ACTION).getKey().getDisplayName(), s.getItem().getName(s))))
            .build())),
    SUNGLASSES_STYLE1(sunGlasses(Artisans.TAPIO)),
    SUNGLASSES_STYLE2(sunGlasses(Artisans.GREYGOD)),
    TACTICAL_GOGGLES_STYLE1(sunGlasses(Artisans.GREYGOD));

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
