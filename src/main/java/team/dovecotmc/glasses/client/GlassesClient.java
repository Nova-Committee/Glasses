package team.dovecotmc.glasses.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.minecraft.client.KeyMapping;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import team.dovecotmc.glasses.Glasses;
import team.dovecotmc.glasses.client.integration.trinkets.TrinketsClientIntegration;
import team.dovecotmc.glasses.client.keybinding.KeyBindingRef;
import team.dovecotmc.glasses.common.item.base.GlassesItem;

import java.util.Arrays;

public class GlassesClient implements ClientModInitializer {
    @Override
    @SuppressWarnings("deprecation")
    public void onInitializeClient() {
        if (Glasses.isTrinketsLoaded()) TrinketsClientIntegration.init();
        Arrays.stream(KeyBindingRef.values()).filter(r -> !r.needsTrinkets()).forEach(r -> {
            final KeyMapping k = r.get();
            KeyBindingHelper.registerKeyBinding(k);
            ClientTickEvents.END_CLIENT_TICK.register(mc -> {
                while (k.consumeClick()) r.getAction().accept(mc);
            });
        });
        BuiltInRegistries.ITEM.entrySet().parallelStream().forEach(i -> {
            if (!(i instanceof GlassesItem g)) return;
            if (g.getProperties().canUse()) FabricModelPredicateProviderRegistry.register(g, new ResourceLocation("on"),
                    (stack, world, entity, item) -> stack.getOrCreateTag().getBoolean("glasses_using") ? 1 : 0);
        });
    }
}
