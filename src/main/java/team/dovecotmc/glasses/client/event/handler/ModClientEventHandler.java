package team.dovecotmc.glasses.client.event.handler;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;
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
    public static void onStitchTexture(TextureStitchEvent.Pre event) {
        event.addSprite(new ResourceLocation("glasses", "slot/empty_glasses_slot"));
    }

    @SubscribeEvent
    public static void onRegisterKeyMappings(FMLClientSetupEvent event) {
        KeyBindingRef.getFunctioning().forEach(r -> ClientRegistry.registerKeyBinding(r.get()));
    }

    @SubscribeEvent
    public static void overrideRegistry(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ForgeRegistries.ITEMS.getValues().parallelStream().forEach(i -> {
                if (!(i instanceof GlassesItem g)) return;
                if (g.getProperties().canUse()) ItemProperties.register(g, new ResourceLocation("on"),
                        (stack, world, entity, item) -> stack.getOrCreateTag().getBoolean("glasses_using") ? 1 : 0);
            });
        });
    }
}
