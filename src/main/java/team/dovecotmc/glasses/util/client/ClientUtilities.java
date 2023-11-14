package team.dovecotmc.glasses.util.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.commons.io.FileUtils;
import team.dovecotmc.glasses.Glasses;
import team.dovecotmc.glasses.client.config.DisplayOffset;
import team.dovecotmc.glasses.client.integration.curios.GlassesRenderer;
import team.dovecotmc.glasses.client.keybinding.KeyBindingRef;
import team.dovecotmc.glasses.util.common.CommonUtilities;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Consumer;

public class ClientUtilities {
    public static final Path glassesDir = FMLPaths.CONFIGDIR.get().resolve("glasses");
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    public static void modifyOffset4GlassesWorn(Player player, Consumer<GlassesRenderer> modifier) {
        if (!Glasses.isCuriosLoaded() || player == null) return;
        CommonUtilities.getMatchedWearingItem(player, CommonUtilities.GLASSES).ifPresent(s -> {
            if (!(CuriosRendererRegistry.getRenderer(s.getItem()).orElse(null) instanceof GlassesRenderer r)) return;
            modifier.accept(r);
        });
    }

    public static Optional<DisplayOffset> getOffsetConfig(String glassesName) {
        if (!glassesDir.toFile().isDirectory()) {
            try {
                Files.createDirectories(glassesDir);
            } catch (IOException e) {
                Glasses.LOGGER.error("Failed to create directory for display offset configs!", e);
                return Optional.empty();
            }
        }
        final Path cfgPath = getGlassesOffsetPath(glassesName);
        DisplayOffset config = new DisplayOffset();
        if (cfgPath.toFile().isFile()) {
            try {
                config = GSON.fromJson(FileUtils.readFileToString(cfgPath.toFile(),
                        StandardCharsets.UTF_8), DisplayOffset.class);
                return Optional.of(config);
            } catch (IOException e) {
                Glasses.LOGGER.error(String.format("Failed to parse display offset config \"%s\"", cfgPath), e);
                FileUtils.deleteQuietly(cfgPath.toFile());
                return createOffsetConfig(cfgPath, config) ? Optional.of(config) : Optional.empty();
            }
        } else return createOffsetConfig(cfgPath, config) ? Optional.of(config) : Optional.empty();
    }

    public static boolean createOffsetConfig(Path cfgPath, DisplayOffset config) {
        try {
            FileUtils.write(cfgPath.toFile(), GSON.toJson(config), StandardCharsets.UTF_8);
            return true;
        } catch (IOException e) {
            Glasses.LOGGER.error(String.format("Failed to create display offset config \"%s\"", cfgPath), e);
        }
        return false;
    }

    public static void saveOffsetConfig(Path cfgPath, DisplayOffset config) {
        try {
            FileUtils.write(cfgPath.toFile(), GSON.toJson(config), StandardCharsets.UTF_8);
        } catch (IOException e) {
            Glasses.LOGGER.error(String.format("Failed to save display offset config \"%s\"", cfgPath), e);
        }
    }

    public static Path getGlassesOffsetPath(String glassesName) {
        return glassesDir.resolve(String.format("%s.json", glassesName));
    }

    public static void remindPlayer(Player player, ItemStack stack) {
        player.displayClientMessage(Component.translatable("msg.glasses.offset.needs_save",
                Component.translatable(stack.getItem().getDescriptionId()),
                KeyBindingRef.GLASSES_OFFSET_SAVE.get().getKey().getDisplayName(),
                KeyBindingRef.GLASSES_OFFSET_RELOAD.get().getKey().getDisplayName()
        ).withStyle(ChatFormatting.AQUA), true);
    }
}