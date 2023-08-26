package com.adso.dao.interfaces;

import java.util.List;
import java.util.Set;

import com.adso.entities.Card;
import com.adso.entities.DeckCard;
import com.adso.entities.Pet;
import com.adso.entities.User;
import com.adso.exceptions.app.NotFoundException;
import com.adso.exceptions.auth.NotAuthorizedException;
import com.adso.exceptions.decks.InvalidDeckException;
import com.adso.exceptions.user.UserAlreadyExistsException;

public interface UserDAO {
	Set<Card> getUserUnlockedCards(Long id);
	DeckCard updateDeck(DeckCard updateDeckCard, Long userId) throws NotAuthorizedException, InvalidDeckException;
	User addNewUser(String username, String password) throws UserAlreadyExistsException;
	List<Object> getUserDecksCards(Long userId);
	User updateUserInfo(Pet pet, Long userId) throws NotAuthorizedException, NotFoundException;
}
