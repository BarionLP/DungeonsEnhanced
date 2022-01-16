package com.barion.dungeons_enhanced.structures.prefabs;

import com.barion.dungeons_enhanced.DEStructures;
import com.legacy.structure_gel.api.config.StructureConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;

public class DEFloatingStructure extends DEBaseStructure{
    public DEFloatingStructure(StructureConfig config, boolean generateNearSpawn, DEPiece... resources) {
        super(config, GenerationType.inAir, generateNearSpawn, resources);
    }

    @Override
    protected void assemble(StructureManager structureManager, DEPiece[] variants, BlockPos pos, Rotation rotation, StructurePiecesBuilder piecesBuilder, int piece) {
        piecesBuilder.addPiece(new Piece(structureManager, Variants[piece].Resource, pos, rotation));
    }

    public static class Piece extends DEBaseStructure.Piece {
        public Piece(StructureManager structureManager, ResourceLocation templateName, BlockPos pos, Rotation rotation, int componentType) {
            super(DEStructures.RuinedBuilding.getPieceType(), structureManager, templateName, pos, rotation, componentType);
        }

        public Piece(StructureManager structureManager, ResourceLocation templateName, BlockPos pos, Rotation rotation) {
            this(structureManager, templateName, pos, rotation, 0);
        }

        public Piece(StructurePieceSerializationContext serializationContext, CompoundTag nbt) {
            super(DEStructures.RuinedBuilding.getPieceType(), serializationContext, nbt);
        }
    }
}
