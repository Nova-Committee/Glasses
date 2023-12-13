package team.dovecotmc.glasses.util.common;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemHandlerHelper;
import team.dovecotmc.glasses.Glasses;
import team.dovecotmc.glasses.common.integration.curios.CuriosComponentProvider;
import team.dovecotmc.glasses.common.item.base.GlassesItem;
import team.dovecotmc.glasses.common.item.impl.SunglassesItem;
import team.dovecotmc.glasses.common.ref.ItemRef;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class CommonUtilities {
    public static final ResourceLocation ADV_ARTISAN = new ResourceLocation(Glasses.MODID, "artisan");
    public static final Predicate<ItemStack> GLASSES = s -> s.getItem() instanceof GlassesItem;
    public static final Predicate<ItemStack> SUNGLASSES = s -> s.getItem() instanceof SunglassesItem;
    public static final Predicate<ItemStack> MONOCULAR = s -> s.is(ItemRef.GLASSES_3.get());

    public static boolean isWearing(Player player, Predicate<ItemStack> filter) {
        return getMatchedWearingItem(player, filter).isPresent();
    }

    public static Optional<ItemStack> getMatchedWearingItem(Player player, Predicate<ItemStack> filter) {
        if (Glasses.isCuriosLoaded()) return CuriosComponentProvider.getMatchedWearingItem(player, filter);
        final ItemStack head = player.getItemBySlot(EquipmentSlot.HEAD);
        return filter.test(head) ? Optional.of(head) : Optional.empty();
    }

    public static void giveItemsToPlayer(Player player, List<ItemStack> stacks) {
        for (final ItemStack stack : stacks) ItemHandlerHelper.giveItemToPlayer(player, stack);
    }
}
