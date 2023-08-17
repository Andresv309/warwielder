package com.adso.dao;

import com.adso.dao.interfaces.PetDAO;
import com.adso.dao.template.AbstractDAOTemplate;
import com.adso.entities.Pet;

import jakarta.persistence.EntityManagerFactory;


public class PetDAOImp extends AbstractDAOTemplate<Pet, Long> implements PetDAO {
	
    public PetDAOImp(EntityManagerFactory emf) {
    	super(emf);
    }
    
    
    
    
}
