package com.adso.constants;

import java.util.HashMap;
import java.util.Map;

import com.adso.enums.Rarity;

public class AppItemsPrices {
    public static int getPetPriceFromRarity(Rarity rarity) {
        return PriceConstants.PET_PRICES.getOrDefault(rarity, null);
    }

    public static int getCardPriceFromRarity(Rarity rarity) {
        return PriceConstants.CARD_PRICES.getOrDefault(rarity, null);
    }
    
    public static Map<String, Map<Rarity, Integer>> getAllPrices() {
        Map<String, Map<Rarity, Integer>> allPrices = new HashMap<>();
        allPrices.put("Pets", PriceConstants.PET_PRICES);
        allPrices.put("Cards", PriceConstants.CARD_PRICES);
        return allPrices;
    }
}

