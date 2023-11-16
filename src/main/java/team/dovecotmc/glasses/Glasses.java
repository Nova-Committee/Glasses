package team.dovecotmc.glasses;

import com.mojang.logging.LogUtils;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import team.dovecotmc.glasses.common.init.RegistryHandler;
import team.dovecotmc.glasses.common.item.base.GlassesItem;
import team.dovecotmc.glasses.util.common.CommonUtilities;

public class Glasses implements ModInitializer {
    public static final String MODID = "glasses";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final ResourceLocation GLASSES_USE = new ResourceLocation(MODID, "use");
    private static boolean trinketsLoaded = false;

    public static boolean isTrinketsLoaded() {
        return trinketsLoaded;
    }

    @Override
    public void onInitialize() {
        trinketsLoaded = FabricLoader.getInstance().isModLoaded("trinkets");
        RegistryHandler.init();
        ServerPlayNetworking.registerGlobalReceiver(GLASSES_USE, ((server, player, handler, buf, responseSender) -> {
            if (player == null) return;
            CommonUtilities.getMatchedWearingItem(player, CommonUtilities.GLASSES).ifPresent(m -> {
                if (m.getItem() instanceof GlassesItem g
                        && (g.getProperties().packetAction() != null
                        || g.getProperties().canUse()))
                    g.onReceivePacket(player, m);
            });
        }));
    }
}
