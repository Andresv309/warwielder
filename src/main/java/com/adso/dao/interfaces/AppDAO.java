package com.adso.dao.interfaces;

import java.util.List;

import com.adso.entities.Card;
import com.adso.entities.Pet;
import com.adso.exceptions.cards.NotFoundCardException;

public interface AppDAO {
	List<Card> getAppCards();
	Card getAppCard(Long id) throws NotFoundCardException;
	List<Pet> getAppPets();
}
