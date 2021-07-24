package com.finderfeed.solarforge.events.other_events.event_handler;


import com.finderfeed.solarforge.Helpers;
import com.finderfeed.solarforge.SolarForge;
import com.finderfeed.solarforge.events.PlayerTickEvent;
import com.finderfeed.solarforge.events.my_events.ProgressionUnlockEvent;
import com.finderfeed.solarforge.magic_items.items.solar_lexicon.achievements.Achievement;
import com.finderfeed.solarforge.magic_items.items.solar_lexicon.unlockables.AncientFragment;
import com.finderfeed.solarforge.magic_items.items.solar_lexicon.unlockables.ProgressionHelper;
import com.finderfeed.solarforge.registries.items.ItemsRegister;
import com.finderfeed.solarforge.world_generation.features.FeaturesRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.core.tools.picocli.CommandLine;
import org.lwjgl.system.CallbackI;

import java.util.ArrayList;
import java.util.List;


@Mod.EventBusSubscriber(modid = "solarforge",bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventHandler {

    @SubscribeEvent
    public static void addFeatures(BiomeLoadingEvent event){
        event.getGeneration().addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, FeaturesRegistry.ENERGY_PYLON_CONFIGURED);
        if (event.getCategory() == Biome.Category.PLAINS){
            event.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,FeaturesRegistry.RUNIC_TREE_FEATURE);
        }
    }


    @SubscribeEvent
    public static void progressionUnlockEvent(ProgressionUnlockEvent event){
        Achievement ach = event.getProgression();
        PlayerEntity playerEntity = event.getPlayer();
        if (!Helpers.hasPlayerUnlocked(ach,playerEntity) && Helpers.canPlayerUnlock(ach,playerEntity)){
            Helpers.setAchievementStatus(ach, playerEntity,true);
            Helpers.triggerToast(ach, playerEntity);
            Helpers.updateProgression((ServerPlayerEntity)playerEntity );
            Helpers.forceChunksReload((ServerPlayerEntity) playerEntity);
            Helpers.triggerProgressionShader(playerEntity);
        }

    }

    @SubscribeEvent
    public static void cancelCrafting(TickEvent.PlayerTickEvent event){
        PlayerEntity playerEntity = event.player;
        if (!playerEntity.level.isClientSide) {
            if (playerEntity.tickCount % 40 == 0) {
                deleteSolarForge(playerEntity);
                deleteInfuser(playerEntity);
            }
        }
    }

    @SubscribeEvent
    public static void cancelCrafting2(BlockEvent.EntityPlaceEvent event){
        Entity entity = event.getEntity();
        if (entity instanceof  PlayerEntity){
            PlayerEntity playerEntity = (PlayerEntity) entity;
            if (!playerEntity.isCreative()) {
                if (event.getPlacedBlock().is(SolarForge.SOLAR_FORGE.get()) && !ProgressionHelper.doPlayerHasFragment(playerEntity, AncientFragment.SOLAR_FORGE)) {
                    event.setCanceled(true);

                } else if (event.getPlacedBlock().is(SolarForge.SOLAR_INFUSER.get()) && !ProgressionHelper.doPlayerHasFragment(playerEntity, AncientFragment.SOLAR_INFUSER)) {
                    event.setCanceled(true);
                }
            }
        }
    }


    public static void deleteSolarForge(PlayerEntity playerEntity){
        int count = playerEntity.inventory.countItem(SolarForge.SOLAR_FORGE_ITEM.get());


        if ((count > 0) && !ProgressionHelper.doPlayerHasFragment(playerEntity, AncientFragment.SOLAR_FORGE) && !playerEntity.isCreative() && !playerEntity.isSpectator()) {
            PlayerInventory inventory = playerEntity.inventory;
            for (int i = 0;i < count;i++){
                int slot = inventory.findSlotMatchingUnusedItem(SolarForge.SOLAR_FORGE_ITEM.get().getDefaultInstance());
                inventory.setItem(slot,ItemStack.EMPTY);
                playerEntity.level.addFreshEntity(createItemEntity(playerEntity,new ItemStack(Blocks.IRON_BLOCK,4)));
                playerEntity.level.addFreshEntity(createItemEntity(playerEntity,new ItemStack(Blocks.OBSIDIAN,4)));
                playerEntity.level.addFreshEntity(createItemEntity(playerEntity,new ItemStack(SolarForge.TEST_ITEM.get(),1)));
            }
            playerEntity.sendMessage(
                    new StringTextComponent("Solar forge not allowed "+AncientFragment.SOLAR_FORGE.getTranslation().getString().toUpperCase()+" fragment is not unlocked")
                            .withStyle(TextFormatting.RED),
                    playerEntity.getUUID());
        }

    }
    public static void deleteInfuser(PlayerEntity playerEntity){
        int count = playerEntity.inventory.countItem(SolarForge.SOLAR_FORGE_ITEM.get());


        if ((count > 0) && !ProgressionHelper.doPlayerHasFragment(playerEntity, AncientFragment.SOLAR_INFUSER) && !playerEntity.isCreative() && !playerEntity.isSpectator()) {
            PlayerInventory inventory = playerEntity.inventory;
            for (int i = 0;i < count;i++){
                int slot = inventory.findSlotMatchingUnusedItem(SolarForge.INFUSING_STAND_ITEM.get().getDefaultInstance());
                inventory.setItem(slot,ItemStack.EMPTY);
                playerEntity.level.addFreshEntity(createItemEntity(playerEntity,new ItemStack(Blocks.IRON_BLOCK,3)));
                playerEntity.level.addFreshEntity(createItemEntity(playerEntity,new ItemStack(ItemsRegister.SOLAR_STONE.get(),3)));
                playerEntity.level.addFreshEntity(createItemEntity(playerEntity,new ItemStack(SolarForge.TEST_ITEM.get(),1)));
            }
            playerEntity.sendMessage(
                    new StringTextComponent("Infuser not allowed "+AncientFragment.SOLAR_INFUSER.getTranslation().getString().toUpperCase()+" fragment is not unlocked")
                            .withStyle(TextFormatting.RED),
                    playerEntity.getUUID());
        }

    }
    public static ItemEntity createItemEntity(PlayerEntity playerEntity,ItemStack stack){
        return new ItemEntity(playerEntity.level,playerEntity.getX(),playerEntity.getY(),playerEntity.getZ(),stack);
    }



}