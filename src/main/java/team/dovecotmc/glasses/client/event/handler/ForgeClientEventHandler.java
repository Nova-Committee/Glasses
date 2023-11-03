package team.dovecotmc.glasses.client.event.handler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ComputeFovModifierEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import team.dovecotmc.glasses.client.keybinding.KeyBindings;
import team.dovecotmc.glasses.common.network.handler.NetworkHandler;
import team.dovecotmc.glasses.common.network.msg.GlassesUseMessage;
import team.dovecotmc.glasses.util.Utilities;

@Mod.EventBusSubscriber(Dist.CLIENT)
public class ForgeClientEventHandler {
    @SubscribeEvent
    public static void onFov(ComputeFovModifierEvent event) {
        if (!(event.getPlayer() instanceof LocalPlayer player)) return;
        if (!Minecraft.getInstance().options.getCameraType().isFirstPerson()) return;
        Utilities.getMatchedWearingItem(player, Utilities.MONOCULAR).ifPresent(m -> {
            if (m.getOrCreateTag().getBoolean("glasses_using")) event.setNewFovModifier(.2F * event.getFovModifier());
        });
    }

    @SubscribeEvent
    public static void onRenderPlayer(RenderPlayerEvent.Pre event) {
        Utilities.getMatchedWearingItem(event.getEntity(), Utilities.MONOCULAR).ifPresent(m -> {
            if (m.getOrCreateTag().getBoolean("glasses_using"))
                event.getRenderer().getModel().rightArmPose = HumanoidModel.ArmPose.SPYGLASS;
        });
    }

    @SubscribeEvent
    public static void onKey(InputEvent.Key event) {
        final Player player = Minecraft.getInstance().player;
        if (player == null) return;
        Utilities.getMatchedWearingItem(player, Utilities.MONOCULAR).ifPresent(m -> {
            if (!KeyBindings.GLASSES_ACTION.consumeClick()) return;
            NetworkHandler.INSTANCE.sendToServer(new GlassesUseMessage());
        });
    }
}
