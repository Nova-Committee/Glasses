package team.dovecotmc.glasses.common.item.properties;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;

public record GlassesProperties(
        Item.Properties properties,
        Supplier<TooltipProvider> tooltipProvider,
        @Nullable PacketAction packetAction,
        boolean canUse,
        SoundEvent startUsing,
        SoundEvent stopUsing
) {
    public static Builder builder() {
        return new Builder();
    }

    public interface PacketAction {
        void run(ServerPlayer player, ItemStack stack);
    }

    public interface TooltipProvider {
        void modify(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag flag);
    }

    public static class Builder {
        private Item.Properties properties = new Item.Properties();
        private Supplier<TooltipProvider> tooltipProvider = () -> (s, l, t, f) -> {
        };
        private @Nullable PacketAction packetAction = null;
        private @Nullable SoundEvent startUsing = null;
        private @Nullable SoundEvent stopUsing = null;
        private boolean canUse = false;

        public Builder properties(Item.Properties properties) {
            this.properties = properties;
            return this;
        }

        public Builder tooltip(Supplier<TooltipProvider> provider) {
            this.tooltipProvider = provider;
            return this;
        }

        public Builder packetAction(PacketAction packetAction) {
            this.packetAction = packetAction;
            return this;
        }

        public Builder canUse(SoundEvent start, SoundEvent stop) {
            this.canUse = true;
            this.startUsing = start;
            this.stopUsing = stop;
            return this;
        }

        public GlassesProperties build() {
            return new GlassesProperties(properties, tooltipProvider, packetAction, canUse, startUsing, stopUsing);
        }
    }
}
