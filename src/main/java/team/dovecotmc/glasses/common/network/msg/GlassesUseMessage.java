package team.dovecotmc.glasses.common.network.msg;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.network.CustomPayloadEvent;
import team.dovecotmc.glasses.common.item.base.GlassesItem;
import team.dovecotmc.glasses.util.common.CommonUtilities;

public class GlassesUseMessage {
    public GlassesUseMessage(FriendlyByteBuf buf) {
    }

    public GlassesUseMessage() {
    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public void handler(CustomPayloadEvent.Context ctx) {
        ctx.enqueueWork(() -> {
            final ServerPlayer player = ctx.getSender();
            if (player == null) return;
            CommonUtilities.getMatchedWearingItem(player, CommonUtilities.MONOCULAR).ifPresent(m -> {
                if (m.getItem() instanceof GlassesItem g && g.getProperties().packetAction() != null)
                    g.onReceivePacket(player, m);
            });
        });
        ctx.setPacketHandled(true);
    }

}
