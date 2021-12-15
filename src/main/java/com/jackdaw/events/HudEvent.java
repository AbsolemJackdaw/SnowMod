package com.jackdaw.events;

import com.jackdaw.capability.Bladder;
import com.jackdaw.mod.SnowMod;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SnowMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class HudEvent {

    private static final ResourceLocation resLoc = new ResourceLocation(SnowMod.MODID, "textures/bladder_hud.png");

    @SubscribeEvent
    public static void renderHUDOverlay(RenderGameOverlayEvent.Pre event) {

        Bladder.get(Minecraft.getInstance().player).ifPresent(data -> {
            if (data.getCapacity() > 0) {
                PoseStack stack = event.getMatrixStack();
                stack.pushPose();
                RenderSystem.setShader(GameRenderer::getPositionTexShader);
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                RenderSystem.setShaderTexture(0, resLoc);

                int screenx = event.getWindow().getGuiScaledWidth() / 2 + 18 * 5 + 9;
                int screeny = event.getWindow().getGuiScaledHeight() - 20;
                float percentFill = 14f * ((float) data.getCapacity() / (float) Bladder.MAX);
                int offset = 14 - (int) percentFill;

                GuiComponent.blit(stack, screenx, screeny, 0, (float) 0, (float) 0, 16, 16, 32, 16);
                GuiComponent.blit(stack, screenx, screeny + offset, 0, (float) 16, (float) offset, 16, 16 - offset, 32, 16);
                stack.popPose();
            }
        });

    }
}
