package com.barion.dungeons_enhanced.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.DEStructures;
import com.barion.dungeons_enhanced.structures.prefabs.DESimpleStructure;

public class DERuinedHouse extends DESimpleStructure {
    public DERuinedHouse(){
        super(DEConfig.COMMON.ruined_house);
        MainPiece = DEStructures.locate("ruined_house");
        Offset = Offset(-5, 0, -5);
    }

    @Override
    public int getSeed() {
        return 99658;
    }
}