package team.dovecotmc.glasses;

import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;
import team.dovecotmc.glasses.common.init.RegistryHandler;
import team.dovecotmc.glasses.common.network.handler.NetworkHandler;

@Mod(Glasses.MODID)
public class Glasses {
    public static final String MODID = "glasses";
    public static final Logger LOGGER = LogUtils.getLogger();
    private static boolean curiosLoaded = false;

    public Glasses(IEventBus bus) {
        curiosLoaded = ModList.get().isLoaded("curios");
        RegistryHandler.init(bus);
        NetworkHandler.registerMessage();
    }

    public static boolean isCuriosLoaded() {
        return curiosLoaded;
    }
}
