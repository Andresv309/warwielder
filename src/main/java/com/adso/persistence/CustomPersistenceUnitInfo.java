package com.adso.persistence;

import java.net.URL;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariDataSource;

import jakarta.persistence.SharedCacheMode;
import jakarta.persistence.ValidationMode;
import jakarta.persistence.spi.ClassTransformer;
import jakarta.persistence.spi.PersistenceUnitInfo;
import jakarta.persistence.spi.PersistenceUnitTransactionType;


public class CustomPersistenceUnitInfo implements PersistenceUnitInfo {

	private final String dbUser;
	private final String dbPassword;
	private final String dbName;
	
	public CustomPersistenceUnitInfo() {
		this.dbUser = "root";
		this.dbPassword = "";
		this.dbName = "tests";
	}
	
	@Override
	public String getPersistenceUnitName() {
		// TODO Auto-generated method stub
		return "my-persistence-unit";
	}

	@Override
	public String getPersistenceProviderClassName() {
		// TODO Auto-generated method stub
		return "org.hibernate.jpa.HibernatePersistenceProvider";
	}

	@Override
	public PersistenceUnitTransactionType getTransactionType() {
		// TODO Auto-generated method stub
		return PersistenceUnitTransactionType.RESOURCE_LOCAL;
	}

	@Override
	public DataSource getJtaDataSource() {
		// TODO Auto-generated method stub
		
		HikariDataSource datasource = new HikariDataSource();
		datasource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		datasource.setJdbcUrl("jdbc:mysql://localhost:3306/" + dbName);
		datasource.setUsername(dbUser);
		datasource.setPassword(dbPassword);
		
		return datasource;
	}

	@Override
	public DataSource getNonJtaDataSource() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getMappingFileNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<URL> getJarFileUrls() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public URL getPersistenceUnitRootUrl() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getManagedClassNames() {
		// TODO Auto-generated method stub
		return List.of("com.adso.entities.User");
	}

	@Override
	public boolean excludeUnlistedClasses() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public SharedCacheMode getSharedCacheMode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationMode getValidationMode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Properties getProperties() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPersistenceXMLSchemaVersion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClassLoader getClassLoader() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addTransformer(ClassTransformer transformer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ClassLoader getNewTempClassLoader() {
		// TODO Auto-generated method stub
		return null;
	}

}
