package team.dovecotmc.glasses.client.integration.curios;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.client.TrinketRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class GlassesRenderer implements TrinketRenderer {
    @Override
    public void render(ItemStack stack, SlotReference slotReference, EntityModel<? extends LivingEntity> entityModel, PoseStack poseStack, MultiBufferSource multiBufferSource, int light, LivingEntity livingEntity, float v, float v1, float v2, float v3, float v4, float v5) {
        if (!(entityModel instanceof HumanoidModel<?> humanoid)) return;
        humanoid.getHead().copyFrom(humanoid.head);
        humanoid.getHead().translateAndRotate(poseStack);
        CustomHeadLayer.translateToHead(poseStack, false);
        final Minecraft mc = Minecraft.getInstance();
        mc.getItemRenderer().renderStatic(stack, ItemDisplayContext.HEAD, light, OverlayTexture.NO_OVERLAY, poseStack, multiBufferSource, mc.level, 0);
    }
}
