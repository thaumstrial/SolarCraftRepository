package com.finderfeed.solarforge.entities.renderers;

import com.finderfeed.solarforge.entities.CrystalBossEntity;
import com.finderfeed.solarforge.entities.ShieldingCrystalCrystalBoss;
import com.finderfeed.solarforge.events.other_events.ModelRegistryEvents;
import com.finderfeed.solarforge.for_future_library.helpers.FinderfeedMathHelper;
import com.finderfeed.solarforge.for_future_library.helpers.RenderingTools;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class CrystalBossRenderer extends EntityRenderer<CrystalBossEntity> {

    public static final ResourceLocation RAY = new ResourceLocation("solarforge","textures/misc/shielding_ray.png");
    private static final RenderType BEAM = RenderType.text(RAY);

    public CrystalBossRenderer(EntityRendererProvider.Context p_174008_) {
        super(p_174008_);
    }


    @Override
    public void render(CrystalBossEntity boss, float idk, float pticks, PoseStack matrices, MultiBufferSource buffer, int light) {
        matrices.pushPose();
        float time = (boss.level.getGameTime() + pticks) % 360;
        RenderingTools.renderObjModel(ModelRegistryEvents.CRYSTAL_BOSS,matrices,buffer,light, OverlayTexture.NO_OVERLAY,(pose)->{
            pose.translate(0,3.5f,0);
            pose.mulPose(Vector3f.YN.rotationDegrees(time));
        });
        matrices.popPose();
        VertexConsumer vertex = buffer.getBuffer(BEAM);


        if (boss.entitiesAroundClient != null) {

            for (ShieldingCrystalCrystalBoss crystal : boss.entitiesAroundClient) {

                renderRay(matrices, vertex, time / 60,
                        boss.position().add(0, boss.getBbHeight() / 2, 0),
                        crystal.position().add(0, crystal.getBbHeight()/2, 0),boss);

            }
        }

        super.render(boss, idk, pticks, matrices, buffer, light);
    }


    private void renderRay(PoseStack matrices, VertexConsumer vertex, float time, Vec3 firstPos,Vec3 secondPos,CrystalBossEntity boss){
        Vec3 direction = secondPos.subtract(firstPos);
        Vec3 yes = direction.normalize();
        double angleY = Math.toDegrees(Math.atan2(yes.x,yes.z));
        double angleX = Math.toDegrees(Math.atan2(Math.sqrt(yes.x*yes.x + yes.z*yes.z),yes.y) );
        matrices.pushPose();
        matrices.translate(0,boss.getBbHeight()/2,0);
        matrices.mulPose(Vector3f.YP.rotationDegrees((float)angleY));
        matrices.mulPose(Vector3f.XP.rotationDegrees((float)angleX));
        double length = direction.length();
        double factor = length/16;

        Matrix4f mat = matrices.last().pose();

        for (int i = 0; i < 6;i++){
            double[] xz = FinderfeedMathHelper.polarToCartesian(0.25f,Math.toRadians(i*60));
            double[] xz2 = FinderfeedMathHelper.polarToCartesian(0.25f,Math.toRadians(i*60+60));
            RenderingTools.basicVertex(mat,vertex,xz[0],0,xz[1],0,(time%2)*(float)factor);
            RenderingTools.basicVertex(mat,vertex,xz2[0],0,xz2[1],1,(time%2)*(float)factor);
            RenderingTools.basicVertex(mat,vertex,xz2[0],length,xz2[1],1,(1+time%2)*(float)factor);
            RenderingTools.basicVertex(mat,vertex,xz[0],length,xz[1],0,(1+time%2)*(float)factor);

            RenderingTools.basicVertex(mat,vertex,xz[0],length,xz[1],0,(1+time%2)*(float)factor);
            RenderingTools.basicVertex(mat,vertex,xz2[0],length,xz2[1],1,(1+time%2)*(float)factor);
            RenderingTools.basicVertex(mat,vertex,xz2[0],0,xz2[1],1,(time%2)*(float)factor);
            RenderingTools.basicVertex(mat,vertex,xz[0],0,xz[1],0,(time%2)*(float)factor);

        }
        matrices.popPose();

    }

    @Override
    public ResourceLocation getTextureLocation(CrystalBossEntity p_114482_) {
        return TextureAtlas.LOCATION_BLOCKS;
    }
}