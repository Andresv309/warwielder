package com.adso.dao.interfaces;

import com.adso.entities.Deck;

public interface DeckDAO extends DAO<Deck, Long> {
	String getDecksForUser(Long id);
}
