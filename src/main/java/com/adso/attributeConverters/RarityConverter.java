package com.adso.attributeConverters;

import com.adso.enums.Rarity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RarityConverter implements AttributeConverter<Rarity, Integer> {

	@Override
	public Integer convertToDatabaseColumn(Rarity attribute) {
        if (attribute == null)
            return null;
        
        switch (attribute) {
	        case FREE:
	        	return 1;
	        	
	        case COMMON:
	            return 2;
	 
	        case RARE:
	            return 3;
	            
	        case EPIC:
	        	return 4;
	        	
	        case LEGENDARY:
	        	return 5;
	        
	        default:
	            throw new IllegalArgumentException(attribute + " not supported.");
        }
 
	}

	@Override
	public Rarity convertToEntityAttribute(Integer dbData) {
        if (dbData == null)
        	return null;
        
        switch (dbData) {
	        case 1:
	        	return Rarity.FREE;
	        	
	        case 2:
	            return Rarity.COMMON;
	 
	        case 3:
	        	return Rarity.RARE;
	            
	        case 4:
	        	return Rarity.EPIC;
	        	
	        case 5:
	        	return Rarity.LEGENDARY;
	        
	        default:
	            throw new IllegalArgumentException(dbData + " not supported.");
        }
	}

}
