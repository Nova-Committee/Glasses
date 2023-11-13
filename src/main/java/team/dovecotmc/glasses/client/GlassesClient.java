package team.dovecotmc.glasses.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import team.dovecotmc.glasses.Glasses;
import team.dovecotmc.glasses.client.integration.curios.TrinketsClientIntegration;
import team.dovecotmc.glasses.client.keybinding.KeyBindings;
import team.dovecotmc.glasses.util.Utilities;

public class GlassesClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        if (Glasses.isTrinketsLoaded()) TrinketsClientIntegration.init();
        KeyBindingHelper.registerKeyBinding(KeyBindings.GLASSES_ACTION);
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (KeyBindings.GLASSES_ACTION.consumeClick()) {
                final Player player = Minecraft.getInstance().player;
                if (player == null) return;
                Utilities.getMatchedWearingItem(player, Utilities.MONOCULAR)
                        .ifPresent($ -> ClientPlayNetworking.send(Glasses.GLASSES_USE, PacketByteBufs.create()));
            }
        });
    }
}
