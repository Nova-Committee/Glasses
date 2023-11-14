package team.dovecotmc.glasses.client.integration.curios;

import dev.emi.trinkets.api.client.TrinketRendererRegistry;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import team.dovecotmc.glasses.client.keybinding.KeyBindingRef;
import team.dovecotmc.glasses.common.item.base.GlassesItem;
import team.dovecotmc.glasses.util.client.ClientUtilities;
import team.dovecotmc.glasses.util.common.CommonUtilities;

import java.util.Arrays;

public class TrinketsClientIntegration {
    public static void init() {
        BuiltInRegistries.ITEM.entrySet().forEach(e -> {
            final Item i = e.getValue();
            if (!(i instanceof GlassesItem)) return;
            TrinketRendererRegistry.registerRenderer(i, new GlassesRenderer(i));
        });
        ClientTickEvents.END_CLIENT_TICK.register(mc -> {
            if (mc.player == null) return;
            CommonUtilities.getMatchedWearingItem(mc.player, CommonUtilities.GLASSES).ifPresent(s -> {
                if (!(TrinketRendererRegistry.getRenderer(s.getItem()).orElse(null) instanceof GlassesRenderer g))
                    return;
                if (!g.isSaved()) ClientUtilities.remindPlayer(mc.player, s);
            });
        });
        Arrays.stream(KeyBindingRef.values()).filter(KeyBindingRef::needsTrinkets).forEach(r -> {
            final KeyMapping k = r.get();
            KeyBindingHelper.registerKeyBinding(k);
            ClientTickEvents.END_CLIENT_TICK.register(mc -> {
                while (k.consumeClick()) r.getAction().accept(mc);
            });
        });
    }
}
