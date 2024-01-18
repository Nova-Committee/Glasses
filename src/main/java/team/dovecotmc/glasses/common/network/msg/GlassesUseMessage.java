package team.dovecotmc.glasses.common.network.msg;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import team.dovecotmc.glasses.Glasses;

public class GlassesUseMessage implements CustomPacketPayload {
    public static final ResourceLocation ID = new ResourceLocation(Glasses.MODID, "use");

    public GlassesUseMessage(FriendlyByteBuf buf) {
    }

    public GlassesUseMessage() {
    }

    @Override
    public void write(FriendlyByteBuf pBuffer) {
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }
}
