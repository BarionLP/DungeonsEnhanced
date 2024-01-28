package com.barion.dungeons_enhanced.world.structure.builder;

import com.barion.dungeons_enhanced.DEUtil;
import com.barion.dungeons_enhanced.world.structure.DEModularStructure;
import com.legacy.structure_gel.api.registry.registrar.StructureRegistrar;
import com.legacy.structure_gel.api.structure.GridStructurePlacement;
import com.mojang.serialization.Codec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.Structure;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public final class DEModularRegistrarBuilder {
    public static DEModularRegistrarBuilder create(StructureRegistrar<DEModularStructure> registrar, String id){return create(registrar, id, null);}
    public static DEModularRegistrarBuilder create(StructureRegistrar<DEModularStructure> registrar, String id, Codec<DEModularStructure> codec) {return create(registrar, DEUtil.location(id), codec);}
    public static DEModularRegistrarBuilder create(StructureRegistrar<DEModularStructure> registrar, ResourceLocation id, Codec<DEModularStructure> codec){
        return new DEModularRegistrarBuilder(registrar, id, codec);
    }

    private final StructureRegistrar<DEModularStructure> _registrar;
    private final StructureRegistrar.Builder<DEModularStructure> _builder;
    private final GridStructurePlacement.Builder _placement = GridStructurePlacement.builder();
    private Codec<DEModularStructure> _codec;

    public DEModularRegistrarBuilder(StructureRegistrar<DEModularStructure> registrar, ResourceLocation resourceLocation, @Nullable Codec<DEModularStructure> codec) {
        _registrar = registrar;
        _codec = codec;
        _builder = StructureRegistrar.builder(resourceLocation, ()-> ()-> _codec);
    }

    public DEModularRegistrarBuilder placement(int spacing){
        return placement(spacing, 1);
    }
    public DEModularRegistrarBuilder placement(int spacing, float probability){
        return placement(spacing, (int)(spacing*0.8), probability);
    }
    public DEModularRegistrarBuilder placement(int spacing, int offset, float probability){
        _placement.spacing(spacing).offset(offset).probability(probability);
        return this;
    }
    public DEModularRegistrarBuilder placement(Consumer<GridStructurePlacement.Builder> placementConsumer){
        placementConsumer.accept(_placement);
        return this;
    }

    public DEModularRegistrarBuilder allowNearSpawn() {return allowNearSpawn(true);}
    public DEModularRegistrarBuilder allowNearSpawn(boolean allow){
        _placement.allowedNearSpawn(allow);
        return this;
    }

    public DEModularRegistrarBuilder addStructure(Consumer<DERandomPieceFactory.Builder> pieceFactoryConsumer, Consumer<DEModularStructure.Builder> builderConsumer, Consumer<StructureRegistrar.StructureBuilder<DEModularStructure>> configuratorConsumer){
        var pieceFactoryBuilder = new DERandomPieceFactory.Builder();
        pieceFactoryConsumer.accept(pieceFactoryBuilder);
        var pieceFactory = pieceFactoryBuilder.build(()-> _registrar.getPieceType().get());
        _builder.addPiece(()-> pieceFactory::createPiece);

        var builder = new DEModularStructure.Builder(pieceFactory, _registrar::getType);
        builderConsumer.accept(builder);
        if(_codec == null) _codec = Structure.simpleCodec(builder::build);

        var configurator = _builder.pushStructure(builder::build);
//        configurator.lakeProof(true);
        configuratorConsumer.accept(configurator);
        configurator.popStructure();

        return this;
    }

    public StructureRegistrar<DEModularStructure> build(){
         return _builder.placement(()-> _placement.build(_registrar)).build();
    }
}
