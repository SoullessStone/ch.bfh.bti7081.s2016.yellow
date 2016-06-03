package ch.bfh.bti7081.s2016.yellow.SwissMD.model;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao.IllnessDao;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao.IllnessDaoImpl;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao.TestEntityManagerProvider;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Illness;

public class IllnessDaoImplTest {
	
	private static IllnessDao illnessDao;

	@BeforeClass
	public static void setUp() {
    	illnessDao = new IllnessDaoImpl(new TestEntityManagerProvider());
		illnessDao.create(new Illness("F20.1", "Hebephrene Schizophrenie"));
    }
	
	@Test
	public void findByCodeExact() {
		List<Illness> found = illnessDao.findByCodeOrName("F20.1");
		Illness illness = found.get(0);
		assertNotNull(illness);
	}
	
	@Test
	public void findByCodePart() {
		List<Illness> found = illnessDao.findByCodeOrName("20.1");
		Illness illness = found.get(0);
		assertNotNull(illness);
	}
	
	@Test
	public void findByNameExact() {
		List<Illness> found = illnessDao.findByCodeOrName("Hebephrene Schizophrenie");
		Illness illness = found.get(0);
		assertNotNull(illness);
	}
	
	@Test
	public void findByNamePart() {
		List<Illness> found = illnessDao.findByCodeOrName("Schizophrenie");
		Illness illness = found.get(0);
		assertNotNull(illness);
	}
}
