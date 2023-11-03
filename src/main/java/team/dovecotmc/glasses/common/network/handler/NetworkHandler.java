package team.dovecotmc.glasses.common.network.handler;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import team.dovecotmc.glasses.Glasses;
import team.dovecotmc.glasses.common.network.msg.GlassesUseMessage;

public class NetworkHandler {
    public static SimpleChannel INSTANCE;
    public static final String VERSION = "1";
    private static int id = 0;

    public static int nextId() {
        return id++;
    }

    public static void registerMessage() {
        INSTANCE = NetworkRegistry.newSimpleChannel(
                new ResourceLocation(Glasses.MODID, "use"),
                () -> VERSION,
                VERSION::equals,
                VERSION::equals
        );
        INSTANCE.messageBuilder(GlassesUseMessage.class, nextId())
                .encoder(GlassesUseMessage::toBytes)
                .decoder(GlassesUseMessage::new)
                .consumerMainThread(GlassesUseMessage::handler)
                .add();
    }
}
