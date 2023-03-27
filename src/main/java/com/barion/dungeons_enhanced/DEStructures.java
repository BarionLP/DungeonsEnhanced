package com.barion.dungeons_enhanced;

import com.barion.dungeons_enhanced.world.DEPools;
import com.barion.dungeons_enhanced.world.structure.*;
import com.barion.dungeons_enhanced.world.structure.prefabs.*;
import com.legacy.structure_gel.api.registry.registrar.StructureRegistrar;
import com.legacy.structure_gel.api.structure.ExtendedJigsawStructure;
import com.legacy.structure_gel.api.structure.GridStructurePlacement;
import com.legacy.structure_gel.api.structure.jigsaw.JigsawCapability;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.heightproviders.ConstantHeight;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSpawnOverride;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import static com.barion.dungeons_enhanced.DEUtil.location;

public class DEStructures {
    public static final StructureRegistrar<ExtendedJigsawStructure> CASTLE;
    public static final StructureRegistrar<ExtendedJigsawStructure> DEEP_CRYPT;
    public static final StructureRegistrar<DEDesertTemple> DESERT_TEMPLE;
    public static final StructureRegistrar<ExtendedJigsawStructure> DESERT_TOMB;
    public static final StructureRegistrar<ExtendedJigsawStructure> DRUID_CIRCLE;
    public static final StructureRegistrar<DEUndergroundStructure> DUNGEON_VARIANT;
    public static final StructureRegistrar<DEEldersTemple> ELDERS_TEMPLE;
    public static final StructureRegistrar<DESwimmingStructure> FISHING_SHIP;
    public static final StructureRegistrar<DEFlyingStructure> FLYING_DUTCHMAN;
    public static final StructureRegistrar<DEGroundStructure> HAY_STORAGE;
    public static final StructureRegistrar<DEIcePit> ICE_PIT;
    public static final StructureRegistrar<DEGroundStructure> JUNGLE_MONUMENT;
    public static final StructureRegistrar<ExtendedJigsawStructure> LARGE_DUNGEON;
    public static final StructureRegistrar<DEGroundStructure> MINERS_HOUSE;
    public static final StructureRegistrar<ExtendedJigsawStructure> MONSTER_MAZE;
    public static final StructureRegistrar<DEGroundStructure> MUSHROOM_HOUSE;
    public static final StructureRegistrar<ExtendedJigsawStructure> PILLAGER_CAMP;
    public static final StructureRegistrar<DEPirateShip> PIRATE_SHIP;
    public static final StructureRegistrar<DEGroundStructure> RUINED_BUILDING;
    public static final StructureRegistrar<DEGroundStructure> STABLES;
    public static final StructureRegistrar<DEUnderwaterStructure> SUNKEN_SHRINE;
    public static final StructureRegistrar<DEGroundStructure> TALL_WITCH_HUT;
    public static final StructureRegistrar<DEGroundStructure> TREE_HOUSE;
    public static final StructureRegistrar<DEGroundStructure> TOWER_OF_THE_UNDEAD;
    public static final StructureRegistrar<DEGroundStructure> WATCH_TOWER;
    public static final StructureRegistrar<DEGroundStructure> WITCH_TOWER;

    private DEStructures(){}

