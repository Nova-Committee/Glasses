package team.dovecotmc.glasses.mixin.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import team.dovecotmc.glasses.common.ref.ItemRef;
import team.dovecotmc.glasses.util.common.CommonUtilities;

import java.util.concurrent.atomic.AtomicBoolean;

@Mixin(EntityRenderDispatcher.class)
public abstract class MixinEntityRenderDispatcher {
    @Shadow
    public abstract boolean shouldRenderHitBoxes();

    @Inject(method = "shouldRenderHitBoxes", at = @At("RETURN"), cancellable = true)
    private void inject$shouldRenderHitBoxes(CallbackInfoReturnable<Boolean> cir) {
        if (cir.getReturnValue() || Minecraft.getInstance().player == null) return;
        final AtomicBoolean b = new AtomicBoolean(false);
        CommonUtilities.getMatchedWearingItem(Minecraft.getInstance().player, s -> s.is(ItemRef.GLASSES_9.get()))
                .ifPresent(s -> b.set(s.getOrCreateTag().getBoolean("glasses_using")));
        cir.setReturnValue(b.get());
    }

    @Redirect(method = "render", at = @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/entity/EntityRenderDispatcher;renderHitBoxes:Z"))
    private boolean redirect$render(EntityRenderDispatcher instance) {
        return shouldRenderHitBoxes();
    }
}
