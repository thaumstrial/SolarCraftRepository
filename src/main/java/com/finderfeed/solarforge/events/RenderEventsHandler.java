package com.finderfeed.solarforge.events;


import com.finderfeed.solarforge.RenderingTools;
import com.finderfeed.solarforge.rendering.shaders.Shaders;
import com.finderfeed.solarforge.rendering.shaders.Uniform;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.client.shader.FramebufferConstants;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class RenderEventsHandler {





    public static float intensity = 0;
    public static float radius = 0;



    @SubscribeEvent
    public void renderWorld(RenderWorldLastEvent event){

        if ((Minecraft.getInstance().getWindow().getScreenWidth() != 0) && (Minecraft.getInstance().getWindow().getScreenHeight() != 0) ) {

            if ((intensity > 0)  ) {
                RenderingTools.renderHandManually(event.getMatrixStack(),event.getPartialTicks());
                Framebuffer buffer = Minecraft.getInstance().getMainRenderTarget();
                Framebuffers.buffer2.resize(Minecraft.getInstance().getWindow().getScreenWidth(), Minecraft.getInstance().getWindow().getScreenHeight(), false);

                GlStateManager._glBindFramebuffer(FramebufferConstants.GL_FRAMEBUFFER, Framebuffers.buffer2.frameBufferId);
                int shader = Shaders.WAVE.getShader().getSHADER();
                int time =(int)Minecraft.getInstance().level.getGameTime();
                Shaders.WAVE.getShader().addUniform(new Uniform("intensity", intensity, shader));
                Shaders.WAVE.getShader().addUniform(new Uniform("timeModifier", 3f, shader));
                Shaders.WAVE.getShader().addUniform(new Uniform("time", time, shader));
                Shaders.WAVE.getShader().setMatrices();


                Shaders.WAVE.getShader().process();

                buffer.blitToScreen(Minecraft.getInstance().getWindow().getScreenWidth(), Minecraft.getInstance().getWindow().getScreenHeight());
                Shaders.close();
                GlStateManager._glBindFramebuffer(FramebufferConstants.GL_FRAMEBUFFER, buffer.frameBufferId);

                Framebuffers.buffer2.blitToScreen(Minecraft.getInstance().getWindow().getScreenWidth(), Minecraft.getInstance().getWindow().getScreenHeight());
                GlStateManager._glBindFramebuffer(FramebufferConstants.GL_FRAMEBUFFER, 0);
                intensity-=0.02;
                radius+=0.01;



            }
        }
    }




    public static void triggerProgressionShader(){
            intensity = 3;
            radius = 0;
    }




//    @SubscribeEvent
//    public void renderTestWorld(RenderWorldLastEvent event){
//        if ((Minecraft.getInstance().getWindow().getScreenWidth() != 0) && (Minecraft.getInstance().getWindow().getScreenHeight() != 0)
//                && Shaders.TEST.getShader().isActive()) {
//
//                Framebuffer buffer = Minecraft.getInstance().getMainRenderTarget();
//                Framebuffers.buffer2.resize(Minecraft.getInstance().getWindow().getScreenWidth(), Minecraft.getInstance().getWindow().getScreenHeight(), false);
//
//
//                GlStateManager._glBindFramebuffer(FramebufferConstants.GL_FRAMEBUFFER, Framebuffers.buffer2.frameBufferId);
//
//                Shaders.TEST.getShader().process();
//
//                buffer.blitToScreen(Minecraft.getInstance().getWindow().getScreenWidth(), Minecraft.getInstance().getWindow().getScreenHeight());
//                Shaders.close();
//                GlStateManager._glBindFramebuffer(FramebufferConstants.GL_FRAMEBUFFER, buffer.frameBufferId);
//                Framebuffers.buffer2.blitToScreen(Minecraft.getInstance().getWindow().getScreenWidth(), Minecraft.getInstance().getWindow().getScreenHeight());
//                GlStateManager._glBindFramebuffer(FramebufferConstants.GL_FRAMEBUFFER, 0);
//                Shaders.TEST.getShader().setActive(false);
//
//            World world = Minecraft.getInstance().level;
//            PlayerEntity playerEntity = Minecraft.getInstance().player;
//
//            Vector3d playerPosition = playerEntity.position().add(0,playerEntity.getBbHeight()/2,0);  //just player position
//            float distance = 15; //distance in blocks
//            Vector3d playerMultipliedLookVector = playerEntity.getLookAngle().multiply(distance,distance,distance); //where player looks
//            Vector3d maximumBlockPosition = playerPosition.add(playerMultipliedLookVector);
//            RayTraceContext ctx = new RayTraceContext(playerPosition,maximumBlockPosition, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE,null);
//            BlockRayTraceResult trace = world.clip(ctx);
//            trace.getBlockPos(); // find a block by block position through World.getBlockstate();
//
//
//        }
//    }





}

class Framebuffers{
    public static final Framebuffer buffer2 =
            new Framebuffer(Minecraft.getInstance().getWindow().getScreenWidth(),Minecraft.getInstance().getWindow().getScreenHeight(),false,true);
}