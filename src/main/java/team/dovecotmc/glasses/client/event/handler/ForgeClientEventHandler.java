package team.dovecotmc.glasses.client.event.handler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ComputeFovModifierEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import team.dovecotmc.glasses.client.integration.curios.GlassesRenderer;
import team.dovecotmc.glasses.client.keybinding.KeyBindingRef;
import team.dovecotmc.glasses.util.client.ClientUtilities;
import team.dovecotmc.glasses.util.common.CommonUtilities;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

import java.util.Arrays;

@Mod.EventBusSubscriber(Dist.CLIENT)
public class ForgeClientEventHandler {
    @SubscribeEvent
    public static void onFov(ComputeFovModifierEvent event) {
        if (!(event.getPlayer() instanceof LocalPlayer player)) return;
        if (!Minecraft.getInstance().options.getCameraType().isFirstPerson()) return;
        CommonUtilities.getMatchedWearingItem(player, CommonUtilities.MONOCULAR).ifPresent(m -> {
            if (m.getOrCreateTag().getBoolean("glasses_using")) event.setNewFovModifier(.2F * event.getFovModifier());
        });
    }

    @SubscribeEvent
    public static void onRenderPlayer(RenderPlayerEvent.Pre event) {
        CommonUtilities.getMatchedWearingItem(event.getEntity(), CommonUtilities.MONOCULAR).ifPresent(m -> {
            if (m.getOrCreateTag().getBoolean("glasses_using"))
                event.getRenderer().getModel().rightArmPose = HumanoidModel.ArmPose.SPYGLASS;
        });
    }

    @SubscribeEvent
    public static void onKey(InputEvent.Key event) {
        Arrays.stream(KeyBindingRef.values()).forEach(r -> {
            if (r.get().consumeClick()) r.getAction().accept(Minecraft.getInstance());
        });
    }

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (!event.phase.equals(TickEvent.Phase.END)) return;
        final Player player = Minecraft.getInstance().player;
        if (player == null) return;
        CommonUtilities.getMatchedWearingItem(player, CommonUtilities.GLASSES).ifPresent(s -> {
            if (!(CuriosRendererRegistry.getRenderer(s.getItem()).orElse(null) instanceof GlassesRenderer g))
                return;
            if (!g.isSaved()) ClientUtilities.remindPlayer(player, s);
        });
    }
}
