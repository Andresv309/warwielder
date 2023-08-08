package com.adso.dao.interfaces;

import java.util.List;

import com.adso.entities.Deck;

public interface DeckDAO extends DAO<Deck, Long> {
	String getDecksForUser(Long id);
}
