package com.finderfeed.solarforge.magic_items.blocks.infusing_table_things;

import com.finderfeed.solarforge.ClientHelpers;
import com.finderfeed.solarforge.SolarForge;
import com.finderfeed.solarforge.misc_things.RunicEnergy;
import com.finderfeed.solarforge.recipe_types.InfusingRecipe;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Matrix4f;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import com.mojang.math.Vector3f;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.Optional;

public class InfuserScreen extends AbstractContainerScreen<InfuserContainer> {
    public final ResourceLocation REQ_ENERGY = new ResourceLocation("solarforge","textures/gui/energy_bar.png");
    private static final ResourceLocation GUI_TEXT = new ResourceLocation("solarforge","textures/gui/solar_infuser_gui.png");
    private static final ResourceLocation ENERGY_GUI = new ResourceLocation("solarforge","textures/gui/infuser_energy_gui.png");
    //60*6
    public final ResourceLocation RUNIC_ENERGY_BAR = new ResourceLocation("solarforge","textures/gui/runic_energy_bar.png");
    public final ResourceLocation RUNIC_ENERGY_BAR_T = new ResourceLocation("solarforge","textures/gui/runic_energy_bar_t.png");
    public int relX;
    public int relY;
    public InfuserScreen(InfuserContainer container, Inventory inv, Component text) {
        super(container, inv, text);
        this.leftPos = 60;
        this.topPos = 0;
        this.height = 170;
        this.width = 256;
        this.imageHeight = 170;
        this.imageWidth = 256;

    }


