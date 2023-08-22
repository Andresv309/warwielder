package com.adso.dao.interfaces;

import java.util.List;
import java.util.Set;

import com.adso.entities.Card;
import com.adso.entities.DeckCard;
import com.adso.entities.Pet;
import com.adso.entities.User;
import com.adso.exceptions.decks.CardAlreadyInDeckException;
import com.adso.exceptions.decks.NotValidPositionValue;
import com.adso.exceptions.pets.NotFoundPetException;
import com.adso.exceptions.user.UserAlreadyExistsException;
import com.adso.exceptions.user.UserNotFoundException;
import com.adso.exceptions.user.UserUnauthorizedForOperationException;

public interface UserDAO {
	Set<Card> getUserUnlockedCards(Long id);
	DeckCard updateDeck(DeckCard updateDeckCard, Long userId) throws NotValidPositionValue, UserUnauthorizedForOperationException, CardAlreadyInDeckException;
	User addNewUser(String username, String password) throws UserAlreadyExistsException;
	List<Object> getUserDecksCards(Long userId);
	User updateUserInfo(Pet pet, Long userId) throws UserNotFoundException, NotFoundPetException ;
}
