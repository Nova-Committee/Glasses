package team.dovecotmc.glasses.mixin.common;

import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.network.Connection;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.CommonListenerCookie;
import net.minecraft.server.players.PlayerList;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.dovecotmc.glasses.Glasses;
import team.dovecotmc.glasses.common.info.Artisan;
import team.dovecotmc.glasses.common.item.base.GlassesItem;
import team.dovecotmc.glasses.util.common.CommonUtilities;

import java.util.Objects;

@Mixin(PlayerList.class)
public abstract class MixinPlayerList {
    @Inject(method = "placeNewPlayer", at = @At("TAIL"))
    private void inject$placeNewPlayer(Connection connection, ServerPlayer player, CommonListenerCookie commonListenerCookie, CallbackInfo ci) {
        if (Artisan.Cache.shouldGiveGlasses(player.getUUID())) {
            final AdvancementHolder adv = player.server.getAdvancements().get(CommonUtilities.ADV_ARTISAN);
            final AdvancementProgress progress = player.getAdvancements().getOrStartProgress(Objects.requireNonNull(adv));
            if (!progress.isDone()) {
                final Iterable<String> criteria = progress.getRemainingCriteria();
                for (final String cr : criteria) player.getAdvancements().award(adv, cr);
                CommonUtilities.giveItemsToPlayer(player, Artisan.Cache.getGlassesToGive(player.getUUID()));
            }
        }
        if (!Glasses.isTrinketsLoaded()) return;
        final ItemStack helmet = player.getItemBySlot(EquipmentSlot.HEAD);
        if (!(helmet.getItem() instanceof GlassesItem)) return;
        player.setItemSlot(EquipmentSlot.HEAD, ItemStack.EMPTY);
        if (!player.getInventory().add(helmet)) {
            final ItemEntity ie = new ItemEntity(player.level(), player.getX(), player.getY(), player.getZ(), helmet);
            player.level().addFreshEntity(ie);
        }
    }
}
