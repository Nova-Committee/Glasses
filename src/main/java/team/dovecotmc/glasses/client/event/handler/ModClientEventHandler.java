package team.dovecotmc.glasses.client.event.handler;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import team.dovecotmc.glasses.Glasses;
import team.dovecotmc.glasses.client.integration.curios.CuriosClientIntegration;
import team.dovecotmc.glasses.client.keybinding.KeyBindingRef;
import team.dovecotmc.glasses.common.item.base.GlassesItem;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModClientEventHandler {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            if (Glasses.isCuriosLoaded()) CuriosClientIntegration.init();
        });
    }

    @SubscribeEvent
    public static void onRegisterKeyMappings(RegisterKeyMappingsEvent event) {
        KeyBindingRef.getFunctioning().forEach(r -> event.register(r.get()));
    }

    @SubscribeEvent
    public static void overrideRegistry(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            BuiltInRegistries.ITEM.forEach(i -> {
                if (!(i instanceof GlassesItem g)) return;
                if (g.getProperties().canUse()) ItemProperties.register(g, new ResourceLocation("on"),
                        (stack, world, entity, item) -> stack.getOrCreateTag().getBoolean("glasses_using") ? 1 : 0);
            });
        });
    }
}
