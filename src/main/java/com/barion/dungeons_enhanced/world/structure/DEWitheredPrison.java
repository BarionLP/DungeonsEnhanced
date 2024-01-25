package com.barion.dungeons_enhanced.world.structure;

import com.barion.dungeons_enhanced.DungeonsEnhanced;
import com.barion.dungeons_enhanced.registry.DEJigsawTypes;
import com.barion.dungeons_enhanced.registry.DEStructures;
import com.barion.dungeons_enhanced.registry.DETemplatePools;
import com.legacy.structure_gel.api.structure.jigsaw.*;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

public class DEWitheredPrison {
    public static final String ID = "withered_prison";

    public static class Capability implements JigsawCapability {
        public static final Capability INSTANCE = new Capability();
        public static final Codec<Capability> CODEC = Codec.unit(INSTANCE);

        @Override
        public JigsawCapabilityType<?> getType() {return DEJigsawTypes.WITHERED_PRISON.get();}
        @Override
        public IPieceFactory getPieceFactory() {return Piece::new;}
    }

    public static class Piece extends ExtendedJigsawStructurePiece {
        public Piece(IPieceFactory.Context context) {super(context);}
        public Piece(StructurePieceSerializationContext serializationContext, CompoundTag nbt) {super(serializationContext, nbt);}

        @Override
        public StructurePieceType getType() {return DEStructures.WITHERED_PRISON.getPieceType().get();}
        @Override
        public void handleDataMarker(String key, BlockPos pos, ServerLevelAccessor level, RandomSource random, BoundingBox box) {}
    }

    public static void pool(BootstapContext<StructureTemplatePool> context){
        var registry = new JigsawRegistryHelper(DungeonsEnhanced.MOD_ID, "withered_prison/", context);
        var basicPool = registry.poolBuilder().maintainWater(true);
        registry.registerBuilder().pools(basicPool.clone().names("main")).register(DETemplatePools.WITHERED_PRISON);

        var bridges = basicPool.clone().names("bridge", "bridge_broken");
        var pillars = basicPool.clone().names("bridge_pillar_1", "bridge_pillar_2");
        var mainExtensions = basicPool.clone().names("main_bridge_extension");

        registry.register("bridge", bridges);
        registry.register("pillar", pillars);
        registry.register("main_extension", mainExtensions);
    }
}
