package ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


public interface EntityManagerProvider {
	public EntityManager createEntityManager();

}