    @Override
    public void init() {
        super.init();
        int width = minecraft.getWindow().getWidth();
        int height = minecraft.getWindow().getHeight();
        int scale = (int) minecraft.getWindow().getGuiScale();
        this.relX = (width/scale - 183)/2;
        this.relY = (height - 218*scale)/2/scale;
        this.inventoryLabelX = 1000;
        this.inventoryLabelY = 1000;
        this.titleLabelX = 89;
        this.titleLabelY = -30;

    }
    @Override
    public void render(PoseStack stack, int rouseX, int rouseY, float partialTicks){

        this.renderBackground(stack);

        super.render(stack,rouseX,rouseY,partialTicks);
        this.renderTooltip(stack,rouseX,rouseY);



    }
    @Override
    protected void renderBg(PoseStack matrices, float partialTicks, int x, int y) {
        matrices.pushPose();
        ClientHelpers.bindText(GUI_TEXT);
        int a= 0;
        if ((int)minecraft.getWindow().getGuiScale() == 2){
            a = -1;
        }
        this.blit(matrices, relX+4+a, relY-8, 0, 0, 190, 230);
        ItemRenderer renderd = minecraft.getItemRenderer();
        InfuserTileEntity tile = this.menu.te;

//        renderItemAndTooltip(tile.getItem(1),relX+137+a,relY+58,x,y,matrices);
//        renderItemAndTooltip(tile.getItem(2),relX+123+a,relY+19,x,y,matrices);
//        renderItemAndTooltip(tile.getItem(3),relX+84+a,relY+5,x,y,matrices);
//        renderItemAndTooltip(tile.getItem(4),relX+45+a,relY+19,x,y,matrices);
//        renderItemAndTooltip(tile.getItem(5),relX+31+a,relY+58,x,y,matrices);
//        renderItemAndTooltip(tile.getItem(6),relX+45+a,relY+97,x,y,matrices);
//        renderItemAndTooltip(tile.getItem(7),relX+84+a,relY+111,x,y,matrices);
//        renderItemAndTooltip(tile.getItem(8),relX+123+a,relY+97,x,y,matrices);
        Optional<InfusingRecipe> recipe = minecraft.level.getRecipeManager().getRecipeFor(SolarForge.INFUSING_RECIPE_TYPE,tile,minecraft.level);

//        if (recipe.isPresent()){
//            renderItemAndTooltip(recipe.get().output,relX+159+a,relY+2,x,y,matrices);
//
//        }
        matrices.popPose();
        matrices.pushPose();
        ClientHelpers.bindText(REQ_ENERGY);

        matrices.translate(relX+22+a,relY+80,0);
        matrices.mulPose(Vector3f.ZP.rotationDegrees(180));
        float percent = (float)tile.energy / 100000;

        if (recipe.isPresent()){
            float percentNeeded = (float)recipe.get().requriedEnergy / 100000;
            RenderSystem.enableBlend();
            blitm(matrices, 0, 0, 0, 0, 16, (int)(percentNeeded*33),16,33);
            RenderSystem.disableBlend();
        }


        blit(matrices,0,0,0,0,16,(int)(percent*33),16,33);
        matrices.popPose();

        matrices.pushPose();
        ClientHelpers.bindText(ENERGY_GUI);
        blit(matrices,relX+a-54,relY-8,0,0,58,177,58,177);
        ClientHelpers.bindText(RUNIC_ENERGY_BAR);
        if (recipe.isPresent()){
            InfusingRecipe recipe1 = recipe.get();
            RenderSystem.enableBlend();
            renderEnergyBar(matrices,relX+a-12,relY+61,recipe1.RUNIC_ENERGY_COST.get(RunicEnergy.Type.KELDA),true);

            renderEnergyBar(matrices,relX+a-28,relY+61,recipe1.RUNIC_ENERGY_COST.get(RunicEnergy.Type.TERA),true);

            renderEnergyBar(matrices,relX+a-44,relY+61,recipe1.RUNIC_ENERGY_COST.get(RunicEnergy.Type.ZETA),true);


            renderEnergyBar(matrices,relX+a-12,relY+145,recipe1.RUNIC_ENERGY_COST.get(RunicEnergy.Type.URBA),true);

            renderEnergyBar(matrices,relX+a-28,relY+145,recipe1.RUNIC_ENERGY_COST.get(RunicEnergy.Type.FIRA),true);

            renderEnergyBar(matrices,relX+a-44,relY+145,recipe1.RUNIC_ENERGY_COST.get(RunicEnergy.Type.ARDO),true);
            RenderSystem.disableBlend();
        }


        renderEnergyBar(matrices,relX+a-12,relY+61,tile.getRunicEnergy(RunicEnergy.Type.KELDA),false);

        renderEnergyBar(matrices,relX+a-28,relY+61,tile.getRunicEnergy(RunicEnergy.Type.TERA),false);

        renderEnergyBar(matrices,relX+a-44,relY+61,tile.getRunicEnergy(RunicEnergy.Type.ZETA),false);


        renderEnergyBar(matrices,relX+a-12,relY+145,tile.getRunicEnergy(RunicEnergy.Type.URBA),false);

        renderEnergyBar(matrices,relX+a-28,relY+145,tile.getRunicEnergy(RunicEnergy.Type.FIRA),false);

        renderEnergyBar(matrices,relX+a-44,relY+145,tile.getRunicEnergy(RunicEnergy.Type.ARDO),false);


        renderItemAndTooltip(tile.getItem(1),relX+137+a,relY+58,x,y,matrices);
        renderItemAndTooltip(tile.getItem(2),relX+123+a,relY+19,x,y,matrices);
        renderItemAndTooltip(tile.getItem(3),relX+84+a,relY+5,x,y,matrices);
        renderItemAndTooltip(tile.getItem(4),relX+45+a,relY+19,x,y,matrices);
        renderItemAndTooltip(tile.getItem(5),relX+31+a,relY+58,x,y,matrices);
        renderItemAndTooltip(tile.getItem(6),relX+45+a,relY+97,x,y,matrices);
        renderItemAndTooltip(tile.getItem(7),relX+84+a,relY+111,x,y,matrices);
        renderItemAndTooltip(tile.getItem(8),relX+123+a,relY+97,x,y,matrices);

        if (recipe.isPresent()){
            renderItemAndTooltip(recipe.get().output,relX+159+a,relY+2,x,y,matrices);

        }
        matrices.popPose();
    }
    private void renderItemAndTooltip(ItemStack toRender, int place1, int place2, int mousex, int mousey, PoseStack matrices){
        minecraft.getItemRenderer().renderGuiItem(toRender,place1,place2);
        minecraft.getItemRenderer().renderGuiItemDecorations(font,toRender,place1,place2);
        if (((mousex >= place1) && (mousex <= place1+16)) && ((mousey >= place2) && (mousey <= place2+16)) && !toRender.getItem().equals(Items.AIR)){
            matrices.pushPose();
            renderTooltip(matrices,toRender,mousex,mousey);
            matrices.popPose();
        }
    }


