package team.dovecotmc.glasses.common.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import team.dovecotmc.glasses.Glasses;
import team.dovecotmc.glasses.common.ref.CreativeModeTabRef;
import team.dovecotmc.glasses.common.ref.ItemRef;

public class RegistryHandler {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Glasses.MODID);
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Glasses.MODID);

    public static void init(IEventBus bus) {
        ITEMS.register(bus);
        TABS.register(bus);
        ItemRef.init();
        CreativeModeTabRef.init();
    }
}
