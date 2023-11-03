package team.dovecotmc.glasses.mixin.client;

import net.minecraft.client.MouseHandler;
import net.minecraft.client.player.LocalPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import team.dovecotmc.glasses.util.Utilities;

import java.util.concurrent.atomic.AtomicBoolean;

@Mixin(MouseHandler.class)
public abstract class MixinMouseHandler {
    @Redirect(method = "turnPlayer", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;isScoping()Z"))
    private boolean redirect$turnPlayer(LocalPlayer instance) {
        if (instance.isScoping()) return true;
        final AtomicBoolean b = new AtomicBoolean(false);
        Utilities.getMatchedWearingItem(instance, Utilities.MONOCULAR).ifPresent(m -> {
            if (m.getOrCreateTag().getBoolean("glasses_using")) b.set(true);
        });
        return b.get();
    }
}