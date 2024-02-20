package team.dovecotmc.glasses.common.info;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.ItemStack;
import team.dovecotmc.glasses.common.ref.ItemRef;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public enum Artisan {
    GREYGOD(
            "greygod",
            Style.EMPTY.applyFormat(ChatFormatting.AQUA),
            UUID.fromString("6330f713-a08f-4f9e-83ab-4e0d73a8743f"),
            () -> List.of(ItemRef.GLASSES_7.get().getDefaultInstance())
    ),
    TAPIO(
            "tapio",
            Style.EMPTY.applyFormat(ChatFormatting.GOLD),
            UUID.fromString("932adb0c-597c-482b-ab5a-296a9a9fee65"),
            () -> List.of(ItemRef.GLASSES_4.get().getDefaultInstance())
    ),
    SHOU_ZHANG(
            "shou_zhang",
            Style.EMPTY.applyFormat(ChatFormatting.BLUE),
            UUID.fromString("941c9a4f-55b0-4201-8d99-60fe3065cbee"),
            () -> List.of(ItemRef.GLASSES_11.get().getDefaultInstance())
    );

    Artisan(String id, Style style, UUID uuid, Supplier<List<ItemStack>> glassesToGive) {
        this.id = id;
        this.style = style;
        this.uuid = uuid;
        this.glassesToGive = glassesToGive;
    }

    private final String id;
    private final Style style;
    private final UUID uuid;
    private final Supplier<List<ItemStack>> glassesToGive;

    public Component getName() {
        return new TranslatableComponent("artisan.glasses." + id).withStyle(style);
    }

    public UUID getUuid() {
        return uuid;
    }

    public List<ItemStack> getGlassesToGive() {
        return glassesToGive.get();
    }

    public static class Cache {
        private static Map<UUID, List<ItemStack>> glassesToGive;

        private static void initGlassesToGive() {
            if (glassesToGive == null) glassesToGive = Arrays.stream(Artisan.values())
                    .collect(Collectors.toMap(a -> a.uuid, Artisan::getGlassesToGive));
        }

        public static List<ItemStack> getGlassesToGive(UUID uuid) {
            initGlassesToGive();
            return glassesToGive.getOrDefault(uuid, Collections.emptyList());
        }

        public static boolean shouldGiveGlasses(UUID uuid) {
            initGlassesToGive();
            return glassesToGive.containsKey(uuid);
        }
    }
}
