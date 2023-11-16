package team.dovecotmc.glasses.client.keybinding;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;
import team.dovecotmc.glasses.Glasses;
import team.dovecotmc.glasses.client.integration.curios.GlassesRenderer;
import team.dovecotmc.glasses.common.network.handler.NetworkHandler;
import team.dovecotmc.glasses.common.network.msg.GlassesUseMessage;
import team.dovecotmc.glasses.util.client.ClientUtilities;
import team.dovecotmc.glasses.util.common.CommonUtilities;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public enum KeyBindingRef implements Supplier<KeyMapping> {
    GLASSES_ACTION(new KeyMapping("key.glasses.glasses_use",
            KeyConflictContext.IN_GAME,
            KeyModifier.NONE,
            InputConstants.Type.KEYSYM,
            InputConstants.KEY_V, "key.glasses.category"), mc -> {
        final Player player = mc.player;
        if (player == null) return;
        CommonUtilities.getMatchedWearingItem(player, CommonUtilities.GLASSES)
                .ifPresent($ -> NetworkHandler.INSTANCE.sendToServer(new GlassesUseMessage()));
    }, false),
    GLASSES_OFFSET_X_PLUS(new KeyMapping("key.glasses.offset.x_plus",
            KeyConflictContext.IN_GAME,
            KeyModifier.NONE,
            InputConstants.Type.KEYSYM,
            InputConstants.KEY_LEFT, "key.glasses.category"),
            mc -> ClientUtilities.modifyOffset4GlassesWorn(mc.player, r -> r.modifyOffset(o -> o.x += 0.01)),
            true),
    GLASSES_OFFSET_X_MINUS(new KeyMapping("key.glasses.offset.x_minus",
            KeyConflictContext.IN_GAME,
            KeyModifier.NONE,
            InputConstants.Type.KEYSYM,
            InputConstants.KEY_RIGHT, "key.glasses.category"),
            mc -> ClientUtilities.modifyOffset4GlassesWorn(mc.player, r -> r.modifyOffset(o -> o.x -= 0.01)),
            true),
    GLASSES_OFFSET_Y_PLUS(new KeyMapping("key.glasses.offset.y_plus",
            KeyConflictContext.IN_GAME,
            KeyModifier.NONE,
            InputConstants.Type.KEYSYM,
            InputConstants.KEY_UP, "key.glasses.category"),
            mc -> ClientUtilities.modifyOffset4GlassesWorn(mc.player, r -> r.modifyOffset(o -> o.y += 0.01)),
            true),
    GLASSES_OFFSET_Y_MINUS(new KeyMapping("key.glasses.offset.y_minus",
            KeyConflictContext.IN_GAME,
            KeyModifier.NONE,
            InputConstants.Type.KEYSYM,
            InputConstants.KEY_DOWN, "key.glasses.category"),
            mc -> ClientUtilities.modifyOffset4GlassesWorn(mc.player, r -> r.modifyOffset(o -> o.y -= 0.01)),
            true),
    GLASSES_OFFSET_Z_PLUS(new KeyMapping("key.glasses.offset.z_plus",
            KeyConflictContext.IN_GAME,
            KeyModifier.NONE,
            InputConstants.Type.KEYSYM,
            InputConstants.KEY_PAGEUP, "key.glasses.category"),
            mc -> ClientUtilities.modifyOffset4GlassesWorn(mc.player, r -> r.modifyOffset(o -> o.z += 0.01)),
            true),
    GLASSES_OFFSET_Z_MINUS(new KeyMapping("key.glasses.offset.z_minus",
            KeyConflictContext.IN_GAME,
            KeyModifier.NONE,
            InputConstants.Type.KEYSYM,
            InputConstants.KEY_PAGEDOWN, "key.glasses.category"),
            mc -> ClientUtilities.modifyOffset4GlassesWorn(mc.player, r -> r.modifyOffset(o -> o.z -= 0.01)),
            true),
    GLASSES_OFFSET_X_ROT_PLUS(new KeyMapping("key.glasses.offset.x_rot_plus",
            KeyConflictContext.IN_GAME,
            KeyModifier.NONE,
            InputConstants.Type.KEYSYM,
            InputConstants.KEY_NUMPAD3, "key.glasses.category"),
            mc -> ClientUtilities.modifyOffset4GlassesWorn(mc.player, r -> r.modifyOffset(o -> {
                o.xRot += 0.01f;
                o.xRot %= (float) Math.PI * 2.0f;
            })),
            true),
    GLASSES_OFFSET_X_ROT_MINUS(new KeyMapping("key.glasses.offset.x_rot_minus",
            KeyConflictContext.IN_GAME,
            KeyModifier.NONE,
            InputConstants.Type.KEYSYM,
            InputConstants.KEY_NUMPAD1, "key.glasses.category"),
            mc -> ClientUtilities.modifyOffset4GlassesWorn(mc.player, r -> r.modifyOffset(o -> {
                o.xRot -= 0.01f;
                o.xRot %= (float) Math.PI * 2.0f;
            })),
            true),
    GLASSES_OFFSET_Y_ROT_PLUS(new KeyMapping("key.glasses.offset.y_rot_plus",
            KeyConflictContext.IN_GAME,
            KeyModifier.NONE,
            InputConstants.Type.KEYSYM,
            InputConstants.KEY_NUMPAD6, "key.glasses.category"),
            mc -> ClientUtilities.modifyOffset4GlassesWorn(mc.player, r -> r.modifyOffset(o -> {
                o.yRot += 0.01f;
                o.yRot %= (float) Math.PI * 2.0f;
            })),
            true),
    GLASSES_OFFSET_Y_ROT_MINUS(new KeyMapping("key.glasses.offset.y_rot_minus",
            KeyConflictContext.IN_GAME,
            KeyModifier.NONE,
            InputConstants.Type.KEYSYM,
            InputConstants.KEY_NUMPAD4, "key.glasses.category"),
            mc -> ClientUtilities.modifyOffset4GlassesWorn(mc.player, r -> r.modifyOffset(o -> {
                o.yRot -= 0.01f;
                o.yRot %= (float) Math.PI * 2.0f;
            })),
            true),
    GLASSES_OFFSET_Z_ROT_PLUS(new KeyMapping("key.glasses.offset.z_rot_plus",
            KeyConflictContext.IN_GAME,
            KeyModifier.NONE,
            InputConstants.Type.KEYSYM,
            InputConstants.KEY_NUMPAD9, "key.glasses.category"),
            mc -> ClientUtilities.modifyOffset4GlassesWorn(mc.player, r -> r.modifyOffset(o -> {
                o.zRot += 0.01f;
                o.zRot %= (float) Math.PI * 2.0f;
            })),
            true),
    GLASSES_OFFSET_Z_ROT_MINUS(new KeyMapping("key.glasses.offset.z_rot_minus",
            KeyConflictContext.IN_GAME,
            KeyModifier.NONE,
            InputConstants.Type.KEYSYM,
            InputConstants.KEY_NUMPAD7, "key.glasses.category"),
            mc -> ClientUtilities.modifyOffset4GlassesWorn(mc.player, r -> r.modifyOffset(o -> {
                o.zRot -= 0.01f;
                o.zRot %= (float) Math.PI * 2.0f;
            })),
            true),
    GLASSES_OFFSET_SAVE(new KeyMapping("key.glasses.offset.save",
            KeyConflictContext.IN_GAME,
            KeyModifier.NONE,
            InputConstants.Type.KEYSYM,
            InputConstants.KEY_NUMPADENTER, "key.glasses.category"),
            mc -> ClientUtilities.modifyOffset4GlassesWorn(mc.player, GlassesRenderer::saveOffset),
            true),
    GLASSES_OFFSET_RELOAD(new KeyMapping("key.glasses.offset.reload",
            KeyConflictContext.IN_GAME,
            KeyModifier.NONE,
            InputConstants.Type.KEYSYM,
            InputConstants.KEY_BACKSPACE, "key.glasses.category"),
            mc -> ClientUtilities.modifyOffset4GlassesWorn(mc.player, GlassesRenderer::reloadOffset),
            true);

    KeyBindingRef(KeyMapping key, Consumer<Minecraft> action, boolean needsCurios) {
        this.key = key;
        this.action = action;
        this.needsCurios = needsCurios;
    }

    private static List<KeyBindingRef> functioning;

    public static List<KeyBindingRef> getFunctioning() {
        if (functioning == null) functioning = List.copyOf(Arrays.stream(KeyBindingRef.values())
                .filter(r -> !r.needsCurios() || Glasses.isCuriosLoaded())
                .toList());
        return functioning;
    }

    private final KeyMapping key;
    private final Consumer<Minecraft> action;
    private final boolean needsCurios;

    @Override
    public KeyMapping get() {
        return key;
    }

    public Consumer<Minecraft> getAction() {
        return action;
    }

    public boolean needsCurios() {
        return needsCurios;
    }
}
