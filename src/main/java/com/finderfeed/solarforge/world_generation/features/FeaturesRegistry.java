package com.finderfeed.solarforge.world_generation.features;

import com.finderfeed.solarforge.SolarForge;
import com.finderfeed.solarforge.registries.blocks.BlocksRegistry;
import com.finderfeed.solarforge.registries.worldgen.configured.ConfiguredFeatures;
import com.finderfeed.solarforge.world_generation.biomes.molten_forest.MoltenForestAmbience;
import com.finderfeed.solarforge.world_generation.dimension_related.radiant_land.CrystallizedOreVeinFeature;
import com.finderfeed.solarforge.world_generation.dimension_related.radiant_land.RadiantSmallTreeFoliagePlacer;
import com.finderfeed.solarforge.world_generation.dimension_related.radiant_land.RadiantTreeFoliagePlacer;

import com.finderfeed.solarforge.world_generation.features.foliage_placers.BurntTreeFoliagePlacer;
import com.finderfeed.solarforge.world_generation.features.trunk_placers.BurntTreeTrunkPlacer;
import com.google.common.collect.ImmutableSet;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.util.valueproviders.ConstantInt;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;


import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.carver.WorldCarver;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;

import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;



import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;


//i hate you with every ounce of being mojang!
public class FeaturesRegistry {



    public static ConfiguredFeature<TreeConfiguration,?> BURNT_TREE_FEATURE_2_CONF;


    public static ConfiguredFeature<?,?> BURNT_TREE_FEATURE_CONF;




    public static final RuleTest END_STONE = new TagMatchTest(Tags.Blocks.END_STONES);

    public static final ResourceLocation BURNT_BIOME_BURNT_TREE = new ResourceLocation("solarforge","burnt_biome_tree");
    public static final ResourceLocation MOLTEN_FOREST_BIOME = new ResourceLocation("solarforge","incinerated_forest");
    public static final ResourceKey<Biome> MOLTEN_BIOME_KEY = ResourceKey.create(Registry.BIOME_REGISTRY,MOLTEN_FOREST_BIOME);


    public static final Feature<NoneFeatureConfiguration> BURNT_BIOME_AMBIENCE_1 = new MoltenForestAmbience(NoneFeatureConfiguration.CODEC);
    public static final Feature<NoneFeatureConfiguration> BURNT_BIOME_AMBIENCE_2 = new MoltenForestRuins(NoneFeatureConfiguration.CODEC);
    public static final Feature<NoneFeatureConfiguration> ENERGY_PYLON = new EnergyPylonFeature(NoneFeatureConfiguration.CODEC);
    public static final Feature<NoneFeatureConfiguration> FLOATING_ISLANDS_RADIANT_LAND = new RadiantLandFloatingIslands(NoneFeatureConfiguration.CODEC);
    public static final Feature<NoneFeatureConfiguration> CRYSTALLIZED_ORE_VEIN_RADIANT_LAND = new CrystallizedOreVeinFeature(NoneFeatureConfiguration.CODEC);
    public static final Feature<NoneFeatureConfiguration> CRYSTAL_CAVE_ORE_CRYSTAL = new CrystalCaveOreCrystal(NoneFeatureConfiguration.CODEC);
    public static final Feature<NoneFeatureConfiguration> CEILING_FLOOR_CRYSTALS = new WallCrystalsCrystalCave(NoneFeatureConfiguration.CODEC);
    public static final Feature<SimpleBlockConfiguration> STONE_FLOWERS = new StoneFlowersFeature(SimpleBlockConfiguration.CODEC);
    public static final Feature<NoneFeatureConfiguration> CEILING_DRIPSTONE_LIKE_CRYSTALS = new CeilingDripstoneLikeCrystals(NoneFeatureConfiguration.CODEC);
    public static final Feature<SimpleBlockConfiguration> CRYSTALS_ORE = new CrystalsOreFeature(SimpleBlockConfiguration.CODEC);
    public static final Feature<NoneFeatureConfiguration> ULDERA_OBELISK = new UlderaObeliskFeature(NoneFeatureConfiguration.CODEC);
    public static final Feature<NoneFeatureConfiguration> ULDERA_PYLON = new UlderaPylonFeature(NoneFeatureConfiguration.CODEC);



