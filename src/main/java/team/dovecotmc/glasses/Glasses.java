package team.dovecotmc.glasses;

import com.mojang.logging.LogUtils;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import team.dovecotmc.glasses.common.init.RegistryHandler;
import team.dovecotmc.glasses.common.network.handler.NetworkHandler;

@Mod(Glasses.MODID)
public class Glasses {
    public static final String MODID = "glasses";
    public static final Logger LOGGER = LogUtils.getLogger();
    private static boolean curiosLoaded = false;

    public Glasses() {
        curiosLoaded = ModList.get().isLoaded("curios");
        RegistryHandler.init(FMLJavaModLoadingContext.get().getModEventBus());
        NetworkHandler.registerMessage();
    }

    public static boolean isCuriosLoaded() {
        return curiosLoaded;
    }
}
