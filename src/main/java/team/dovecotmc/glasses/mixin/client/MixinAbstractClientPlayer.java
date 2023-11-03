package team.dovecotmc.glasses.mixin.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.util.Mth;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import team.dovecotmc.glasses.util.Utilities;

import java.util.concurrent.atomic.AtomicReference;

@Mixin(AbstractClientPlayer.class)
public abstract class MixinAbstractClientPlayer {
    @Redirect(method = "getFieldOfViewModifier", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/Mth;lerp(FFF)F"))
    private float inject$getFieldOfViewModifier(float f, float g, float h) {
        AtomicReference<Float> value0 = new AtomicReference<>(Mth.lerp(f, g, h));
        final Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || !mc.options.getCameraType().isFirstPerson()) return value0.get();
        Utilities.getMatchedWearingItem(mc.player, Utilities.MONOCULAR).ifPresent(m -> {
            if (m.getOrCreateTag().getBoolean("glasses_using")) value0.set(value0.get() * 0.2F);
        });
        return value0.get();
    }
}
