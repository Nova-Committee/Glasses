package team.dovecotmc.glasses.client.integration.curios;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.registries.ForgeRegistries;
import team.dovecotmc.glasses.common.item.base.GlassesItem;
import team.dovecotmc.glasses.util.client.ClientUtilities;
import team.dovecotmc.glasses.util.common.CommonUtilities;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

import java.util.function.Consumer;

public class CuriosClientIntegration {
    public static void init() {
        ForgeRegistries.ITEMS.getValues().parallelStream().forEach(i -> {
            if (!(i instanceof GlassesItem)) return;
            CuriosRendererRegistry.register(i, () -> new GlassesRenderer(i));
        });
    }

    public static void remindPlayer(Player player) {
        CommonUtilities.getMatchedWearingItem(player, CommonUtilities.GLASSES).ifPresent(s -> {
            if (!(CuriosRendererRegistry.getRenderer(s.getItem()).orElse(null) instanceof GlassesRenderer g))
                return;
            if (!g.isSaved()) ClientUtilities.remindPlayer(player, s);
        });
    }

    public static void modifyOffset(Player player, Consumer<GlassesRenderer> modifier) {
        CommonUtilities.getMatchedWearingItem(player, CommonUtilities.GLASSES).ifPresent(s -> {
            if (!(CuriosRendererRegistry.getRenderer(s.getItem()).orElse(null) instanceof GlassesRenderer r)) return;
            modifier.accept(r);
        });
    }
}
