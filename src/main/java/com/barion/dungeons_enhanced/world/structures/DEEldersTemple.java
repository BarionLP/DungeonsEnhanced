package com.barion.dungeons_enhanced.world.structures;

import com.barion.dungeons_enhanced.DEStructures;
import com.barion.dungeons_enhanced.DEUtil;
import com.barion.dungeons_enhanced.world.gen.DETerrainAnalyzer;
import com.barion.dungeons_enhanced.world.structures.prefabs.DEBaseStructure;
import com.barion.dungeons_enhanced.world.structures.prefabs.DEUnderwaterStructure;
import com.barion.dungeons_enhanced.world.structures.prefabs.utils.DEPieceAssembler;
import com.barion.dungeons_enhanced.world.structures.prefabs.utils.DEUnderwaterProcessor;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

import static com.barion.dungeons_enhanced.DEUtil.location;

public class DEEldersTemple extends DEUnderwaterStructure {
    private static final ResourceLocation NE = location("elders_temple/ne");
    private static final ResourceLocation NW = location("elders_temple/nw");
    private static final ResourceLocation SE = location("elders_temple/se");
    private static final ResourceLocation SW = location("elders_temple/sw");

    public DEEldersTemple(StructureSettings settings) {super(settings, DEUtil.pieceBuilder().add("elders_temple/se").build(), DEEldersTemple::assembleTemple);}

    private static boolean checkLocation(PieceGeneratorSupplier.Context<NoneFeatureConfiguration> context){
        if(DETerrainAnalyzer.isUnderwater(context.chunkPos(), context.chunkGenerator(), 32, context.heightAccessor(), context.randomState())){
            return DETerrainAnalyzer.areNearbyBiomesValid(context.biomeSource(), context.chunkPos(), context.chunkGenerator(), 30, context.validBiome(), context.randomState());
        }
        return false;
    }

    public static void assembleTemple(DEPieceAssembler.Context context) {
        Rotation rotation = Rotation.NONE;
        context.piecesBuilder().addPiece(new Piece(context.structureManager(), SE, context.pos().offset(0,0,0), rotation));
        context.piecesBuilder().addPiece(new Piece(context.structureManager(), SW, context.pos().offset(-30, 0, 0), rotation));
        context.piecesBuilder().addPiece(new Piece(context.structureManager(), NE, context.pos().offset(0, 0, -29), rotation));
        context.piecesBuilder().addPiece(new Piece(context.structureManager(), NW, context.pos().offset(-30, 0, -29), rotation));
    }

    public static class Piece extends DEBaseStructure.Piece {
        public Piece(StructureTemplateManager structureManager, ResourceLocation templateName, BlockPos pos, Rotation rotation){
            super(DEStructures.EldersTemple.getPieceType(), structureManager, templateName, pos, rotation);
        }

        public Piece(StructurePieceSerializationContext serializationContext, CompoundTag nbt){
            super(DEStructures.EldersTemple.getPieceType(), serializationContext, nbt);
        }

        @Override
        protected void addProcessors(StructurePlaceSettings settings) {
            settings.clearProcessors();
            settings.addProcessor(DEUnderwaterProcessor.Instance)
                    .addProcessor(DEUtil.Processors.BrainCoral)
                    .addProcessor(DEUtil.Processors.BubbleCoral)
                    .addProcessor(DEUtil.Processors.FireCoral)
                    .addProcessor(DEUtil.Processors.HornCoral)
                    .addProcessor(DEUtil.Processors.TubeCoral);
        }
    }
}