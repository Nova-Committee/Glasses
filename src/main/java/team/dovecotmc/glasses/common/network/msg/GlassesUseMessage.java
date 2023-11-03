package team.dovecotmc.glasses.common.network.msg;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import team.dovecotmc.glasses.common.item.base.GlassesItem;
import team.dovecotmc.glasses.util.Utilities;

import java.util.function.Supplier;

public class GlassesUseMessage {
    public GlassesUseMessage(FriendlyByteBuf buf) {
    }

    public GlassesUseMessage() {
    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public void handler(Supplier<NetworkEvent.Context> sup) {
        final NetworkEvent.Context ctx = sup.get();
        ctx.enqueueWork(() -> {
            final ServerPlayer player = ctx.getSender();
            if (player == null) return;
            Utilities.getMatchedWearingItem(player, Utilities.MONOCULAR).ifPresent(m -> {
                if (m.getItem() instanceof GlassesItem g && g.getProperties().packetAction() != null)
                    g.onReceivePacket(player, m);
            });
        });
        ctx.setPacketHandled(true);
    }

}