    public static ConfiguredFeature<?,?> RADIANT_TREE_CONFIGURED_CONF;
    public static ConfiguredFeature<?,?> RADIANT_SMALL_TREE_CONFIGURED_CONF;
    public static ConfiguredFeature<?,?> ENERGY_PYLON_CONFIGURED_CONF;
    public static ConfiguredFeature<?,?> MOLTEN_FOREST_RUINS_CONFIGURED_CONF;
    public static ConfiguredFeature<?,?> RANDOM_PATCH_RADIANT_GRASS_CONF;
    public static ConfiguredFeature<?,?> FLOATING_ISLANDS_RADIANT_LAND_CONFIGURED_CONF;
    public static ConfiguredFeature<?,?> CRYSTALLIZED_ORE_VEIN_CONFIGURED_CONF;
    public static ConfiguredFeature<?,?> RADIANT_BERRY_BUSH_CONF;
    public static ConfiguredFeature<?,?> ENDER_CRACKS_CONF;
    public static ConfiguredFeature<?,?> LENSING_CRYSTAL_ORE_CONF;
    public static ConfiguredFeature<?,?> CRYSTAL_CAVE_ORE_CRYSTAL_CONF;
    public static ConfiguredFeature<?,?> CEILING_FLOOR_CRYSTALS_CONF;
    public static ConfiguredFeature<?,?> CRYSTAL_FLOWER_CONF;
    public static ConfiguredFeature<?,?> CEILING_DRIPSTONE_LIKE_CRYSTALS_CONF;
    public static ConfiguredFeature<?,?> CRYSTALLIZED_RUNIC_ENERGY_CRYSTALS_CONF;
    public static ConfiguredFeature<?,?> LUNAR_LILY_FEATURE_CONF;
    public static ConfiguredFeature<?,?> EMPTY_CRYSTALS_CONF;
    public static ConfiguredFeature<?,?> SOLAR_ORE_CONF;
    public static ConfiguredFeature<?,?> SOLAR_STONE_CONF;
    public static ConfiguredFeature<?,?> ULDERA_OBELISK_CONFIGURED;
    public static ConfiguredFeature<?,?> ULDERA_PYLON_CONFIGURED;


    public static Holder<PlacedFeature> ULDERA_OBELISK_PLACEMENT;
    public static Holder<PlacedFeature> BURNT_TREE_2;
    public static Holder<PlacedFeature> BURNT_TREE_1;
    public static Holder<PlacedFeature> RADIANT_TREE_PLACEMENT;
    public static Holder<PlacedFeature> RADIANT_SMALL_TREE_PLACEMENT;
    public static Holder<PlacedFeature> ENERGY_PYLON_PLACEMENT;
    public static Holder<PlacedFeature> MOLTEN_FOREST_RUINS_PLACEMENT;
    public static Holder<PlacedFeature> RANDOM_PATCH_RADIANT_GRASS;
    public static Holder<PlacedFeature> FLOATING_ISLANDS_RADIANT_LAND_PLACEMENT;
    public static Holder<PlacedFeature> CRYSTALLIZED_ORE_VEIN_CONFIGURED;
    public static Holder<PlacedFeature> RADIANT_BERRY_BUSH;
    public static Holder<PlacedFeature> ENDER_CRACKS;
    public static Holder<PlacedFeature> LENSING_CRYSTAL_ORE_PLACEMENT;
    public static Holder<PlacedFeature> CRYSTAL_CAVE_ORE_CRYSTAL_PLACEMENT;
    public static Holder<PlacedFeature> CEILING_FLOOR_CRYSTALS_PLACEMENT;
    public static Holder<PlacedFeature> CRYSTAL_FLOWER_PLACEMENT;
    public static Holder<PlacedFeature> CEILING_DRIPSTONE_LIKE_CRYSTALS_PLACEMENT;
    public static Holder<PlacedFeature> CRYSTALLIZED_RUNIC_ENERGY_CRYSTALS_PLACEMENT;
    public static Holder<PlacedFeature> LUNAR_LILY_FEATURE_PLACEMENT;
    public static Holder<PlacedFeature> EMPTY_CRYSTALS_PLACEMENT;
    public static Holder<PlacedFeature> RUNIC_TREE_FEATURE;
    public static Holder<PlacedFeature> ULDORADIUM_ORE_PLACED_FEATURE;
    public static Holder<PlacedFeature> BURNT_BIOME_AMBIENECE_PLACED_FEATURE;
    public static Holder<PlacedFeature> SOLAR_ORE;
    public static Holder<PlacedFeature> SOLAR_STONE;
    public static Holder<PlacedFeature> ULDERA_PYLON_PLACEMENT;

    //public static ConfiguredFeature<?,?> RADIANT_LAND_AMBIENT_TREE;



    public static  ConfiguredFeature<?,?> BURNT_BIOME_AMBIENCE_1_CONFIGURED ;
//            .decorated(FeatureDecorator.CHANCE.configured(new ChanceDecoratorConfiguration(4)



    public static ConfiguredFeature<OreConfiguration,?> ULDORADIUM_ORE;
//            .rangeUniform(VerticalAnchor.bottom(),VerticalAnchor.absolute(60)).squared().count(6);



