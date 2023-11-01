package team.dovecotmc.glasses.common.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import team.dovecotmc.glasses.Glasses;
import team.dovecotmc.glasses.common.ref.CreativeModeTabRef;
import team.dovecotmc.glasses.common.ref.ItemRef;

public class RegistryHandler {
    public static DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Glasses.MODID);
    public static DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Glasses.MODID);

    public static void init(IEventBus bus) {
        ITEMS.register(bus);
        TABS.register(bus);
        ItemRef.init();
        CreativeModeTabRef.init();
    }
}
