package team.dovecotmc.glasses.client.keybinding;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;

public class KeyBindings {
    public static KeyMapping GLASSES_ACTION = new KeyMapping("key.glasses.glasses_action", KeyConflictContext.UNIVERSAL, KeyModifier.NONE,
            InputConstants.Type.KEYSYM, InputConstants.KEY_X, "key.glasses.category");
}
