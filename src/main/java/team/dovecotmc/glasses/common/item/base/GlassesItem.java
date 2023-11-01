package team.dovecotmc.glasses.common.item.base;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GlassesItem extends Item {
    protected final Component artisan;

    public GlassesItem(Component artisan, Properties properties) {
        super(properties.stacksTo(1));
        this.artisan = artisan;
    }

    public GlassesItem(Component artisan) {
        this(artisan, new Properties());
    }

    @Override
    public @Nullable EquipmentSlot getEquipmentSlot(ItemStack stack) {
        return EquipmentSlot.HEAD;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(stack, level, list, flag);
        list.add(Component.translatable("tooltips.glasses.artisan", artisan));
    }
}
