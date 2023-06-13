package com.barion.dungeons_enhanced.world;

import com.barion.dungeons_enhanced.DEUtil;
import net.minecraft.resources.ResourceLocation;

public interface DELootTables {
    ResourceLocation SUNKEN_SHRINE = chest("sunken_shrine");
    ResourceLocation MINERS_HOUSE = chest("miners_house");

    interface PILLAGER_CAMP{
        ResourceLocation GENERAL = chest("pillager_camp/general");
        ResourceLocation KITCHEN = chest("pillager_camp/kitchen");
    }

    private static ResourceLocation chest(String key) {
        return DEUtil.location("chests/" + key);
    }
}