    //runic energy bar texture is binded before it
    private void renderEnergyBar(PoseStack matrices, int offsetx, int offsety, double energyAmount,boolean simulate){
        matrices.pushPose();

        int texturex = Math.round((float)energyAmount/100000*60);
        matrices.translate(offsetx,offsety,0);
        matrices.mulPose(Vector3f.ZN.rotationDegrees(90));
        if (!simulate) {
            blit(matrices, 0, 0, 0, 0, texturex, 6);
        }else{
            blitm(matrices, 0, 0, 0, 0, texturex, 6,60,6);
        }

        matrices.popPose();
    }




    //those are just to render transparent texture, copied from vanilla and a bit modified
    public static void blitm(PoseStack p_93161_, int p_93162_, int p_93163_, int p_93164_, int p_93165_, float p_93166_, float p_93167_, int p_93168_, int p_93169_, int p_93170_, int p_93171_) {
        innerBlitm(p_93161_, p_93162_, p_93162_ + p_93164_, p_93163_, p_93163_ + p_93165_, 0, p_93168_, p_93169_, p_93166_, p_93167_, p_93170_, p_93171_);
    }

    public static void blitm(PoseStack p_93134_, int p_93135_, int p_93136_, float p_93137_, float p_93138_, int p_93139_, int p_93140_, int p_93141_, int p_93142_) {
        blitm(p_93134_, p_93135_, p_93136_, p_93139_, p_93140_, p_93137_, p_93138_, p_93139_, p_93140_, p_93141_, p_93142_);
    }

    private static void innerBlitm(PoseStack p_93188_, int p_93189_, int p_93190_, int p_93191_, int p_93192_, int p_93193_, int p_93194_, int p_93195_, float p_93196_, float p_93197_, int p_93198_, int p_93199_) {
        innerBlitm(p_93188_.last().pose(), p_93189_, p_93190_, p_93191_, p_93192_, p_93193_, (p_93196_ + 0.0F) / (float)p_93198_, (p_93196_ + (float)p_93194_) / (float)p_93198_, (p_93197_ + 0.0F) / (float)p_93199_, (p_93197_ + (float)p_93195_) / (float)p_93199_);
    }

    private static void innerBlitm(Matrix4f p_93113_, int p_93114_, int p_93115_, int p_93116_, int p_93117_, int p_93118_, float p_93119_, float p_93120_, float p_93121_, float p_93122_) {

        RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
        BufferBuilder var10 = Tesselator.getInstance().getBuilder();
        var10.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX_COLOR);
        var10.vertex(p_93113_, (float)p_93114_, (float)p_93117_, (float)p_93118_).uv(p_93119_, p_93122_).color(1,1,1,0.5f).endVertex();
        var10.vertex(p_93113_, (float)p_93115_, (float)p_93117_, (float)p_93118_).uv(p_93120_, p_93122_).color(1,1,1,0.5f).endVertex();
        var10.vertex(p_93113_, (float)p_93115_, (float)p_93116_, (float)p_93118_).uv(p_93120_, p_93121_).color(1,1,1,0.5f).endVertex();
        var10.vertex(p_93113_, (float)p_93114_, (float)p_93116_, (float)p_93118_).uv(p_93119_, p_93121_).color(1,1,1,0.5f).endVertex();
        var10.end();
        BufferUploader.end(var10);

    }

}