package com.finderfeed.solarcraft.registries.shader_registry;


import com.finderfeed.solarcraft.SolarCraft;
import com.finderfeed.solarcraft.client.rendering.CoreShaders;
import com.finderfeed.solarcraft.client.rendering.rendertypes.RadiantPortalRendertype;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterShadersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.io.IOException;

@Mod.EventBusSubscriber(modid = "solarcraft",bus = Mod.EventBusSubscriber.Bus.MOD,value = Dist.CLIENT)
public class SolarcraftCoreShadersRegistry {



    @SubscribeEvent
    public static void register(RegisterShadersEvent event) throws IOException {
        event.registerShader(new ShaderInstance(event.getResourceProvider(),new ResourceLocation("solarcraft","water"), DefaultVertexFormat.POSITION_COLOR_TEX_LIGHTMAP),
                (instance)->{
            RadiantPortalRendertype.WATER_SHADER = instance;
        });
        event.registerShader(new ShaderInstance(event.getResourceProvider(),new ResourceLocation("solarcraft","ray"), DefaultVertexFormat.POSITION_COLOR_TEX_LIGHTMAP),
                (instance)->{
                    RadiantPortalRendertype.RAY_SHADER = instance;
        });
        event.registerShader(new ShaderInstance(event.getResourceProvider(),new ResourceLocation(SolarCraft.MOD_ID,"aoe_gun_projectile"), DefaultVertexFormat.POSITION_TEX),
                (instance)->{
                    CoreShaders.AOE_GUN_PROJECTILE_SHADER = instance;
        });
        event.registerShader(new ShaderInstance(event.getResourceProvider(),new ResourceLocation(SolarCraft.MOD_ID,"runic_energy_flow"), DefaultVertexFormat.POSITION_TEX),
                (instance)->{
                    CoreShaders.RUNIC_ENERGY_FLOW_SHADER = instance;
        });
        event.registerShader(new ShaderInstance(event.getResourceProvider(),new ResourceLocation(SolarCraft.MOD_ID,"radial_menu"), DefaultVertexFormat.POSITION_TEX),
                (instance)->{
                    CoreShaders.RADIAL_MENU = instance;
        });
    }

}
