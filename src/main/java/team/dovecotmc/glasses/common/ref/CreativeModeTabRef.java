package team.dovecotmc.glasses.common.ref;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import team.dovecotmc.glasses.Glasses;
import team.dovecotmc.glasses.common.item.base.GlassesItem;

import java.util.Locale;
import java.util.function.Supplier;

public enum CreativeModeTabRef implements Supplier<CreativeModeTab> {
    GLASSES(() -> CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
            .title(Component.translatable("itemGroup.glasses.glasses"))
            .icon(() -> ItemRef.SUNGLASSES_STYLE2.get().getDefaultInstance())
            .displayItems((param, output) -> {
                BuiltInRegistries.ITEM.entrySet().forEach(e -> {
                    final Item i = e.getValue();
                    if (!(i instanceof GlassesItem)) return;
                    output.accept(i);
                });
            })
            .build());


    CreativeModeTabRef(Supplier<CreativeModeTab> sup) {
        this.obj = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
                new ResourceLocation(Glasses.MODID, this.name().toLowerCase(Locale.ROOT)), sup.get());
    }

    private final CreativeModeTab obj;

    @Override
    public CreativeModeTab get() {
        return obj;
    }

    public static void init() {
    }
}
