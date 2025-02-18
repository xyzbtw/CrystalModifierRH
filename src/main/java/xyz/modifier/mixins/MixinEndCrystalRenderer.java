package xyz.modifier.mixins;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EndCrystalRenderer;
import net.minecraft.world.entity.boss.enderdragon.EndCrystal;
import xyz.modifier.CrystalModifier;
import xyz.modifier.ExamplePlugin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EndCrystalRenderer.class)
public class MixinEndCrystalRenderer {

    @Unique
    private static CrystalModifier crystalModifier = ExamplePlugin.INSTANCE.crystalModifier;

    @Inject(
            method = "render(Lnet/minecraft/world/entity/boss/enderdragon/EndCrystal;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V",
            at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;pushPose()V")
    )
    public void scaleHook(EndCrystal entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight, CallbackInfo ci) {
        if (crystalModifier.isToggled() && crystalModifier.changeScale.getValue()) {
            float scale = crystalModifier.scaleMultiplier.getValue();
            poseStack.scale(scale, scale, scale);
        }
    }


    @ModifyArg(method = "render(Lnet/minecraft/world/entity/boss/enderdragon/EndCrystal;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V",
            at = @At(value = "INVOKE", target = "Lcom/mojang/math/Axis;rotationDegrees(F)Lorg/joml/Quaternionf;"))
    public float spinRenderHook(float degrees) {

        if (crystalModifier.isToggled() && crystalModifier.spin.getValue()) {
            return degrees * crystalModifier.spinMultiplier.getValue();
        }

        return degrees;
    }


    @Inject(method = "getY", at = @At("HEAD"), cancellable = true)
    private static void bounceHook(EndCrystal endCrystal, float partialTick, CallbackInfoReturnable<Float> cir) {

        if (crystalModifier.isToggled() && crystalModifier.disableBounce.getValue()) {
            cir.setReturnValue(-1f);
        }
    }

}
