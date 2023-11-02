package team.dovecotmc.glasses.common.item.impl;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import team.dovecotmc.glasses.common.item.base.GlassesItem;
import team.dovecotmc.glasses.common.item.properties.GlassesProperties;

import java.util.List;

public class SunglassesItem extends GlassesItem {
    public SunglassesItem(Component artisan, GlassesProperties properties) {
        super(artisan, properties);
    }

    public SunglassesItem(Component artisan) {
        this(artisan, GlassesProperties.builder().build());
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(stack, level, list, flag);
        list.add(Component.translatable("tooltips.glasses.blocks_endermen").withStyle(ChatFormatting.LIGHT_PURPLE));
    }

    @Override
    public boolean isEnderMask(ItemStack stack, Player player, EnderMan endermanEntity) {
        return true;
    }
}
