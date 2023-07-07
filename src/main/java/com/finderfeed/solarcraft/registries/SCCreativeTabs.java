package com.finderfeed.solarcraft.registries;

import com.finderfeed.solarcraft.*;
import com.finderfeed.solarcraft.content.items.SolarGodBow;
import com.finderfeed.solarcraft.content.items.SolarGodPickaxe;
import com.finderfeed.solarcraft.content.items.SolarGodSword;
import com.finderfeed.solarcraft.content.items.solar_lexicon.unlockables.AncientFragment;
import com.finderfeed.solarcraft.content.items.solar_lexicon.unlockables.ProgressionHelper;
import com.finderfeed.solarcraft.registries.items.SolarcraftItems;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;


public class SCCreativeTabs {

    public static final ResourceLocation BACKGROUND = new ResourceLocation(SolarCraft.MOD_ID,"textures/gui/solar_items_tab.png");
    public static final ResourceLocation TABS = new ResourceLocation(SolarCraft.MOD_ID,"textures/gui/solar_items_tabs.png");

    public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB,SolarCraft.MOD_ID);
    public static RegistryObject<CreativeModeTab> SOLAR_GROUP_COPY;
    public static RegistryObject<CreativeModeTab> SOLAR_GROUP_BLOCKS_COPY;
    public static RegistryObject<CreativeModeTab> SOLAR_GROUP_WEAPONS_COPY;
    public static RegistryObject<CreativeModeTab> SOLAR_GROUP_TOOLS_COPY;
    public static RegistryObject<CreativeModeTab> SOLAR_GROUP_MATERIALS_COPY;
    public static RegistryObject<CreativeModeTab> SOLAR_GROUP_FRAGMENTS_COPY = null;

    public static final RegistryObject<CreativeModeTab> SOLAR_GROUP = REGISTRY.register("main_group",()->CreativeModeTab.builder()
            .icon(()->SolarcraftItems.SOLAR_SHARD.get().getDefaultInstance())
            .title(Component.literal("itemGroup.solar_forge_group"))
            .withTabsImage(TABS)
            .displayItems((parameters,output)->{
                var a = SolarcraftItems.itemTabs.get(SOLAR_GROUP_COPY);
                a.forEach(regitem->output.accept(regitem.get()));
            })
            .withBackgroundLocation(BACKGROUND)
            .withLabelColor(ChatFormatting.GOLD.getColor())
            .build());




    public static final RegistryObject<CreativeModeTab> SOLAR_GROUP_BLOCKS = REGISTRY.register("blocks_group",()->CreativeModeTab.builder()
            .icon(()->SolarcraftItems.MAGISTONE_BRICKS.get().getDefaultInstance())
            .title(Component.literal("itemGroup.solar_forge_group_blocks"))
            .withTabsImage(TABS)
            .withBackgroundLocation(BACKGROUND)
            .displayItems((parameters,output)->{
                var a = SolarcraftItems.itemTabs.get(SOLAR_GROUP_BLOCKS_COPY);
                a.forEach(regitem->output.accept(regitem.get()));
            })
            .withLabelColor(ChatFormatting.GOLD.getColor())
            .build());


//    public static final CreativeModeTab SOLAR_GROUP_TOOLS = new SolarGroupTools("solar_forge_group_tools");
    public static final RegistryObject<CreativeModeTab> SOLAR_GROUP_TOOLS = REGISTRY.register("tools_group",()->CreativeModeTab.builder()
            .icon(()->SolarcraftItems.SOLAR_NETWORK_BINDER.get().getDefaultInstance())
            .title(Component.literal("itemGroup.solar_forge_group_tools"))
            .withTabsImage(TABS)
            .withBackgroundLocation(BACKGROUND)
        .displayItems((parameters,output)->{
            var a = SolarcraftItems.itemTabs.get(SOLAR_GROUP_TOOLS_COPY);
            a.forEach(regitem->output.accept(regitem.get()));
            for (int i = 0; i <= SolarGodPickaxe.UPGRADE_COUNT; i++){
                SolarGodPickaxe item;
                ItemStack stack = new ItemStack(item = (SolarGodPickaxe) SolarcraftItems.SOLAR_GOD_PICKAXE.get(),1);
                item.setItemLevel(stack,i);
                output.accept(stack);
            }
        })
            .withLabelColor(ChatFormatting.GOLD.getColor())
            .build());


