package com.adso.dao;

import com.adso.dao.interfaces.DAOManager;
import com.adso.dao.interfaces.DeckDAO;
import com.adso.dao.interfaces.UserDAO;
import com.adso.persistence.AppEntityManager;

import jakarta.persistence.EntityManagerFactory;

public class DAOManagerImp implements DAOManager {

	private EntityManagerFactory emf;
	private DeckDAO deckDao = null;
	private UserDAO userDao = null;
	
    public DAOManagerImp () {
    	this.emf = AppEntityManager.getInstance().getEntityManagerFactory();
    }
	
	@Override
	public DeckDAO getDeckDAO() {
        if (deckDao == null) {
        	deckDao = new DeckDAOImp(emf);
        }
        return deckDao;
	}

	@Override
	public UserDAO getUserDAO() {
        if (userDao == null) {
        	userDao = new UserDAOImp(emf);
        }
        return userDao;
	}

}
