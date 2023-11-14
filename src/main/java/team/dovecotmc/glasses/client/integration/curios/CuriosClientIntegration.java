package team.dovecotmc.glasses.client.integration.curios;

import net.minecraftforge.registries.ForgeRegistries;
import team.dovecotmc.glasses.common.item.base.GlassesItem;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

public class CuriosClientIntegration {
    public static void init() {
        ForgeRegistries.ITEMS.getValues().forEach(i -> {
            if (!(i instanceof GlassesItem)) return;
            CuriosRendererRegistry.register(i, () -> new GlassesRenderer(i));
        });
    }
}
