package team.dovecotmc.glasses.common.network.msg;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraftforge.event.network.CustomPayloadEvent;
import team.dovecotmc.glasses.util.Utilities;

public class MonocularUseMessage {
    public MonocularUseMessage(FriendlyByteBuf buf) {
    }

    public MonocularUseMessage() {
    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public void handler(CustomPayloadEvent.Context ctx) {
        ctx.enqueueWork(() -> {
            final ServerPlayer player = ctx.getSender();
            if (player == null) return;
            Utilities.getMatchedWearingItem(player, Utilities.MONOCULAR).ifPresent(m -> {
                final CompoundTag tag = m.getOrCreateTag();
                final boolean newStatus = !tag.getBoolean("glasses_using");
                player.playNotifySound(newStatus ? SoundEvents.SPYGLASS_USE : SoundEvents.SPYGLASS_STOP_USING,
                        SoundSource.PLAYERS, 1.0F, 1.0F);
                tag.putBoolean("glasses_using", newStatus);
            });
        });
        ctx.setPacketHandled(true);
    }

}
