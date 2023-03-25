package com.barion.dungeons_enhanced.world.structure;

import com.barion.dungeons_enhanced.DEStructures;
import com.barion.dungeons_enhanced.DEUtil;
import com.barion.dungeons_enhanced.world.structure.prefabs.DESimpleStructure;
import com.barion.dungeons_enhanced.world.structure.prefabs.utils.DEPieceAssembler;
import com.barion.dungeons_enhanced.world.structure.prefabs.utils.DEStructurePieces;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.Heightmap;

import javax.annotation.Nonnull;
import java.util.Optional;

import static com.barion.dungeons_enhanced.DEUtil.location;

public class DEIcePit extends DESimpleStructure {
    public static final Codec<DEIcePit> CODEC = simpleCodec(DEIcePit::new);
    private static final ResourceLocation ENTRANCE = location("ice_pit/top");
    public DEIcePit(StructureSettings settings) {super(settings, DEUtil.pieceBuilder().yOffset(-25).add("ice_pit/var1").add("ice_pit/var2").add("ice_pit/var3").build(), DEStructures.ICE_PIT::getType);}

    @Override @Nonnull
    public Optional<GenerationStub> findGenerationPoint(@Nonnull GenerationContext context) {
        DEStructurePieces.Piece piece = variants.getRandomPiece(context.random());
        final BlockPos pos = DEUtil.ChunkPosToBlockPosFromHeightMap(context.chunkPos(), context.chunkGenerator(), Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor(), context.randomState()).above(piece.yOffset);
        return at(pos, (builder)-> generatePieces(builder, pos, piece, Rotation.getRandom(context.random()), context, DEIcePit::assembleIcePit));
    }

    private static void assembleIcePit(DEPieceAssembler.Context context) {
        BlockPos pos = context.pos();
        context.piecesBuilder().addPiece(new Piece(context.structureManager(), ENTRANCE, pos, context.rotation()));
        int yOffset = -6;
        if(context.piece().getPath().contains("var3")) {yOffset = -11;}
        context.piecesBuilder().addPiece(new Piece(context.structureManager(), context.piece(), pos.offset(-17, yOffset,-17), context.rotation()));
    }
}