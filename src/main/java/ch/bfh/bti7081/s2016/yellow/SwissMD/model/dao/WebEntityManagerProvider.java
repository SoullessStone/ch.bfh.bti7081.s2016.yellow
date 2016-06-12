package ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Implementation of the {@code EntityManagerProvider}.
 * 
 * For the use in the web application.
 * 
 * @author K.Suter
 * 
 */
@WebListener
public class WebEntityManagerProvider implements ServletContextListener,
		EntityManagerProvider {

	private static EntityManagerFactory emf;

	@Override
	public void contextInitialized(ServletContextEvent event) {
		emf = Persistence.createEntityManagerFactory("manager");
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		emf.close();
	}

	public EntityManager createEntityManager() {
		if (emf == null) {
			throw new IllegalStateException("Context is not initialized yet.");
		}

		return emf.createEntityManager();
	}

}
