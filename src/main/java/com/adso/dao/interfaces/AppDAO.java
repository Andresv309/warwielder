package com.adso.dao.interfaces;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.adso.entities.Card;
import com.adso.entities.Pet;
import com.adso.exceptions.cards.NotFoundCardException;

public interface AppDAO {
	List<Card> getAppCards(Map<String, Object> queryParams);
	Card getAppCard(Long id) throws NotFoundCardException;
	List<Pet> getAppPets();
	Long getTotalNumberOfCards();
	Map<String, Object> getStoreItems();
}
