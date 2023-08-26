package com.adso.dao.interfaces;

import java.util.List;
import java.util.Map;

import com.adso.entities.Card;
import com.adso.entities.Pet;
import com.adso.exceptions.app.NotFoundException;
import com.adso.exceptions.app.NotResultsToShowException;

public interface AppDAO {
	List<Card> getAppCards(Map<String, Object> queryParams);
	Card getAppCard(Long id) throws NotFoundException;
	List<Pet> getAppPets();
	Long getTotalNumberOfCards();
	Map<String, Object> getStoreItems() throws NotResultsToShowException;
}
