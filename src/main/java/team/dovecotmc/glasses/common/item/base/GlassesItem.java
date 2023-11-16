package team.dovecotmc.glasses.common.item.base;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import team.dovecotmc.glasses.client.keybinding.KeyBindingRef;
import team.dovecotmc.glasses.common.item.impl.SunglassesItem;
import team.dovecotmc.glasses.common.item.properties.GlassesProperties;

import java.util.List;

public class GlassesItem extends Item {
    protected GlassesProperties properties;
    protected final Component artisan;

    public GlassesItem(Component artisan, GlassesProperties properties) {
        super(properties.properties().stacksTo(1));
        this.artisan = artisan;
        this.properties = properties;
    }

    public GlassesItem(Component artisan) {
        this(artisan, GlassesProperties.builder().build());
    }

    @Override
    public @Nullable EquipmentSlot getEquipmentSlot(ItemStack stack) {
        return EquipmentSlot.HEAD;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(stack, level, list, flag);
        list.add(Component.translatable("tooltips.glasses.artisan", artisan));
        if (this instanceof SunglassesItem) list.add(Component.translatable("tooltips.glasses.blocks_endermen")
                .withStyle(ChatFormatting.LIGHT_PURPLE));
        if (properties.packetAction() != null || properties.canUse())
            list.add(Component.translatable("tooltips.glasses.use",
                KeyBindingRef.GLASSES_ACTION.get().getKey().getDisplayName(),
                Component.translatable(stack.getItem().getDescriptionId())));
        properties.tooltipProvider().get().modify(stack, level, list, flag);
    }

    public void onReceivePacket(ServerPlayer player, ItemStack stack) {
        if (properties.canUse()) {
            final CompoundTag tag = stack.getOrCreateTag();
            final boolean newStatus = !tag.getBoolean("glasses_using");
            if (newStatus) {
                if (properties.startUsing() != null) player.playNotifySound(properties.startUsing(),
                        SoundSource.PLAYERS, 1.0F, 1.0F);
            } else if (properties.stopUsing() != null) player.playNotifySound(properties.stopUsing(),
                    SoundSource.PLAYERS, 1.0F, 1.0F);
            tag.putBoolean("glasses_using", newStatus);
        }
        if (properties.packetAction() != null) properties.packetAction().run(player, stack);
    }

    public GlassesProperties getProperties() {
        return properties;
    }
}