    public static ConfiguredFeature<TreeConfiguration,?> RUNIC_TREE_FEATURE_CONF;



    public static void registerFeatures(final RegistryEvent.Register<Feature<?>> event){
        event.getRegistry().register(BURNT_BIOME_AMBIENCE_1.setRegistryName(BURNT_BIOME_BURNT_TREE));
        event.getRegistry().register(BURNT_BIOME_AMBIENCE_2.setRegistryName(new ResourceLocation(SolarForge.MOD_ID,"ruins_feature")));
        event.getRegistry().register(ENERGY_PYLON.setRegistryName(new ResourceLocation(SolarForge.MOD_ID,"energy_pylon_feature")));
        event.getRegistry().register(FLOATING_ISLANDS_RADIANT_LAND.setRegistryName(new ResourceLocation(SolarForge.MOD_ID,"floating_islands")));
        event.getRegistry().register(CRYSTALLIZED_ORE_VEIN_RADIANT_LAND.setRegistryName(new ResourceLocation(SolarForge.MOD_ID,"crystallized_ore_vein")));
        event.getRegistry().register(CRYSTAL_CAVE_ORE_CRYSTAL.setRegistryName(new ResourceLocation(SolarForge.MOD_ID,"crystal_cave_ore_crystal")));
        event.getRegistry().register(CEILING_FLOOR_CRYSTALS.setRegistryName(new ResourceLocation(SolarForge.MOD_ID,"ceiling_floor_crystals")));
        event.getRegistry().register(STONE_FLOWERS.setRegistryName(new ResourceLocation(SolarForge.MOD_ID,"stone_flowers")));
        event.getRegistry().register(CEILING_DRIPSTONE_LIKE_CRYSTALS.setRegistryName(new ResourceLocation(SolarForge.MOD_ID,"ceiling_dripstonelike_crystals")));
        registerFeature(event, CRYSTALS_ORE,"crystallized_runic_energy");
        registerFeature(event,ULDERA_OBELISK,"uldera_obelisk");
        registerFeature(event,ULDERA_PYLON,"uldera_pylon");

    }
    private static void registerFeature(RegistryEvent.Register<Feature<?>> event,Feature<?> f,String name){
        event.getRegistry().register(f.setRegistryName(new ResourceLocation(SolarForge.MOD_ID,name)));

    }


    public static void addCarvableBlocks(FMLCommonSetupEvent event){
        event.enqueueWork(()-> {
            WorldCarver<?> carver = ForgeRegistries.WORLD_CARVERS.getValue(new ResourceLocation("minecraft", "cave"));
            if (carver != null) {
                ImmutableSet.Builder<Block> builder = new ImmutableSet.Builder<>();
                builder.addAll(carver.replaceableBlocks);
                builder.add(BlocksRegistry.RADIANT_GRASS.get());
                carver.replaceableBlocks = builder.build();
            }
        });
    }

    public static void registerConfiguredFeatures(final FMLCommonSetupEvent event){
        event.enqueueWork(()->{
            BURNT_TREE_FEATURE_2_CONF = new ConfiguredFeature<>(Feature.TREE,new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(BlocksRegistry.BURNT_LOG.get().defaultBlockState()),
                    new StraightTrunkPlacer(4, 2, 0),
                    BlockStateProvider.simple(BlocksRegistry.ASH_LEAVES.get().defaultBlockState()),
                    new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))
                    .ignoreVines().build());
//
//            Registry.register(Registry.FOLIAGE_PLACER_TYPES,new ResourceLocation("solarforge","burnt_tree_foliage"), FoliagePlacerRegistry.BURNT_TREE_PLACER);
//            Registry.register(Registry.FOLIAGE_PLACER_TYPES,new ResourceLocation("solarforge","radiant_tree_foliage"), FoliagePlacerRegistry.RADIANT_PLACER);
//            Registry.register(Registry.FOLIAGE_PLACER_TYPES,new ResourceLocation("solarforge","radiant_tree_small_foliage"), FoliagePlacerRegistry.RADIANT_SMALL_PLACER);


//            BURNT_TREE_2 = BURNT_TREE_FEATURE_2_CONF.placed(
//                    PlacementUtils.countExtra(10,0.1f,1),
//                    HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
//                    InSquarePlacement.spread(),
//                    NoiseBasedCountPlacement.of(3,2,3),
//                    BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)));
            Registry.register(BuiltinRegistries.CONFIGURED_FEATURE,new ResourceLocation(SolarForge.MOD_ID,"burnt_tree_feature2_configured"), BURNT_TREE_FEATURE_2_CONF);
            BURNT_TREE_2 = registerPlacedFeature("burnt_tree_feature2",Holder.direct(BURNT_TREE_FEATURE_2_CONF),
                    PlacementUtils.countExtra(10,0.1f,1),
                    HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                    InSquarePlacement.spread(),
                    NoiseBasedCountPlacement.of(3,2,3),
                    BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(),
                            BlockPos.ZERO)));


