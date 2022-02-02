package com.barion.dungeons_enhanced.structures.pools;

import com.barion.dungeons_enhanced.DungeonsEnhanced;
import com.legacy.structure_gel.worldgen.jigsaw.JigsawPoolBuilder;
import com.legacy.structure_gel.worldgen.jigsaw.JigsawRegistryHelper;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;

public class DEMonsterMazePool {
    public static final JigsawPattern Root;

    public DEMonsterMazePool(){

    }

    public static void init(){}

    static {
        JigsawRegistryHelper registry = new JigsawRegistryHelper(DungeonsEnhanced.Mod_ID, "monster_maze/");
        Root = registry.register("root", registry.builder().names("root").maintainWater(false).build());

        JigsawPoolBuilder poolBuilder = registry.builder().maintainWater(false);
        JigsawPoolBuilder CrossTunnels = poolBuilder.clone().names("tunnels/cross1");
        JigsawPoolBuilder EdgeTunnels = poolBuilder.clone().names("tunnels/edge1", "tunnels/edge2");
        JigsawPoolBuilder RoomTunnels = poolBuilder.clone().names("tunnels/room1", "tunnels/room2", "tunnels/room3");
        JigsawPoolBuilder ShortTunnels = poolBuilder.clone().names("tunnels/small1", "tunnels/small2", "tunnels/small3");
        JigsawPoolBuilder LongTunnels = poolBuilder.clone().names("tunnels/big1", "tunnels/big2", "tunnels/big3", "tunnels/big4", "tunnels/big5");
        JigsawPoolBuilder LongStairs = poolBuilder.clone().names("stairs/big1", "stairs/big2");
        JigsawPoolBuilder Rooms = poolBuilder.clone().names("big_room", "church", "prison", "room1", "storage");
        JigsawPoolBuilder Boss = poolBuilder.clone().names("boss");

        registry.register("tunnels/cross", CrossTunnels.build());
        registry.register("tunnels/edge", EdgeTunnels.build());
        registry.register("tunnels/room", RoomTunnels.build());
        registry.register("tunnels/small", ShortTunnels.build());
        registry.register("tunnels/big", LongTunnels.build());
        registry.register("stairs", LongStairs.build());
        registry.register("rooms", Rooms.build());
        registry.register("boss", Boss.build());

        registry.register("tree", poolBuilder.clone().names("tree").build());
        registry.register("tunnels", JigsawPoolBuilder.collect(EdgeTunnels, RoomTunnels, ShortTunnels, LongTunnels));
        registry.register("main", JigsawPoolBuilder.collect(EdgeTunnels.weight(4), RoomTunnels.weight(3), ShortTunnels.weight(3), LongTunnels.weight(3), CrossTunnels.weight(4), Rooms.weight(2)));
    }
}