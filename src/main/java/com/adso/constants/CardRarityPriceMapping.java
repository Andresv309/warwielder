package com.adso.constants;

import java.util.HashMap;
import java.util.Map;

import com.adso.enums.Rarity;

public class CardRarityPriceMapping {
    private static final Map<Rarity, Integer> rarityPriceMap = new HashMap<>();

    static {
        rarityPriceMap.put(Rarity.FREE, 1000);
        rarityPriceMap.put(Rarity.COMMON, 5000);
        rarityPriceMap.put(Rarity.RARE, 10000);
        rarityPriceMap.put(Rarity.EPIC, 20000);
        rarityPriceMap.put(Rarity.LEGENDARY, 50000);
    }

    public static int getPriceForRarity(Rarity rarity) {
        return rarityPriceMap.getOrDefault(rarity, 0); // Default price if rarity is not found
    }
}
