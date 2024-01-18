package team.dovecotmc.glasses.client.event.handler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.ComputeFovModifierEvent;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.client.event.RenderPlayerEvent;
import net.neoforged.neoforge.event.TickEvent;
import team.dovecotmc.glasses.Glasses;
import team.dovecotmc.glasses.client.integration.curios.CuriosClientIntegration;
import team.dovecotmc.glasses.client.keybinding.KeyBindingRef;
import team.dovecotmc.glasses.util.common.CommonUtilities;

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
        KeyBindingRef.getFunctioning().forEach(r -> {
            if (r.get().consumeClick()) r.getAction().accept(Minecraft.getInstance());
        });
    }

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (!Glasses.isCuriosLoaded()) return;
        if (!event.phase.equals(TickEvent.Phase.END)) return;
        final Player player = Minecraft.getInstance().player;
        if (player == null) return;
        CuriosClientIntegration.remindPlayer(player);
    }
}
