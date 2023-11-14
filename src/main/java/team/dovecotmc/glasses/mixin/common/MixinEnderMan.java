package team.dovecotmc.glasses.mixin.common;

import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import team.dovecotmc.glasses.util.common.CommonUtilities;

@Mixin(EnderMan.class)
public abstract class MixinEnderMan {
    @Inject(method = "isLookingAtMe", at = @At("HEAD"), cancellable = true)
    private void inject$isLookingAtMe(Player player, CallbackInfoReturnable<Boolean> cir) {
        if (CommonUtilities.isWearing(player, CommonUtilities.SUNGLASSES)) cir.setReturnValue(false);
    }
}