    static {
        CASTLE = StructureRegistrar.jigsawBuilder(location(DECastle.ID))
                .placement(()-> gridPlacement(56, 52).build(DEStructures.CASTLE))
                .addPiece(()-> DECastle.Piece::new)
                .pushStructure((context, settings)-> extendedJigsawStructure(context, settings, DECastle.Capability.Instance, DEPools.CASTLE, 1, ConstantHeight.ZERO).onSurface().build())
                        .biomes(BiomeTags.IS_OVERWORLD)
                        .dimensions(Level.OVERWORLD)
                        .terrainAdjustment(TerrainAdjustment.BEARD_BOX)
                .popStructure()
                .build();

        DEEP_CRYPT = StructureRegistrar.jigsawBuilder(location(DEDeepCrypt.ID))
                .placement(()-> gridPlacement(35, 74).build(DEStructures.DEEP_CRYPT))
                .addPiece(()-> DEDeepCrypt.Piece::new)
                .pushStructure((context, settings) -> extendedJigsawStructure(context, settings, DEDeepCrypt.Capability.Instance, DEPools.DEEP_CRYPT, 4, UniformHeight.of(VerticalAnchor.aboveBottom(16), VerticalAnchor.aboveBottom(48))).build())
                        .biomes(BiomeTags.IS_OVERWORLD)
                        .dimensions(Level.OVERWORLD)
                        .generationStep(GenerationStep.Decoration.UNDERGROUND_STRUCTURES)
                .popStructure()
                .build();

        DESERT_TEMPLE = StructureRegistrar.builder(location(DEDesertTemple.ID), ()-> ()-> DEDesertTemple.CODEC)
                .placement(()-> gridPlacement(31, 65).build(DEStructures.DESERT_TEMPLE))
                .addPiece(()-> DEDesertTemple.Piece::new)
                .pushStructure(DEDesertTemple::new)
                        .biomes(BiomeTags.HAS_DESERT_PYRAMID)
                        .dimensions(Level.OVERWORLD)
                .popStructure()
                .build();

        DESERT_TOMB = StructureRegistrar.jigsawBuilder(location(DEDesertTomb.ID))
                .placement(()-> gridPlacement(25, 75).allowedNearSpawn(true).build(DEStructures.DESERT_TOMB))
                .addPiece(()-> DEDesertTomb.Piece::new)
                .pushStructure((context, settings)-> extendedJigsawStructure(context, settings, DEDesertTomb.Capability.INSTANCE, DEPools.DESERT_TOMB, 5, ConstantHeight.ZERO).onSurface().build())
                        .biomes(BiomeTags.HAS_DESERT_PYRAMID)
                        .dimensions(Level.OVERWORLD)
                .popStructure()
                .build();

        DRUID_CIRCLE = StructureRegistrar.jigsawBuilder(location(DEDruidCircle.ID))
                .placement(()-> gridPlacement(41, 68).allowedNearSpawn(true).build(DEStructures.DRUID_CIRCLE))
                .addPiece(()-> DEDruidCircle.Piece::new)
                .pushStructure((context, settings)-> extendedJigsawStructure(context, settings, DEDruidCircle.Capability.INSTANCE, DEPools.DRUID_CIRCLE, 1, ConstantHeight.ZERO).onSurface().build())
                        .biomes(BiomeTags.HAS_PILLAGER_OUTPOST) //TODO: make own tag
                        .dimensions(Level.OVERWORLD)
                        .terrainAdjustment(TerrainAdjustment.BEARD_THIN)
                .popStructure()
                .build();

        DUNGEON_VARIANT = StructureRegistrar.builder(location(DEUndergroundStructure.ID_DUNGEON_VARIANT), ()-> ()-> DEUndergroundStructure.CODEC_DUNGEON_VARIANT)
                .placement(()-> gridPlacement(17, 80).allowedNearSpawn(true).build(DEStructures.DUNGEON_VARIANT))
                .addPiece(()-> DEUndergroundStructure.Piece::new)
                .pushStructure(DEUndergroundStructure::DungeonVariant)
                        .biomes(BiomeTags.IS_OVERWORLD)
                        .dimensions(Level.OVERWORLD)
                        .generationStep(GenerationStep.Decoration.UNDERGROUND_STRUCTURES)
                .popStructure()
                .build();

        ELDERS_TEMPLE = StructureRegistrar.builder(location(DEEldersTemple.ID), ()-> ()-> DEEldersTemple.CODEC)
                .placement(()-> gridPlacement(29).build(DEStructures.ELDERS_TEMPLE))
                .addPiece(()-> DEEldersTemple.Piece::new)
                .pushStructure(DEEldersTemple::new)
                        .biomes(BiomeTags.HAS_OCEAN_MONUMENT)
                        .dimensions(Level.OVERWORLD)
                        .noSpawns(StructureSpawnOverride.BoundingBoxType.STRUCTURE, MobCategory.UNDERGROUND_WATER_CREATURE, MobCategory.AXOLOTLS, MobCategory.WATER_AMBIENT, MobCategory.WATER_CREATURE)
                        .spawns(MobCategory.MONSTER, StructureSpawnOverride.BoundingBoxType.STRUCTURE, spawns(spawn(EntityType.GUARDIAN, 1,2,4)))
                .popStructure()
                .build();

        FISHING_SHIP = StructureRegistrar.builder(location(DESwimmingStructure.ID_FISHING_SHIP), ()-> ()-> DESwimmingStructure.CODEC_FISHING_SHIP)
                .placement(()-> gridPlacement(48, 68).allowedNearSpawn(true).build(DEStructures.FISHING_SHIP))
                .addPiece(()-> DEGroundStructure.Piece::new)
                .pushStructure(DESwimmingStructure::FishingShip)
                        .biomes(BiomeTags.IS_OCEAN)
                        .dimensions(Level.OVERWORLD)
                .popStructure()
                .build();

        FLYING_DUTCHMAN = StructureRegistrar.builder(location(DEFlyingStructure.ID_FLYING_DUTCHMAN), ()-> ()-> DEFlyingStructure.CODEC_FLYING_DUTCHMAN)
                .placement(()-> gridPlacement(73, 49).build(DEStructures.FLYING_DUTCHMAN))
                .addPiece(()-> DEFlyingStructure.Piece::new)
                .pushStructure(DEFlyingStructure::FlyingDutchman)
                        .biomes(BiomeTags.IS_OCEAN)
                        .dimensions(Level.OVERWORLD)
                .popStructure()
                .build();

        HAY_STORAGE = StructureRegistrar.builder(location(DEGroundStructure.ID_HAY_STORAGE), ()-> ()-> DEGroundStructure.CODEC_HAY_STORAGE)
                .placement(()-> gridPlacement(24, 75).allowedNearSpawn(true).build(DEStructures.HAY_STORAGE))
                .addPiece(()-> DEGroundStructure.Piece::new)
                .pushStructure(DEGroundStructure::HayStorage)
                        .biomes(BiomeTags.IS_SAVANNA)
                        .dimensions(Level.OVERWORLD)
                        .terrainAdjustment(TerrainAdjustment.BEARD_THIN)
                .popStructure()
                .build();

        ICE_PIT = StructureRegistrar.builder(location(DEIcePit.ID), ()-> ()-> DEIcePit.CODEC)
                .addPiece(()-> DEGroundStructure.Piece::new)
                .placement(()-> gridPlacement(35, 70).build(DEStructures.ICE_PIT))
                .pushStructure(DEIcePit::new)
                        .biomes(BiomeTags.HAS_IGLOO)
                        .dimensions(Level.OVERWORLD)
                .popStructure()
                .build();

        JUNGLE_MONUMENT = StructureRegistrar.builder(location(DEGroundStructure.ID_JUNGLE_MONUMENT), ()-> ()-> DEGroundStructure.CODEC_JUNGLE_MONUMENT)
                .placement(()-> gridPlacement(41, 75).build(DEStructures.JUNGLE_MONUMENT))
                .addPiece(()-> DEGroundStructure.Piece::new)
                .pushStructure(DEGroundStructure::JungleMonument)
                        .biomes(BiomeTags.HAS_JUNGLE_TEMPLE)
                        .dimensions(Level.OVERWORLD)
                .popStructure()
                .build();

        LARGE_DUNGEON = StructureRegistrar.jigsawBuilder(location(DELargeDungeon.ID))
                .placement(()-> gridPlacement(45, 56).allowedNearSpawn(true).build(DEStructures.LARGE_DUNGEON))
                .addPiece(()-> DELargeDungeon.Piece::new)
                .pushStructure((context, settings)-> extendedJigsawStructure(context, settings, DELargeDungeon.Capability.INSTANCE, DEPools.LARGE_DUNGEON, 5, height(-16)).onSurface().build())
                        .biomes(BiomeTags.HAS_JUNGLE_TEMPLE)
                        .dimensions(Level.OVERWORLD)
                .popStructure()
                .build();

        MINERS_HOUSE = StructureRegistrar.builder(location(DEGroundStructure.ID_MINERS_HOUSE), ()-> ()-> DEGroundStructure.CODEC_MINERS_HOUSE)
                .placement(()-> gridPlacement(24, 80).allowedNearSpawn(true).build(DEStructures.MINERS_HOUSE))
                .addPiece(()-> DEGroundStructure.Piece::new)
                .pushStructure(DEGroundStructure::MinersHouse)
                        .biomes(BiomeTags.HAS_MINESHAFT_MESA)
                        .dimensions(Level.OVERWORLD)
                        .terrainAdjustment(TerrainAdjustment.BEARD_THIN)
                .popStructure()
                .build();

        MONSTER_MAZE = StructureRegistrar.jigsawBuilder(location(DEMonsterMaze.ID))
                .placement(()-> gridPlacement(32, 42).build(DEStructures.MONSTER_MAZE))
                .addPiece(()-> DEMonsterMaze.Piece::new)
                .pushStructure((context, settings)-> extendedJigsawStructure(context, settings, DEMonsterMaze.Capability.INSTANCE, DEPools.MONSTER_MAZE, 12, height(-17)).onSurface().build())
                        .biomes(Biomes.DARK_FOREST)
                        .dimensions(Level.OVERWORLD)
                .popStructure()
                .build();

        MUSHROOM_HOUSE = StructureRegistrar.builder(location(DEGroundStructure.ID_MUSHROOM_HOUSE), ()-> ()-> DEGroundStructure.CODEC_MUSHROOM_HOUSE)
                .placement(()-> gridPlacement(19, 83).allowedNearSpawn(true).build(DEStructures.MUSHROOM_HOUSE))
                .addPiece(()-> DEGroundStructure.Piece::new)
                .pushStructure(DEGroundStructure::MushroomHouse)
                        .biomes(Biomes.MUSHROOM_FIELDS)
                        .dimensions(Level.OVERWORLD)
                        .terrainAdjustment(TerrainAdjustment.BEARD_THIN)
                .popStructure()
                .build();

        PILLAGER_CAMP = StructureRegistrar.jigsawBuilder(location(DEPillagerCamp.ID))
                .placement(()-> gridPlacement(49, 36).build(DEStructures.PILLAGER_CAMP))
                .addPiece(()-> DEPillagerCamp.Piece::new)
                .pushStructure((context, settings)-> extendedJigsawStructure(context, settings, DEPillagerCamp.Capability.INSTANCE, DEPools.PILLAGER_CAMP, 4, ConstantHeight.ZERO).onSurface().build())
                        .biomes(BiomeTags.HAS_PILLAGER_OUTPOST)
                        .dimensions(Level.OVERWORLD)
                        .spawns(MobCategory.MONSTER, StructureSpawnOverride.BoundingBoxType.STRUCTURE, spawns(spawn(EntityType.PILLAGER, 4, 2, 3), spawn(EntityType.VINDICATOR, 2, 1, 2)))
                        .terrainAdjustment(TerrainAdjustment.BEARD_THIN)
                .popStructure()
                .build();

        PIRATE_SHIP = StructureRegistrar.builder(location(DEPirateShip.ID), ()-> ()-> DEPirateShip.CODEC)
                .placement(()-> gridPlacement(65, 51).build(DEStructures.PIRATE_SHIP))
                .addPiece(()-> DEGroundStructure.Piece::new)
                .pushStructure(DEPirateShip::new)
                        .biomes(BiomeTags.IS_OCEAN)
                        .dimensions(Level.OVERWORLD)
                        .spawns(MobCategory.MONSTER, StructureSpawnOverride.BoundingBoxType.STRUCTURE, spawns(spawn(EntityType.PILLAGER, 4, 3, 4), spawn(EntityType.VINDICATOR, 3, 1, 2)))
                        .noSpawns(StructureSpawnOverride.BoundingBoxType.STRUCTURE, MobCategory.UNDERGROUND_WATER_CREATURE, MobCategory.AXOLOTLS, MobCategory.WATER_AMBIENT, MobCategory.WATER_CREATURE)
                .popStructure()
                .build();

        RUINED_BUILDING = StructureRegistrar.builder(location(DEGroundStructure.ID_RUINED_BUILDING), ()-> ()-> DEGroundStructure.CODEC_RUINED_BUILDING)
                .placement(()-> gridPlacement(27, 54).allowedNearSpawn(true).build(DEStructures.RUINED_BUILDING))
                .addPiece(()-> DEGroundStructure.Piece::new)
                .pushStructure(DEGroundStructure::RuinedBuilding)
                        .biomes(BiomeTags.IS_TAIGA) // TODO: own tag
                        .dimensions(Level.OVERWORLD)
                        .terrainAdjustment(TerrainAdjustment.BEARD_THIN)
                .popStructure()
                .build();

        STABLES = StructureRegistrar.builder(location(DEGroundStructure.ID_STABLES), ()-> ()-> DEGroundStructure.CODEC_STABLES)
                .placement(()-> gridPlacement(46, 54).allowedNearSpawn(true).build(DEStructures.STABLES))
                .addPiece(()-> DEGroundStructure.Piece::new)
                .pushStructure(DEGroundStructure::Stables)
                        .biomes(BiomeTags.HAS_VILLAGE_PLAINS) // TODO: own tag
                        .dimensions(Level.OVERWORLD)
                .popStructure()
                .build();

        SUNKEN_SHRINE = StructureRegistrar.builder(location(DEUnderwaterStructure.ID_SUNKEN_SHRINE), ()-> ()-> DEUnderwaterStructure.CODEC_SUNKEN_SHRINE)
                .placement(()-> gridPlacement(32, 55).allowedNearSpawn(true).build(DEStructures.SUNKEN_SHRINE))
                .addPiece(()-> DEUnderwaterStructure.Piece::new)
                .pushStructure(DEUnderwaterStructure::SunkenShrine)
                        .biomes(BiomeTags.IS_OCEAN)
                        .dimensions(Level.OVERWORLD)
                .popStructure()
                .build();

        TALL_WITCH_HUT = StructureRegistrar.builder(location(DEGroundStructure.ID_TALL_WITCH_HUT), ()-> ()-> DEGroundStructure.CODEC_TALL_WITCH_HUT)
                .placement(()-> gridPlacement(19, 60).allowedNearSpawn(true).build(DEStructures.TALL_WITCH_HUT))
                .addPiece(()-> DEGroundStructure.Piece::new)
                .pushStructure(DEGroundStructure::TallWitchHut)
                        .biomes(BiomeTags.HAS_SWAMP_HUT)
                        .dimensions(Level.OVERWORLD)
                        //.terrainAdjustment(TerrainAdjustment.BEARD_THIN)
                .popStructure()
                .build();

        TOWER_OF_THE_UNDEAD = StructureRegistrar.builder(location(DEGroundStructure.ID_TOWER_OF_THE_UNDEAD), ()-> ()-> DEGroundStructure.CODEC_TOWER_OF_THE_UNDEAD)
                .placement(()-> gridPlacement(37, 35).allowedNearSpawn(true).build(DEStructures.TOWER_OF_THE_UNDEAD))
                .addPiece(()-> DEGroundStructure.Piece::new)
                .pushStructure(DEGroundStructure::TowerOfTheUndead)
                        .biomes(BiomeTags.HAS_VILLAGE_PLAINS) // TODO: own tag
                        .dimensions(Level.OVERWORLD)
                        .terrainAdjustment(TerrainAdjustment.BEARD_THIN)
                .popStructure()
                .build();

        TREE_HOUSE = StructureRegistrar.builder(location(DEGroundStructure.ID_TREE_HOUSE), ()-> ()-> DEGroundStructure.CODEC_TREE_HOUSE)
                .placement(()-> gridPlacement(29, 40).allowedNearSpawn(true).build(DEStructures.TREE_HOUSE))
                .addPiece(()-> DEGroundStructure.Piece::new)
                .pushStructure(DEGroundStructure::TreeHouse)
                        .biomes(BiomeTags.IS_JUNGLE)
                        .dimensions(Level.OVERWORLD)
                        .terrainAdjustment(TerrainAdjustment.BEARD_THIN)
                .popStructure()
                .build();

        WATCH_TOWER = StructureRegistrar.builder(location(DEGroundStructure.ID_WATCH_TOWER), ()-> ()-> DEGroundStructure.CODEC_WATCH_TOWER)
                .addPiece(()-> DEGroundStructure.Piece::new)
                .pushStructure(DEGroundStructure::WatchTower)
                        .biomes(BiomeTags.HAS_VILLAGE_PLAINS)
                        .dimensions(Level.OVERWORLD)
                        .terrainAdjustment(TerrainAdjustment.BEARD_THIN)
                .popStructure()
                .placement(()-> gridPlacement(33, 45).allowedNearSpawn(true).build(DEStructures.WATCH_TOWER))
                .build();

        WITCH_TOWER = StructureRegistrar.builder(location(DEGroundStructure.ID_WITCH_TOWER), ()-> ()-> DEGroundStructure.CODEC_WITCH_TOWER)
                .placement(()-> gridPlacement(29, 45).allowedNearSpawn(true).build(DEStructures.WITCH_TOWER))
                .addPiece(()-> DEGroundStructure.Piece::new)
                .pushStructure(DEGroundStructure::WitchTower)
                        .biomes(BiomeTags.IS_TAIGA)
                        .dimensions(Level.OVERWORLD)
                        .terrainAdjustment(TerrainAdjustment.BEARD_THIN)
                .popStructure()
                .build();
    }

