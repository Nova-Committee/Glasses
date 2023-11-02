package team.dovecotmc.glasses.common.item.properties;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;

public record GlassesProperties(Item.Properties properties, Supplier<TooltipProvider> tooltipProvider,
                                PacketAction packetAction) {
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
        private PacketAction packetAction = null;

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

        public GlassesProperties build() {
            return new GlassesProperties(properties, tooltipProvider, packetAction);
        }
    }
}
