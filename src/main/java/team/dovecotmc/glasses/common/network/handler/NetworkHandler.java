package team.dovecotmc.glasses.common.network.handler;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.Channel;
import net.minecraftforge.network.ChannelBuilder;
import net.minecraftforge.network.SimpleChannel;
import team.dovecotmc.glasses.Glasses;
import team.dovecotmc.glasses.common.network.msg.GlassesUseMessage;

public class NetworkHandler {
    public static SimpleChannel INSTANCE;
    public static final String VERSION = "1.0";
    private static int id = 0;

    public static int nextId() {
        return id++;
    }

    public static void registerMessage() {
        INSTANCE = ChannelBuilder.named(new ResourceLocation(Glasses.MODID, "use"))
                .acceptedVersions(Channel.VersionTest.exact(1))
                .networkProtocolVersion(1)
                .simpleChannel();
        INSTANCE.messageBuilder(GlassesUseMessage.class, nextId())
                .encoder(GlassesUseMessage::toBytes)
                .decoder(GlassesUseMessage::new)
                .consumerMainThread(GlassesUseMessage::handler)
                .add();
    }
}
