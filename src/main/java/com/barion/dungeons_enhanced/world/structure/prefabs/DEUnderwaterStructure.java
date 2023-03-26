package com.barion.dungeons_enhanced.world.structure.prefabs;

import com.barion.dungeons_enhanced.DEUtil;
import com.barion.dungeons_enhanced.world.gen.DETerrainAnalyzer;
import com.barion.dungeons_enhanced.world.structure.prefabs.utils.DEPieceAssembler;
import com.barion.dungeons_enhanced.world.structure.prefabs.utils.DEStructurePieces;
import com.barion.dungeons_enhanced.world.structure.processor.DEUnderwaterProcessor;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

import javax.annotation.Nonnull;
import java.util.Optional;
import java.util.function.Supplier;

import static com.barion.dungeons_enhanced.DEStructures.SUNKEN_SHRINE;
import static com.barion.dungeons_enhanced.DEUtil.pieceBuilder;

public class DEUnderwaterStructure extends DEBaseStructure {

    public static final Codec<DEUnderwaterStructure> CODEC_SUNKEN_SHRINE = simpleCodec(DEUnderwaterStructure::SunkenShrine);

    public static DEUnderwaterStructure SunkenShrine(StructureSettings settings){
        return new DEUnderwaterStructure(settings, pieceBuilder().yOffset(-1).add("sunken_shrine").build(), SUNKEN_SHRINE::getType);
    }

    public DEUnderwaterStructure(StructureSettings settings, DEStructurePieces variants, Supplier<StructureType<?>> type) {super(settings, variants, type);}

    @Override @Nonnull
    public Optional<GenerationStub> findGenerationPoint(@Nonnull GenerationContext context) {
        final DEStructurePieces.Piece piece = variants.getRandomPiece(context.random());
        final BlockPos pos = DEUtil.ChunkPosToBlockPosFromHeightMap(context.chunkPos(), context.chunkGenerator(), Heightmap.Types.OCEAN_FLOOR_WG, context.heightAccessor(), context.randomState()).above(piece.yOffset);

        if(!DETerrainAnalyzer.isUnderwater(pos, context.chunkGenerator(), 16, context.heightAccessor(), context.randomState())) {return Optional.empty();}

        return at(pos, (builder) -> generatePieces(builder, pos, piece, Rotation.getRandom(context.random()), context, DEUnderwaterStructure::assemble));
    }

    private static void assemble(DEPieceAssembler.Context context) {
        context.piecesBuilder().addPiece(new Piece(context.structureManager(), context.piece(), context.pos(), context.rotation()));
    }

    public static class Piece extends DEBaseStructure.Piece {
        public Piece(StructureTemplateManager structureManager, ResourceLocation templateName, BlockPos pos, Rotation rotation){
            super(SUNKEN_SHRINE.getPieceType(), structureManager, templateName, pos, rotation);
        }
        public Piece(StructurePieceSerializationContext serializationContext, CompoundTag nbt){
            super(SUNKEN_SHRINE.getPieceType(), serializationContext, nbt);
        }

        @Override
        protected void addProcessors(StructurePlaceSettings settings) {
            settings.clearProcessors();
            settings.addProcessor(DEUnderwaterProcessor.INSTANCE);
        }
    }
}