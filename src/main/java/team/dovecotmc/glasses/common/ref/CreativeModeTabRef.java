package team.dovecotmc.glasses.common.ref;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.neoforge.registries.DeferredHolder;
import team.dovecotmc.glasses.common.init.RegistryHandler;

import java.util.Arrays;
import java.util.Locale;
import java.util.function.Supplier;

public enum CreativeModeTabRef implements Supplier<CreativeModeTab> {
    GLASSES(() -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.glasses.glasses"))
            .icon(() -> ItemRef.GLASSES_5.get().getDefaultInstance())
            .displayItems((param, output) -> Arrays.stream(ItemRef.values()).forEach(r -> output.accept(r.get())))
            .build());


    CreativeModeTabRef(Supplier<CreativeModeTab> sup) {
        this.reg = RegistryHandler.TABS.register(this.name().toLowerCase(Locale.ROOT), sup);
    }

    private final DeferredHolder<CreativeModeTab, CreativeModeTab> reg;

    @Override
    public CreativeModeTab get() {
        return reg.get();
    }

    public static void init() {
    }
}
