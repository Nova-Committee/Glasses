package team.dovecotmc.glasses.common.ref;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import team.dovecotmc.glasses.common.init.RegistryHandler;
import team.dovecotmc.glasses.common.item.base.GlassesItem;

import java.util.Locale;
import java.util.function.Supplier;

public enum CreativeModeTabRef implements Supplier<CreativeModeTab> {
    GLASSES(() -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.glasses.glasses"))
            .icon(() -> ItemRef.SUNGLASSES_STYLE2.get().getDefaultInstance())
            .displayItems((param, output) -> {
                ForgeRegistries.ITEMS.getValues().forEach(i -> {
                    if (!(i instanceof GlassesItem)) return;
                    output.accept(i);
                });
            })
            .build());


    CreativeModeTabRef(Supplier<CreativeModeTab> sup) {
        this.reg = RegistryHandler.TABS.register(this.name().toLowerCase(Locale.ROOT), sup);
    }

    private final RegistryObject<CreativeModeTab> reg;

    @Override
    public CreativeModeTab get() {
        return reg.get();
    }

    public static void init() {
    }
}
