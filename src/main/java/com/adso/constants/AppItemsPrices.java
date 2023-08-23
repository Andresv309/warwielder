package com.adso.constants;

import com.adso.enums.Rarity;

public class AppItemsPrices {
    public static int getPetPriceFromRarity(Rarity rarity) {
        return PriceConstants.PET_PRICES.getOrDefault(rarity, null);
    }

    public static int getCardPriceFromRarity(Rarity rarity) {
        return PriceConstants.CARD_PRICES.getOrDefault(rarity, null);
    }
}