    public static StructureRegistrar<?>[] getAllStructureRegistrars() {
        return new StructureRegistrar<?>[] {
                CASTLE,
                DEEP_CRYPT,
                DESERT_TEMPLE,
                DESERT_TOMB,
                DRUID_CIRCLE,
                DUNGEON_VARIANT,
                ELDERS_TEMPLE,
                FISHING_SHIP,
                FLYING_DUTCHMAN,
                HAY_STORAGE,
                ICE_PIT,
                JUNGLE_MONUMENT,
                LARGE_DUNGEON,
                MINERS_HOUSE,
                MONSTER_MAZE,
                MUSHROOM_HOUSE,
                PILLAGER_CAMP,
                PIRATE_SHIP,
                RUINED_BUILDING,
                STABLES,
                SUNKEN_SHRINE,
                TALL_WITCH_HUT,
                TOWER_OF_THE_UNDEAD,
                TREE_HOUSE,
                WATCH_TOWER,
                WITCH_TOWER
        };
    }

    private static ConstantHeight height(int y) {return ConstantHeight.of(new VerticalAnchor.Absolute(y));}
    private static Supplier<List<MobSpawnSettings.SpawnerData>> spawns(MobSpawnSettings.SpawnerData... spawns) {return ()-> Arrays.stream(spawns).toList();}
    private static MobSpawnSettings.SpawnerData spawn(EntityType<?> entity, int weight, int min, int max) {
        return new MobSpawnSettings.SpawnerData(entity, weight, min, max);
    }

    private static GridStructurePlacement.Builder gridPlacement(int spacing, int probability){
        return gridPlacement(spacing).probability(probability/100f);
    }
    private static GridStructurePlacement.Builder gridPlacement(int spacing){
        return GridStructurePlacement.builder().spacing(spacing).offset((int) (spacing*0.8));
    }

    private static ExtendedJigsawStructure.Builder extendedJigsawStructure(BootstapContext<?> context, Structure.StructureSettings settings, JigsawCapability.IJigsawCapability capability, ResourceKey<StructureTemplatePool> poolKey, int maxDepth, HeightProvider heightProvider){
        return ExtendedJigsawStructure.builder(settings, context.lookup(Registries.TEMPLATE_POOL).getOrThrow(poolKey)).maxDepth(maxDepth).startHeight(heightProvider).capability(capability);
    }
}