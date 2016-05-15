package ch.bfh.bti7081.s2016.yellow.SwissMD;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.Person;

public class HibernateTest {

	public static void main(String[] args) {

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("manager");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		entityManager.getTransaction().begin();

		entityManager.persist(new Person("Jakab Gipsz", new Date()));
		entityManager.persist(new Person("Captain Nemo", new Date()));

		entityManager.getTransaction().commit();
		
		Query q = entityManager.createQuery("From Person ");

		List<Person> resultList = q.getResultList();
		System.out.println("num of persons:" + resultList.size());
		for (Person next : resultList) {
			System.out.println("next person: " + next);
		}

	}

}
