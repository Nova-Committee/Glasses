package team.dovecotmc.glasses.common.network.handler;

import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlerEvent;
import net.neoforged.neoforge.network.registration.IPayloadRegistrar;
import team.dovecotmc.glasses.Glasses;
import team.dovecotmc.glasses.common.item.base.GlassesItem;
import team.dovecotmc.glasses.common.network.msg.GlassesUseMessage;
import team.dovecotmc.glasses.util.common.CommonUtilities;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class NetworkHandler {
    @SubscribeEvent
    public static void registerPayload(RegisterPayloadHandlerEvent event) {
        final IPayloadRegistrar registrar = event.registrar(Glasses.MODID);
        registrar.play(GlassesUseMessage.ID, GlassesUseMessage::new, h -> h
                .server((d, ctx) -> ctx.workHandler().submitAsync(() -> ctx.player().ifPresent(p -> {
                    if (!(p instanceof ServerPlayer player)) return;
                    CommonUtilities.getMatchedWearingItem(player, CommonUtilities.GLASSES).ifPresent(m -> {
                        if (m.getItem() instanceof GlassesItem g
                                && (g.getProperties().packetAction() != null
                                || g.getProperties().canUse()))
                            g.onReceivePacket(player, m);
                    });
                })).exceptionally(e -> {
                    Glasses.LOGGER.error("Error occurred while handling 'use' message for glasses", e);
                    return null;
                }))
        );
    }
}
