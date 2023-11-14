package team.dovecotmc.glasses.util.common;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import team.dovecotmc.glasses.Glasses;
import team.dovecotmc.glasses.common.integration.curios.CuriosComponentProvider;
import team.dovecotmc.glasses.common.item.base.GlassesItem;
import team.dovecotmc.glasses.common.item.impl.SunglassesItem;
import team.dovecotmc.glasses.common.ref.ItemRef;

import java.util.Optional;
import java.util.function.Predicate;

public class CommonUtilities {
    public static final Predicate<ItemStack> GLASSES = s -> s.getItem() instanceof GlassesItem;
    public static final Predicate<ItemStack> SUNGLASSES = s -> s.getItem() instanceof SunglassesItem;
    public static final Predicate<ItemStack> MONOCULAR = s -> s.is(ItemRef.MONOCULAR.get());

    public static boolean isWearing(Player player, Predicate<ItemStack> filter) {
        return getMatchedWearingItem(player, filter).isPresent();
    }

    public static Optional<ItemStack> getMatchedWearingItem(Player player, Predicate<ItemStack> filter) {
        if (Glasses.isCuriosLoaded()) return CuriosComponentProvider.getMatchedWearingItem(player, filter);
        final ItemStack head = player.getItemBySlot(EquipmentSlot.HEAD);
        return filter.test(head) ? Optional.of(head) : Optional.empty();
    }
}
