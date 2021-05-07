package demo;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import junit.framework.TestCase;
import test.repository.DbRate;

public class TestDb extends TestCase {
	public void test() {
		SessionFactory sessionFactory = new Configuration().configure()
				.buildSessionFactory();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			
			DbRate dbRate = new DbRate(LocalDate.now(), "USD", new BigDecimal("25"));
			session.save(dbRate);
			session.getTransaction().commit();
			session.close();
	}
}