//            registerPlacedFeature(BURNT_TREE_2,"burnt_tree_feature2");
            BURNT_TREE_FEATURE_CONF = new ConfiguredFeature<>(Feature.TREE,new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(BlocksRegistry.BURNT_LOG.get().defaultBlockState()),
                    new BurntTreeTrunkPlacer(5, 3, 0),
                    BlockStateProvider.simple(BlocksRegistry.ASH_LEAVES.get().defaultBlockState()),
                    new BurntTreeFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)),
                    new TwoLayersFeatureSize(1, 0, 1))
                    .ignoreVines().build());
            Registry.register(BuiltinRegistries.CONFIGURED_FEATURE,new ResourceLocation("solarforge","burnt_tree_feature_configured"),BURNT_TREE_FEATURE_CONF);

            //            BURNT_TREE_FEATURE_CONF = Feature.TREE.configured(new TreeConfiguration.TreeConfigurationBuilder(
//                    BlockStateProvider.simple(BlocksRegistry.BURNT_LOG.get().defaultBlockState()),
//                    new BurntTreeTrunkPlacer(5, 3, 0),
//                    BlockStateProvider.simple(BlocksRegistry.ASH_LEAVES.get().defaultBlockState()),
//                    new BurntTreeFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)),
//                    new TwoLayersFeatureSize(1, 0, 1))
//                    .ignoreVines().build());

//            BURNT_TREE_1 = BURNT_TREE_FEATURE_CONF.placed(HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
//                    InSquarePlacement.spread(),
//                    BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)));
//            registerPlacedFeature(BURNT_TREE_1,"burnt_tree_feature");
            BURNT_TREE_1 = registerPlacedFeature("burnt_tree_feature",Holder.direct(BURNT_TREE_FEATURE_CONF),HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                    InSquarePlacement.spread(),
                    BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)));

//            = BURNT_BIOME_AMBIENCE_1
//                    .configured(NoneFeatureConfiguration.INSTANCE);
            BURNT_BIOME_AMBIENCE_1_CONFIGURED = new ConfiguredFeature<>(BURNT_BIOME_AMBIENCE_1,NoneFeatureConfiguration.INSTANCE);
            Registry.register(BuiltinRegistries.CONFIGURED_FEATURE,new ResourceLocation("solarforge","burnt_biome_ambience_1_configured"),BURNT_BIOME_AMBIENCE_1_CONFIGURED);

            BURNT_BIOME_AMBIENECE_PLACED_FEATURE = registerPlacedFeature("burnt_biome_ambience_1",Holder.direct(BURNT_BIOME_AMBIENCE_1_CONFIGURED),
                    RarityFilter.onAverageOnceEvery(4));





            ULDORADIUM_ORE = new ConfiguredFeature<>(Feature.ORE,
                    new OreConfiguration(OreFeatures.NATURAL_STONE,
                            BlocksRegistry.ULDORADIUM_ORE.get().defaultBlockState(),6));
            Registry.register(BuiltinRegistries.CONFIGURED_FEATURE,new ResourceLocation("solarforge","uldoradium_ore_configured"),ULDORADIUM_ORE);

            ULDORADIUM_ORE_PLACED_FEATURE = registerPlacedFeature("uldoradium_ore",Holder.direct(ULDORADIUM_ORE),
                    HeightRangePlacement.uniform(VerticalAnchor.bottom(),VerticalAnchor.absolute(60)),InSquarePlacement.spread());

            ENERGY_PYLON_CONFIGURED_CONF = new ConfiguredFeature<>(ENERGY_PYLON,NoneFeatureConfiguration.INSTANCE);
            Registry.register(BuiltinRegistries.CONFIGURED_FEATURE,new ResourceLocation("solarforge","energy_pylon_configured"),
                    ENERGY_PYLON_CONFIGURED_CONF);

            ENERGY_PYLON_PLACEMENT = registerPlacedFeature("energy_pylon",Holder.direct(ENERGY_PYLON_CONFIGURED_CONF),
                    RarityFilter.onAverageOnceEvery(200));


            RUNIC_TREE_FEATURE_CONF = new ConfiguredFeature<>(Feature.TREE,new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(BlocksRegistry.RUNIC_LOG.get().defaultBlockState()),
                    new StraightTrunkPlacer(4, 2, 0),
                    BlockStateProvider.simple(BlocksRegistry.RUNIC_LEAVES.get().defaultBlockState()),
                    new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1))
                    .ignoreVines().build());
            Registry.register(BuiltinRegistries.CONFIGURED_FEATURE,new ResourceLocation("solarforge","runic_tree_configured"),RUNIC_TREE_FEATURE_CONF);

            RUNIC_TREE_FEATURE = registerPlacedFeature("runic_tree",Holder.direct(RUNIC_TREE_FEATURE_CONF),
                    HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                    RarityFilter.onAverageOnceEvery(100),
                    InSquarePlacement.spread(),
                    BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)));


            MOLTEN_FOREST_RUINS_CONFIGURED_CONF = new ConfiguredFeature<>(BURNT_BIOME_AMBIENCE_2,NoneFeatureConfiguration.INSTANCE);
            //TODO:switch back to 60 when incinerated forest returns

            Registry.register(BuiltinRegistries.CONFIGURED_FEATURE,new ResourceLocation("solarforge","configured_ruins_configured"),
                    MOLTEN_FOREST_RUINS_CONFIGURED_CONF);

            MOLTEN_FOREST_RUINS_PLACEMENT = registerPlacedFeature("configured_ruins",Holder.direct(MOLTEN_FOREST_RUINS_CONFIGURED_CONF),
                    RarityFilter.onAverageOnceEvery(120));


            RADIANT_TREE_CONFIGURED_CONF = new ConfiguredFeature<>(Feature.TREE,
                    new TreeConfiguration.TreeConfigurationBuilder(
                            BlockStateProvider.simple(BlocksRegistry.RADIANT_LOG.get().defaultBlockState()),
                            new StraightTrunkPlacer(15, 1, 0),
                            BlockStateProvider.simple(BlocksRegistry.RADIANT_LEAVES.get().defaultBlockState()),
                            new RadiantTreeFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0)),
                            new TwoLayersFeatureSize(1, 0, 1)
                    ).build());
            Registry.register(BuiltinRegistries.CONFIGURED_FEATURE,new ResourceLocation("solarforge","radiant_tree_configured"), RADIANT_TREE_CONFIGURED_CONF);

            RADIANT_TREE_PLACEMENT = registerPlacedFeature("radiant_tree",Holder.direct(RADIANT_TREE_CONFIGURED_CONF),
                    PlacementUtils.countExtra(3,0.1f,2),
                    HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                    InSquarePlacement.spread(),
                    BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)));


