package com.adso.dao.interfaces;

import com.adso.entities.User;

public interface UserDAO extends DAO<User, Long> {
	String getUserDecks(Long id);
}
