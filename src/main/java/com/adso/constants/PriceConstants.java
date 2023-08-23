package com.adso.constants;

import java.util.HashMap;
import java.util.Map;

import com.adso.enums.Rarity;

public class PriceConstants {
    public static final Map<Rarity, Integer> PET_PRICES = new HashMap<>();
    public static final Map<Rarity, Integer> CARD_PRICES = new HashMap<>();

    static {
        PET_PRICES.put(Rarity.COMMON, 800);
        PET_PRICES.put(Rarity.RARE, 4000);
        PET_PRICES.put(Rarity.ADVANCED, 8000);
        PET_PRICES.put(Rarity.EPIC, 16000);
        PET_PRICES.put(Rarity.LEGENDARY, 40000);

        CARD_PRICES.put(Rarity.COMMON, 1000);
        CARD_PRICES.put(Rarity.RARE, 5000);
        CARD_PRICES.put(Rarity.ADVANCED, 10000);
        CARD_PRICES.put(Rarity.EPIC, 20000);
        CARD_PRICES.put(Rarity.LEGENDARY, 50000);
    }
}
