package team.dovecotmc.glasses;

import com.mojang.logging.LogUtils;
import committee.nova.neteasecheck.NeteaseCheck;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import team.dovecotmc.glasses.common.init.RegistryHandler;
import team.dovecotmc.glasses.common.network.handler.NetworkHandler;
import top.theillusivec4.curios.api.SlotTypeMessage;

@Mod(Glasses.MODID)
public class Glasses {
    public static final String MODID = "glasses";
    public static final Logger LOGGER = LogUtils.getLogger();
    private static boolean curiosLoaded = false;

    public Glasses() {
        NeteaseCheck.check();
        curiosLoaded = ModList.get().isLoaded("curios");
        RegistryHandler.init(FMLJavaModLoadingContext.get().getModEventBus());
        NetworkHandler.registerMessage();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::imc);
    }

    public static boolean isCuriosLoaded() {
        return curiosLoaded;
    }

    public void imc(InterModEnqueueEvent event) {
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> new SlotTypeMessage.Builder("glasses")
                .size(1)
                .priority(30)
                .icon(new ResourceLocation("glasses", "slot/empty_glasses_slot"))
                .build());
    }
}
