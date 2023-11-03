package team.dovecotmc.glasses.common.info;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public class Artisans {
    public static final Component TAPIO = getTranslatableArtisanName("tapio").withStyle(ChatFormatting.GOLD);
    public static final Component GREYGOD = getTranslatableArtisanName("greygod").withStyle(ChatFormatting.AQUA);


    private static MutableComponent getTranslatableArtisanName(String id) {
        return Component.translatable("artisan.glasses." + id);
    }
}