//            RANDOM_PATCH_RADIANT_GRASS_CONF = Feature.RANDOM_PATCH.configured(
//                    FeatureUtils.simpleRandomPatchConfiguration(1,
//                            Feature.SIMPLE_BLOCK.configured(
//                            new SimpleBlockConfiguration(BlockStateProvider.simple(BlocksRegistry.RADIANT_GRASS_NOT_BLOCK.get()))).onlyWhenEmpty()));



            RANDOM_PATCH_RADIANT_GRASS_CONF = new ConfiguredFeature<>(Feature.RANDOM_PATCH,
                    FeatureUtils.simpleRandomPatchConfiguration(1,PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                            new SimpleBlockConfiguration(BlockStateProvider.simple(BlocksRegistry.RADIANT_GRASS_NOT_BLOCK.get())))));
            Registry.register(BuiltinRegistries.CONFIGURED_FEATURE,new ResourceLocation("solarforge","radiant_grass_grass_configured"), RANDOM_PATCH_RADIANT_GRASS_CONF);

            RANDOM_PATCH_RADIANT_GRASS = registerPlacedFeature("radiant_grass_grass",Holder.direct(RANDOM_PATCH_RADIANT_GRASS_CONF),
                    CountPlacement.of(60), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE);


            FLOATING_ISLANDS_RADIANT_LAND_CONFIGURED_CONF = new ConfiguredFeature<>(FLOATING_ISLANDS_RADIANT_LAND,NoneFeatureConfiguration.INSTANCE);
            Registry.register(BuiltinRegistries.CONFIGURED_FEATURE,new ResourceLocation("solarforge","floating_islands_configured"), FLOATING_ISLANDS_RADIANT_LAND_CONFIGURED_CONF);

            FLOATING_ISLANDS_RADIANT_LAND_PLACEMENT = registerPlacedFeature("floating_islands",Holder.direct(FLOATING_ISLANDS_RADIANT_LAND_CONFIGURED_CONF),
                    RarityFilter.onAverageOnceEvery(30),
                    HeightRangePlacement.triangle(VerticalAnchor.absolute(100),VerticalAnchor.absolute(130)),
                    InSquarePlacement.spread());



            RADIANT_SMALL_TREE_CONFIGURED_CONF = new ConfiguredFeature<>(Feature.TREE,
                    new TreeConfiguration.TreeConfigurationBuilder(
                            BlockStateProvider.simple(BlocksRegistry.RADIANT_LOG.get().defaultBlockState()),
                            new StraightTrunkPlacer(9, 1, 0),
                            BlockStateProvider.simple(BlocksRegistry.RADIANT_LEAVES.get().defaultBlockState()),
                            new RadiantSmallTreeFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0)),
                            new TwoLayersFeatureSize(1, 0, 1))
                            .ignoreVines().build());
            Registry.register(BuiltinRegistries.CONFIGURED_FEATURE,new ResourceLocation("solarforge","radiant_land_ambient_tree_configured"), RADIANT_SMALL_TREE_CONFIGURED_CONF);

            RADIANT_SMALL_TREE_PLACEMENT = registerPlacedFeature("radiant_land_ambient_tree",Holder.direct(RADIANT_SMALL_TREE_CONFIGURED_CONF),
                    RarityFilter.onAverageOnceEvery(3),
                    HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                    InSquarePlacement.spread(),
                    BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)));




            CRYSTALLIZED_ORE_VEIN_CONFIGURED_CONF = new ConfiguredFeature<>(CRYSTALLIZED_ORE_VEIN_RADIANT_LAND,NoneFeatureConfiguration.INSTANCE);
            Registry.register(BuiltinRegistries.CONFIGURED_FEATURE,new ResourceLocation("solarforge","crystallized_ore_vein_configured"), CRYSTALLIZED_ORE_VEIN_CONFIGURED_CONF);

            CRYSTALLIZED_ORE_VEIN_CONFIGURED = registerPlacedFeature("crystallized_ore_vein",Holder.direct(CRYSTALLIZED_ORE_VEIN_CONFIGURED_CONF),
                    RarityFilter.onAverageOnceEvery(25),
                    HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                    InSquarePlacement.spread());


            RADIANT_BERRY_BUSH_CONF = new ConfiguredFeature<>(Feature.RANDOM_PATCH,FeatureUtils.simpleRandomPatchConfiguration(4,
                    PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                            new SimpleBlockConfiguration(BlockStateProvider.simple(BlocksRegistry.RADIANT_BERRY_BUSH.get())))));
            Registry.register(BuiltinRegistries.CONFIGURED_FEATURE,new ResourceLocation("solarforge","radiant_berry_bush_configured"), RADIANT_BERRY_BUSH_CONF);
            RADIANT_BERRY_BUSH = registerPlacedFeature("radiant_berry_bush",Holder.direct(RADIANT_BERRY_BUSH_CONF),
                    InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE);


            ENDER_CRACKS_CONF = new ConfiguredFeature<>(Feature.ORE,
                    new OreConfiguration(END_STONE, BlocksRegistry.ENDER_CRACKS.get().defaultBlockState(),5));
            Registry.register(BuiltinRegistries.CONFIGURED_FEATURE,new ResourceLocation("solarforge","ender_cracks_configured"), ENDER_CRACKS_CONF);

            ENDER_CRACKS = registerPlacedFeature("ender_cracks",Holder.direct(ENDER_CRACKS_CONF),
                    HeightRangePlacement.uniform(VerticalAnchor.absolute(30),VerticalAnchor.absolute(100)));

            CRYSTAL_CAVE_ORE_CRYSTAL_CONF = new ConfiguredFeature<>(CRYSTAL_CAVE_ORE_CRYSTAL,NoneFeatureConfiguration.INSTANCE);
            Registry.register(BuiltinRegistries.CONFIGURED_FEATURE,new ResourceLocation("solarforge","crystal_cave_ore_crystal"), CRYSTAL_CAVE_ORE_CRYSTAL_CONF);

            CRYSTAL_CAVE_ORE_CRYSTAL_PLACEMENT = registerPlacedFeature("crystal_cave_ore_crystal",Holder.direct(CRYSTAL_CAVE_ORE_CRYSTAL_CONF),
                    RarityFilter.onAverageOnceEvery(2),
                    CountPlacement.of(UniformInt.of(10,15)),InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, BiomeFilter.biome());

            CEILING_FLOOR_CRYSTALS_CONF = new ConfiguredFeature<>(CEILING_FLOOR_CRYSTALS,NoneFeatureConfiguration.INSTANCE);
            registerConfiguredFeature(CEILING_FLOOR_CRYSTALS_CONF,"ceiling_floor_crystals");

            CEILING_FLOOR_CRYSTALS_PLACEMENT = registerPlacedFeature("ceiling_floor_crystals",Holder.direct(CEILING_FLOOR_CRYSTALS_CONF),
                    CountPlacement.of(UniformInt.of(13,17)), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
                    BiomeFilter.biome());

            CRYSTAL_FLOWER_CONF = new ConfiguredFeature<>(Feature.FLOWER,
                    FeatureUtils.simpleRandomPatchConfiguration(7,
                            PlacementUtils.onlyWhenEmpty(STONE_FLOWERS,
                                    new SimpleBlockConfiguration(BlockStateProvider.simple(BlocksRegistry.CRYSTAL_FLOWER.get())))));
            registerConfiguredFeature(CRYSTAL_FLOWER_CONF,"crystal_flower");

            CRYSTAL_FLOWER_PLACEMENT = registerPlacedFeature("crystal_flower",Holder.direct(CRYSTAL_FLOWER_CONF),
                    CountPlacement.of(UniformInt.of(50,60)),PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,BiomeFilter.biome() );

            CEILING_DRIPSTONE_LIKE_CRYSTALS_CONF = new ConfiguredFeature<>(CEILING_DRIPSTONE_LIKE_CRYSTALS,NoneFeatureConfiguration.INSTANCE);
            registerConfiguredFeature(CEILING_DRIPSTONE_LIKE_CRYSTALS_CONF,"ceiling_dripstonelike_crystals");

            CEILING_DRIPSTONE_LIKE_CRYSTALS_PLACEMENT = registerPlacedFeature("ceiling_dripstonelike_crystals",
                    Holder.direct(CEILING_DRIPSTONE_LIKE_CRYSTALS_CONF),
                    CountPlacement.of(UniformInt.of(20,30)),InSquarePlacement.spread(),
                    PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,BiomeFilter.biome());

            CRYSTALLIZED_RUNIC_ENERGY_CRYSTALS_CONF = new ConfiguredFeature<>(CRYSTALS_ORE,
                    new SimpleBlockConfiguration(BlockStateProvider.simple(BlocksRegistry.CRYSTALLIZED_RUNIC_ENERGY.get())));
            registerConfiguredFeature(CRYSTALLIZED_RUNIC_ENERGY_CRYSTALS_CONF,"crystallized_runic_energy");

            CRYSTALLIZED_RUNIC_ENERGY_CRYSTALS_PLACEMENT = registerPlacedFeature("crystallized_runic_energy",
                    Holder.direct(CRYSTALLIZED_RUNIC_ENERGY_CRYSTALS_CONF),
                    CountPlacement.of(UniformInt.of(60,100)),InSquarePlacement.spread(),
                    HeightRangePlacement.uniform(VerticalAnchor.bottom(),VerticalAnchor.absolute(100)),BiomeFilter.biome());

            LENSING_CRYSTAL_ORE_CONF = new ConfiguredFeature<>(Feature.ORE,
                    new OreConfiguration(OreFeatures.NATURAL_STONE,BlocksRegistry.LENSING_CRYSTAL_ORE.get().defaultBlockState(),4));
            registerConfiguredFeature(LENSING_CRYSTAL_ORE_CONF,"lensing_crystal_ore");

            LENSING_CRYSTAL_ORE_PLACEMENT = registerPlacedFeature("lensing_crystal_ore",Holder.direct(LENSING_CRYSTAL_ORE_CONF),
                    CountPlacement.of(UniformInt.of(4,6)),InSquarePlacement.spread(),
                    HeightRangePlacement.uniform(VerticalAnchor.absolute(10),VerticalAnchor.absolute(50)));

            LUNAR_LILY_FEATURE_CONF = new ConfiguredFeature<>(Feature.FLOWER,
                    FeatureUtils.simpleRandomPatchConfiguration(3,PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                            new SimpleBlockConfiguration(BlockStateProvider.simple(BlocksRegistry.VOID_LILY.get())))));
            registerConfiguredFeature(LUNAR_LILY_FEATURE_CONF,"lunar_lily");

            LUNAR_LILY_FEATURE_PLACEMENT = registerPlacedFeature("lunar_lily",Holder.direct(LUNAR_LILY_FEATURE_CONF),
                    NoiseThresholdCountPlacement.of(-0.8D, 15, 4),
                    RarityFilter.onAverageOnceEvery(32), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP );

            EMPTY_CRYSTALS_CONF = new ConfiguredFeature<>(CRYSTALS_ORE,
                    new SimpleBlockConfiguration(BlockStateProvider.simple(BlocksRegistry.CRYSTAL.get())));
            registerConfiguredFeature(EMPTY_CRYSTALS_CONF,"empty_crystals");

            EMPTY_CRYSTALS_PLACEMENT = registerPlacedFeature("empty_crystals",Holder.direct(EMPTY_CRYSTALS_CONF),
                    CountPlacement.of(UniformInt.of(15,30)),
                    InSquarePlacement.spread(),HeightRangePlacement.uniform(VerticalAnchor.bottom(),
                            VerticalAnchor.absolute(100)),BiomeFilter.biome() );
            SOLAR_ORE_CONF = new ConfiguredFeature<>(Feature.ORE,new OreConfiguration(OreFeatures.NATURAL_STONE, SolarForge.SOLAR_ORE.get().defaultBlockState(),4));
            registerConfiguredFeature(SOLAR_ORE_CONF,"solar_ore");
            SOLAR_ORE = registerPlacedFeature("solar_ore",Holder.direct(SOLAR_ORE_CONF),
                    HeightRangePlacement.uniform(VerticalAnchor.absolute(5),VerticalAnchor.absolute(30)));


            SOLAR_STONE_CONF = new ConfiguredFeature<>(Feature.ORE,new OreConfiguration(OreFeatures.NATURAL_STONE, BlocksRegistry.SOLAR_STONE.get().defaultBlockState(),13));
            registerConfiguredFeature(SOLAR_STONE_CONF,"solar_stone");
            SOLAR_STONE = registerPlacedFeature("solar_stone",Holder.direct(SOLAR_STONE_CONF),
                    CountPlacement.of(UniformInt.of(3,4)), InSquarePlacement.spread(),
                    HeightRangePlacement.uniform(VerticalAnchor.bottom(),VerticalAnchor.absolute(80)));


            ConfiguredFeatures.SOLAR_FLOWER_FEATURE_CONF = new ConfiguredFeature<>(Feature.RANDOM_PATCH,FeatureUtils.simpleRandomPatchConfiguration(2,
                    PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,new SimpleBlockConfiguration(BlockStateProvider.simple(BlocksRegistry.SOLAR_FLOWER.get())))));
            registerConfiguredFeature(ConfiguredFeatures.SOLAR_FLOWER_FEATURE_CONF,"solar_flower_feature");
            ConfiguredFeatures.SOLAR_FLOWER_FEATURE = registerPlacedFeature("solar_flower_feature",Holder.direct(ConfiguredFeatures.SOLAR_FLOWER_FEATURE_CONF),
                    HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING),InSquarePlacement.spread());


            ConfiguredFeatures.DEAD_SPROUT_FEATURE_CONF = new ConfiguredFeature<>(Feature.RANDOM_PATCH,
                    FeatureUtils.simpleRandomPatchConfiguration(7,PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                            new SimpleBlockConfiguration(BlockStateProvider.simple(BlocksRegistry.DEAD_SPROUT.get())))));
            registerConfiguredFeature(ConfiguredFeatures.DEAD_SPROUT_FEATURE_CONF,"dead_sprout_feature");
            ConfiguredFeatures.DEAD_SPROUT_FEATURE = registerPlacedFeature("dead_sprout_feature",Holder.direct(ConfiguredFeatures.DEAD_SPROUT_FEATURE_CONF),
                    HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING),InSquarePlacement.spread());

            ULDERA_OBELISK_CONFIGURED = new ConfiguredFeature<>(ULDERA_OBELISK,NoneFeatureConfiguration.INSTANCE);
            registerConfiguredFeature(ULDERA_OBELISK_CONFIGURED,"uldera_obelisk");

            ULDERA_OBELISK_PLACEMENT = registerPlacedFeature("uldera_obelisk",Holder.direct(ULDERA_OBELISK_CONFIGURED),
                    RarityFilter.onAverageOnceEvery(100),
                    HeightRangePlacement.triangle(VerticalAnchor.absolute(75),VerticalAnchor.absolute(90)),
                    InSquarePlacement.spread());


            ULDERA_PYLON_CONFIGURED = new ConfiguredFeature<>(ULDERA_PYLON,NoneFeatureConfiguration.INSTANCE);
            registerConfiguredFeature(ULDERA_PYLON_CONFIGURED,"uldera_pylon");

            ULDERA_PYLON_PLACEMENT = registerPlacedFeature("uldera_pylon",Holder.direct(ULDERA_PYLON_CONFIGURED),
                    RarityFilter.onAverageOnceEvery(300));
        });
    }



    private static void registerConfiguredFeature(ConfiguredFeature<?,?> feature,String registryid){
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE,new ResourceLocation(SolarForge.MOD_ID,registryid),feature);
    }

    private static void registerPlacedFeature(PlacedFeature feature,String registryid){
        Registry.register(BuiltinRegistries.PLACED_FEATURE,new ResourceLocation(SolarForge.MOD_ID,registryid),feature);
    }
    public static Holder<PlacedFeature> registerPlacedFeature(String name, Holder<? extends ConfiguredFeature<?, ?>> cFeature, PlacementModifier... mod) {
        return BuiltinRegistries.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation(SolarForge.MOD_ID,name), new PlacedFeature(Holder.hackyErase(cFeature), List.of(mod)));
    }



}
