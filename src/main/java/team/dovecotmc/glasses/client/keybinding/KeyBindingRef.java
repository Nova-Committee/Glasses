package team.dovecotmc.glasses.client.keybinding;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import team.dovecotmc.glasses.Glasses;
import team.dovecotmc.glasses.client.integration.curios.GlassesRenderer;
import team.dovecotmc.glasses.util.client.ClientUtilities;
import team.dovecotmc.glasses.util.common.CommonUtilities;

import java.util.function.Consumer;
import java.util.function.Supplier;

public enum KeyBindingRef implements Supplier<KeyMapping> {
    GLASSES_ACTION(new KeyMapping("key.glasses.glasses_use",
            InputConstants.KEY_V, "key.glasses.category"), mc -> {
        final Player player = mc.player;
        if (player == null) return;
        CommonUtilities.getMatchedWearingItem(player, CommonUtilities.GLASSES)
                .ifPresent($ -> ClientPlayNetworking.send(Glasses.GLASSES_USE, PacketByteBufs.create()));
    }, false),
    GLASSES_OFFSET_X_PLUS(new KeyMapping("key.glasses.offset.x_plus",
            InputConstants.KEY_LEFT, "key.glasses.category"),
            mc -> ClientUtilities.modifyOffset4GlassesWorn(mc.player, r -> r.modifyOffset(o -> o.x += 0.01)),
            true),
    GLASSES_OFFSET_X_MINUS(new KeyMapping("key.glasses.offset.x_minus",
            InputConstants.KEY_RIGHT, "key.glasses.category"),
            mc -> ClientUtilities.modifyOffset4GlassesWorn(mc.player, r -> r.modifyOffset(o -> o.x -= 0.01)),
            true),
    GLASSES_OFFSET_Y_PLUS(new KeyMapping("key.glasses.offset.y_plus",
            InputConstants.KEY_UP, "key.glasses.category"),
            mc -> ClientUtilities.modifyOffset4GlassesWorn(mc.player, r -> r.modifyOffset(o -> o.y += 0.01)),
            true),
    GLASSES_OFFSET_Y_MINUS(new KeyMapping("key.glasses.offset.y_minus",
            InputConstants.KEY_DOWN, "key.glasses.category"),
            mc -> ClientUtilities.modifyOffset4GlassesWorn(mc.player, r -> r.modifyOffset(o -> o.y -= 0.01)),
            true),
    GLASSES_OFFSET_Z_PLUS(new KeyMapping("key.glasses.offset.z_plus",
            InputConstants.KEY_PAGEUP, "key.glasses.category"),
            mc -> ClientUtilities.modifyOffset4GlassesWorn(mc.player, r -> r.modifyOffset(o -> o.z += 0.01)),
            true),
    GLASSES_OFFSET_Z_MINUS(new KeyMapping("key.glasses.offset.z_minus",
            InputConstants.KEY_PAGEDOWN, "key.glasses.category"),
            mc -> ClientUtilities.modifyOffset4GlassesWorn(mc.player, r -> r.modifyOffset(o -> o.z -= 0.01)),
            true),
    GLASSES_OFFSET_X_ROT_PLUS(new KeyMapping("key.glasses.offset.x_rot_plus",
            InputConstants.KEY_NUMPAD3, "key.glasses.category"),
            mc -> ClientUtilities.modifyOffset4GlassesWorn(mc.player, r -> r.modifyOffset(o -> {
                o.xRot += 0.01f;
                o.xRot %= (float) Math.PI * 2.0f;
            })),
            true),
    GLASSES_OFFSET_X_ROT_MINUS(new KeyMapping("key.glasses.offset.x_rot_minus",
            InputConstants.KEY_NUMPAD1, "key.glasses.category"),
            mc -> ClientUtilities.modifyOffset4GlassesWorn(mc.player, r -> r.modifyOffset(o -> {
                o.xRot -= 0.01f;
                o.xRot %= (float) Math.PI * 2.0f;
            })),
            true),
    GLASSES_OFFSET_Y_ROT_PLUS(new KeyMapping("key.glasses.offset.y_rot_plus",
            InputConstants.KEY_NUMPAD6, "key.glasses.category"),
            mc -> ClientUtilities.modifyOffset4GlassesWorn(mc.player, r -> r.modifyOffset(o -> {
                o.yRot += 0.01f;
                o.yRot %= (float) Math.PI * 2.0f;
            })),
            true),
    GLASSES_OFFSET_Y_ROT_MINUS(new KeyMapping("key.glasses.offset.y_rot_minus",
            InputConstants.KEY_NUMPAD4, "key.glasses.category"),
            mc -> ClientUtilities.modifyOffset4GlassesWorn(mc.player, r -> r.modifyOffset(o -> {
                o.yRot -= 0.01f;
                o.yRot %= (float) Math.PI * 2.0f;
            })),
            true),
    GLASSES_OFFSET_Z_ROT_PLUS(new KeyMapping("key.glasses.offset.z_rot_plus",
            InputConstants.KEY_NUMPAD9, "key.glasses.category"),
            mc -> ClientUtilities.modifyOffset4GlassesWorn(mc.player, r -> r.modifyOffset(o -> {
                o.zRot += 0.01f;
                o.zRot %= (float) Math.PI * 2.0f;
            })),
            true),
    GLASSES_OFFSET_Z_ROT_MINUS(new KeyMapping("key.glasses.offset.z_rot_minus",
            InputConstants.KEY_NUMPAD7, "key.glasses.category"),
            mc -> ClientUtilities.modifyOffset4GlassesWorn(mc.player, r -> r.modifyOffset(o -> {
                o.zRot -= 0.01f;
                o.zRot %= (float) Math.PI * 2.0f;
            })),
            true),
    GLASSES_OFFSET_SAVE(new KeyMapping("key.glasses.offset.save",
            InputConstants.KEY_NUMPADENTER, "key.glasses.category"),
            mc -> ClientUtilities.modifyOffset4GlassesWorn(mc.player, GlassesRenderer::saveOffset),
            true),
    GLASSES_OFFSET_RELOAD(new KeyMapping("key.glasses.offset.reload",
            InputConstants.KEY_BACKSPACE, "key.glasses.category"),
            mc -> ClientUtilities.modifyOffset4GlassesWorn(mc.player, GlassesRenderer::reloadOffset),
            true);

    KeyBindingRef(KeyMapping key, Consumer<Minecraft> action, boolean needsTrinkets) {
        this.key = key;
        this.action = action;
        this.needsTrinkets = needsTrinkets;
    }

    private final KeyMapping key;
    private final Consumer<Minecraft> action;
    private final boolean needsTrinkets;

    @Override
    public KeyMapping get() {
        return key;
    }

    public Consumer<Minecraft> getAction() {
        return action;
    }

    public boolean needsTrinkets() {
        return needsTrinkets;
    }
}
