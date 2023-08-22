package com.adso.dao;

import com.adso.dao.interfaces.AppDAO;
import com.adso.dao.interfaces.DAOManager;
import com.adso.dao.interfaces.UserDAO;
import com.adso.persistence.AppEntityManager;

import jakarta.persistence.EntityManagerFactory;

public class DAOManagerImp implements DAOManager {
	
	private EntityManagerFactory emf;
	private UserDAO userDao = null;
	private AppDAO appDao = null;
	
    public DAOManagerImp () {
    	this.emf = AppEntityManager.getInstance().getEntityManagerFactory();
    }

	@Override
	public UserDAO getUserDAO() {
        if (userDao == null) {
        	userDao = new UserDAOImp(emf);
        }
        return userDao;
	}

	@Override
	public AppDAO getAppDAO() {
        if (appDao == null) {
        	appDao = new AppDAOImp(emf);
        }
        return appDao;
	}

}

