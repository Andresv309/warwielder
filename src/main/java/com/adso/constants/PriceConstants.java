package com.adso.constants;

import java.util.HashMap;
import java.util.Map;

import com.adso.enums.Rarity;

public class PriceConstants {
    public static final Map<Rarity, Integer> PET_PRICES = new HashMap<>();
    public static final Map<Rarity, Integer> CARD_PRICES = new HashMap<>();

    static {
        PET_PRICES.put(Rarity.FREE, 15000);
        PET_PRICES.put(Rarity.COMMON, 30000);
        PET_PRICES.put(Rarity.RARE, 60000);
        PET_PRICES.put(Rarity.EPIC, 120000);
        PET_PRICES.put(Rarity.LEGENDARY, 240000);

        CARD_PRICES.put(Rarity.FREE, 1000);
        CARD_PRICES.put(Rarity.COMMON, 8000);
        CARD_PRICES.put(Rarity.RARE, 16000);
        CARD_PRICES.put(Rarity.EPIC, 32000);
        CARD_PRICES.put(Rarity.LEGENDARY, 72000);
    }
}
