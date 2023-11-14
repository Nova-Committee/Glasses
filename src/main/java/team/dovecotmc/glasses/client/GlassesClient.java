package team.dovecotmc.glasses.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import team.dovecotmc.glasses.Glasses;
import team.dovecotmc.glasses.client.integration.curios.TrinketsClientIntegration;
import team.dovecotmc.glasses.client.keybinding.KeyBindingRef;

import java.util.Arrays;

public class GlassesClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        if (Glasses.isTrinketsLoaded()) TrinketsClientIntegration.init();
        Arrays.stream(KeyBindingRef.values()).filter(r -> !r.needsTrinkets()).forEach(r -> {
            final KeyMapping k = r.get();
            KeyBindingHelper.registerKeyBinding(k);
            ClientTickEvents.END_CLIENT_TICK.register(mc -> {
                while (k.consumeClick()) r.getAction().accept(mc);
            });
        });
    }
}
