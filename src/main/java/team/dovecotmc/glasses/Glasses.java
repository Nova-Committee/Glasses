package team.dovecotmc.glasses;

import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import team.dovecotmc.glasses.common.init.RegistryHandler;
import team.dovecotmc.glasses.common.network.handler.NetworkHandler;

@Mod(Glasses.MODID)
public class Glasses {
    public static final String MODID = "glasses";
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
