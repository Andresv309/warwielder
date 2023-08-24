package com.adso.attributeConverters;

import com.adso.enums.Race;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RaceConverter implements AttributeConverter<Race, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Race attribute) {
        if (attribute == null)
            return null;

        return attribute.ordinal(); // Save the ordinal value
    }

    @Override
    public Race convertToEntityAttribute(Integer dbData) {
        if (dbData == null)
            return null;

        if (dbData < 0 || dbData >= Race.values().length) {
            throw new IllegalArgumentException("Invalid Race ordinal: " + dbData);
        }

        return Race.values()[dbData];
    }
}