//    public static final CreativeModeTab SOLAR_GROUP_MATERIALS = new SolarGroupThemed("solar_group_materials", SolarcraftItems.ILLIDIUM_INGOT);
    public static final RegistryObject<CreativeModeTab> SOLAR_GROUP_MATERIALS = REGISTRY.register("materials_group",()->CreativeModeTab.builder()
            .icon(()->SolarcraftItems.ILLIDIUM_INGOT.get().getDefaultInstance())
            .title(Component.literal("itemGroup.solar_group_materials"))
            .withTabsImage(TABS)
            .withBackgroundLocation(BACKGROUND)
            .displayItems((parameters,output)->{
                var a = SolarcraftItems.itemTabs.get(SOLAR_GROUP_MATERIALS_COPY);
                a.forEach(regitem->output.accept(regitem.get()));
            })
            .withLabelColor(ChatFormatting.GOLD.getColor())
            .build());


//    public static final CreativeModeTab SOLAR_GROUP_WEAPONS = new SolarGroupThemed("solar_group_weapons", SolarcraftItems.ILLIDIUM_SWORD);
    public static final RegistryObject<CreativeModeTab> SOLAR_GROUP_WEAPONS = REGISTRY.register("weapons_group",()->CreativeModeTab.builder()
            .icon(()->SolarcraftItems.ILLIDIUM_SWORD.get().getDefaultInstance())
            .title(Component.literal("itemGroup.solar_group_weapons"))
            .withTabsImage(TABS)
            .withBackgroundLocation(BACKGROUND)
            .displayItems((parameters,output)->{
                var a = SolarcraftItems.itemTabs.get(SOLAR_GROUP_WEAPONS_COPY);
                a.forEach(regitem->output.accept(regitem.get()));
                for (int i = 0; i <= SolarGodBow.UPGRADE_COUNT;i++){
                    SolarGodBow item;
                    ItemStack stack = new ItemStack(item = (SolarGodBow) SolarcraftItems.SOLAR_GOD_BOW.get(),1);
                    item.setItemLevel(stack,i);
                    output.accept(stack);
                }

                for (int i = 0; i <= SolarGodSword.UPGRADE_COUNT; i++){
                    SolarGodSword item;
                    ItemStack stack = new ItemStack(item = (SolarGodSword) SolarcraftItems.SOLAR_GOD_SWORD.get(),1);
                    item.setItemLevel(stack,i);
                    output.accept(stack);
                }
            })
            .withLabelColor(ChatFormatting.GOLD.getColor())
            .build());

//    public static final CreativeModeTab SOLAR_GROUP_FRAGMENTS = new SolarGroupFragments("solar_forge_group_fragments");
    public static final RegistryObject<CreativeModeTab> SOLAR_GROUP_FRAGMENTS = REGISTRY.register("fragments_group",()->CreativeModeTab.builder()
            .icon(()->SolarcraftItems.INFO_FRAGMENT.get().getDefaultInstance())
            .title(Component.literal("itemGroup.solar_group_weapons"))
            .withTabsImage(TABS)
            .withBackgroundLocation(BACKGROUND)
            .displayItems((parameters,output)->{
                var a = SolarcraftItems.itemTabs.get(SOLAR_GROUP_FRAGMENTS_COPY);
                a.forEach(regitem->output.accept(regitem.get()));
                for (AncientFragment frag : AncientFragment.getAllFragments()) {
                    ItemStack stack = new ItemStack(SolarcraftItems.INFO_FRAGMENT.get(), 1);
                    stack.getOrCreateTagElement(ProgressionHelper.TAG_ELEMENT).putString(ProgressionHelper.FRAG_ID, frag.getId());
                    output.accept(stack);
                }
            })
            .withLabelColor(ChatFormatting.GOLD.getColor())
            .build());

    static {
        SOLAR_GROUP_COPY = SOLAR_GROUP;
        SOLAR_GROUP_MATERIALS_COPY = SOLAR_GROUP_MATERIALS;
        SOLAR_GROUP_FRAGMENTS_COPY = SOLAR_GROUP_FRAGMENTS;
        SOLAR_GROUP_TOOLS_COPY = SOLAR_GROUP_TOOLS;
        SOLAR_GROUP_WEAPONS_COPY = SOLAR_GROUP_WEAPONS;
        SOLAR_GROUP_BLOCKS_COPY = SOLAR_GROUP_BLOCKS;
    }
}