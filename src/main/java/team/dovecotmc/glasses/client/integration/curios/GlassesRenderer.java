package team.dovecotmc.glasses.client.integration.curios;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;
import org.joml.Quaternionf;
import team.dovecotmc.glasses.client.config.DisplayOffset;
import team.dovecotmc.glasses.util.client.ClientUtilities;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.function.Consumer;

public class GlassesRenderer implements ICurioRenderer {
    @Nullable
    private DisplayOffset offset;
    private final Item glasses;

    private boolean saved = true;
    private final String glassesName;

    public GlassesRenderer(Item i) {
        this.glasses = i;
        this.glassesName = Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(i)).toString().replace(':', '.');
        this.offset = ClientUtilities.getOffsetConfig(glassesName).orElse(null);
    }

    @Override
    public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack stack, SlotContext slotContext, PoseStack poseStack, RenderLayerParent<T, M> renderLayerParent, MultiBufferSource renderTypeBuffer, int light, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (!(renderLayerParent.getModel() instanceof HeadedModel headedModel)) return;
        ICurioRenderer.followHeadRotations(slotContext.entity(), headedModel.getHead());
        headedModel.getHead().translateAndRotate(poseStack);
        CustomHeadLayer.translateToHead(poseStack, false);
        if (offset != null) {
            poseStack.translate(offset.x, offset.y, offset.z);
            poseStack.mulPose(new Quaternionf().rotationZYX(offset.zRot, offset.yRot, offset.xRot));
            poseStack.scale(offset.xScale, offset.yScale, offset.zScale);
        }
        final Minecraft mc = Minecraft.getInstance();
        mc.getItemRenderer().renderStatic(stack, ItemDisplayContext.HEAD, light, OverlayTexture.NO_OVERLAY, poseStack, renderTypeBuffer, mc.level, 0);
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
