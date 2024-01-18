package team.dovecotmc.glasses.common.event.handler;

import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import team.dovecotmc.glasses.Glasses;
import team.dovecotmc.glasses.common.info.Artisan;
import team.dovecotmc.glasses.common.item.base.GlassesItem;
import team.dovecotmc.glasses.util.common.CommonUtilities;

import java.util.Objects;

@Mod.EventBusSubscriber
public class ForgeEventHandler {
    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        final Player player = event.getEntity();
        if (player instanceof ServerPlayer sp && Artisan.Cache.shouldGiveGlasses(sp.getUUID())) {
            final AdvancementHolder adv = sp.server.getAdvancements().get(CommonUtilities.ADV_ARTISAN);
            final AdvancementProgress progress = sp.getAdvancements().getOrStartProgress(Objects.requireNonNull(adv));
            if (!progress.isDone()) {
                final Iterable<String> criteria = progress.getRemainingCriteria();
                for (final String cr : criteria) sp.getAdvancements().award(adv, cr);
                CommonUtilities.giveItemsToPlayer(sp, Artisan.Cache.getGlassesToGive(sp.getUUID()));
            }
        }
        if (!Glasses.isCuriosLoaded()) return;
        final ItemStack helmet = player.getItemBySlot(EquipmentSlot.HEAD);
        if (!(helmet.getItem() instanceof GlassesItem)) return;
        player.setItemSlot(EquipmentSlot.HEAD, ItemStack.EMPTY);
        if (!player.getInventory().add(helmet)) {
            final ItemEntity ie = new ItemEntity(player.level(), player.getX(), player.getY(), player.getZ(), helmet);
            player.level().addFreshEntity(ie);
        }
    }
}
