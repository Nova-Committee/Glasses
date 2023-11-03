package team.dovecotmc.glasses.client.integration.curios;

import dev.emi.trinkets.api.client.TrinketRendererRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import team.dovecotmc.glasses.common.item.base.GlassesItem;

public class TrinketsClientIntegration {
    public static void init() {
        BuiltInRegistries.ITEM.entrySet().forEach(e -> {
            final Item i = e.getValue();
            if (!(i instanceof GlassesItem)) return;
            TrinketRendererRegistry.registerRenderer(i, new GlassesRenderer());
        });
    }
}
