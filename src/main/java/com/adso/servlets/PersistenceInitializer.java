package com.adso.servlets;

import com.adso.persistence.AppEntityManager;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;


@WebListener
public class PersistenceInitializer implements ServletContextListener {
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContextListener.super.contextInitialized(sce);
		
        // Initialize the EntityManagerFactory once during application startup
		AppEntityManager.getInstance().getEntityManagerFactory();
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		ServletContextListener.super.contextDestroyed(sce);
		
        // Close the EntityManagerFactory when the application is shutting down
		AppEntityManager.closeEntityManagerFactory();
	}
}
