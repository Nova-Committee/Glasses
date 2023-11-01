package team.dovecotmc.glasses.client.event.handler;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import team.dovecotmc.glasses.Glasses;
import team.dovecotmc.glasses.client.integration.curios.CuriosClientIntegration;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModClientEventHandler {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            if (Glasses.isCuriosLoaded()) CuriosClientIntegration.init();
        });
    }
}
