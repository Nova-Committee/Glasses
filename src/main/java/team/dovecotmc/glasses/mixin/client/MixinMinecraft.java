package team.dovecotmc.glasses.mixin.client;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import team.dovecotmc.glasses.common.ref.ItemRef;
import team.dovecotmc.glasses.util.common.CommonUtilities;

import java.util.concurrent.atomic.AtomicBoolean;

@Mixin(Minecraft.class)
public abstract class MixinMinecraft {
    @Inject(method = "shouldEntityAppearGlowing", at = @At("RETURN"), cancellable = true)
    private void inject$shouldEntityAppearGlowing(Entity e, CallbackInfoReturnable<Boolean> cir) {
        if (cir.getReturnValue() || Minecraft.getInstance().player == null) return;
        final AtomicBoolean b = new AtomicBoolean(false);
        CommonUtilities.getMatchedWearingItem(Minecraft.getInstance().player, s -> s.is(ItemRef.GLASSES_9.get()))
                .ifPresent(s -> b.set(s.getOrCreateTag().getBoolean("glasses_using")
                        && Minecraft.getInstance().options.getCameraType().isFirstPerson()));
        cir.setReturnValue(b.get());
    }
}
