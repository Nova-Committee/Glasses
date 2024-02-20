package team.dovecotmc.glasses.common.init;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import team.dovecotmc.glasses.Glasses;
import team.dovecotmc.glasses.common.ref.ItemRef;

public class RegistryHandler {
    public static DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Glasses.MODID);

    public static void init(IEventBus bus) {
        ITEMS.register(bus);
        ItemRef.init();
    }
}
