package com.adso.dao.interfaces;

import com.adso.entities.User;

public interface UserDAO extends DAO<User, Long> {
	String getUserInfo(Long id);
	String getUserDecks(Long id);
	String getUserUnlockedCards(Long id);
	String updateDeck(String updateDeckCardJson);
	User addNewUser(String newUserJson);
	String getUserDecksCards(Long id);
}
