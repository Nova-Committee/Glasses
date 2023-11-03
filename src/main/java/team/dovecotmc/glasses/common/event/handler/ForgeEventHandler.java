package team.dovecotmc.glasses.common.event.handler;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import team.dovecotmc.glasses.Glasses;
import team.dovecotmc.glasses.common.item.base.GlassesItem;

@Mod.EventBusSubscriber
public class ForgeEventHandler {
    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        if (!Glasses.isCuriosLoaded()) return;
        final Player player = event.getEntity();
        final ItemStack helmet = player.getItemBySlot(EquipmentSlot.HEAD);
        if (!(helmet.getItem() instanceof GlassesItem)) return;
        player.setItemSlot(EquipmentSlot.HEAD, ItemStack.EMPTY);
        if (!player.getInventory().add(helmet)) {
            final ItemEntity ie = new ItemEntity(player.level(), player.getX(), player.getY(), player.getZ(), helmet);
            player.level().addFreshEntity(ie);
        }
    }
}
