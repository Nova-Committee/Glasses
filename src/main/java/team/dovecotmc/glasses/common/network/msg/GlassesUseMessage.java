package team.dovecotmc.glasses.common.network.msg;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.NetworkEvent;
import team.dovecotmc.glasses.common.item.base.GlassesItem;
import team.dovecotmc.glasses.util.common.CommonUtilities;

public class GlassesUseMessage {
    public GlassesUseMessage(FriendlyByteBuf buf) {
    }

    public GlassesUseMessage() {
    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public void handler(NetworkEvent.Context ctx) {
        ctx.enqueueWork(() -> {
            final ServerPlayer player = ctx.getSender();
            if (player == null) return;
            CommonUtilities.getMatchedWearingItem(player, CommonUtilities.GLASSES).ifPresent(m -> {
                if (m.getItem() instanceof GlassesItem g
                        && (g.getProperties().packetAction() != null
                        || g.getProperties().canUse()))
                    g.onReceivePacket(player, m);
            });
        });
        ctx.setPacketHandled(true);
    }

}