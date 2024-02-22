package team.dovecotmc.glasses.client.integration.trinkets;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.client.TrinketRenderer;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.joml.Quaternionf;
import team.dovecotmc.glasses.client.config.DisplayOffset;
import team.dovecotmc.glasses.util.client.ClientUtilities;

import java.util.function.Consumer;

public class GlassesRenderer implements TrinketRenderer {
    @Nullable
    private DisplayOffset offset;
    private final Item glasses;

    private boolean saved = true;
    private final String glassesName;

    public GlassesRenderer(Item i) {
        this.glasses = i;
        this.glassesName = BuiltInRegistries.ITEM.getKey(i).toString().replace(':', '.');
        this.offset = ClientUtilities.getOffsetConfig(glassesName).orElse(null);
    }

    @Override
    public void render(ItemStack stack, SlotReference slotReference, EntityModel<? extends LivingEntity> entityModel, PoseStack poseStack, MultiBufferSource multiBufferSource, int light, LivingEntity livingEntity, float v, float v1, float v2, float v3, float v4, float v5) {
        if (!(entityModel instanceof HumanoidModel<?> humanoid)) return;
        poseStack.pushPose();
        humanoid.getHead().copyFrom(humanoid.head);
        humanoid.getHead().translateAndRotate(poseStack);
        CustomHeadLayer.translateToHead(poseStack, false);
        if (offset != null) {
            poseStack.translate(offset.x, offset.y, offset.z);
            poseStack.mulPose(new Quaternionf().rotationZYX(offset.zRot, offset.yRot, offset.xRot));
            poseStack.scale(offset.xScale, offset.yScale, offset.zScale);
        }
        final Minecraft mc = Minecraft.getInstance();
        mc.getItemRenderer().renderStatic(stack, ItemDisplayContext.HEAD, light, OverlayTexture.NO_OVERLAY, poseStack, multiBufferSource, mc.level, 0);
        poseStack.popPose();
    }

    public void modifyOffset(Consumer<DisplayOffset> modifier) {
        modifier.accept(offset);
        this.saved = false;
    }

    public void reloadOffset() {
        this.offset = ClientUtilities.getOffsetConfig(glassesName).orElse(null);
        this.saved = true;
        if (Minecraft.getInstance().player == null) return;
        Minecraft.getInstance().player.displayClientMessage(Component.translatable("msg.glasses.offset.reloaded",
                Component.translatable(glasses.getDescriptionId())).withStyle(ChatFormatting.YELLOW), true);
    }

    public void saveOffset() {
        ClientUtilities.saveOffsetConfig(ClientUtilities.getGlassesOffsetPath(glassesName), offset);
        this.saved = true;
        if (Minecraft.getInstance().player == null) return;
        Minecraft.getInstance().player.displayClientMessage(Component.translatable("msg.glasses.offset.saved",
                Component.translatable(glasses.getDescriptionId())).withStyle(ChatFormatting.GREEN), true);
    }

    public boolean isSaved() {
        return saved;
    }

    public Item getGlasses() {
        return glasses;
    }
}
