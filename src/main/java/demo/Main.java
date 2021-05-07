package demo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import test.SaleDocumentService;
import test.repository.DbRate;

public class Main {
	public static void main(String[] args) {
		SaleDocumentService saleDocumentService = new test.SaleDocumentService();
		saleDocumentService.insert();
		
		SessionFactory sessionFactory = new Configuration().configure()
			.buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		DbRate dbRate = new DbRate(LocalDate.now(), "USD", new BigDecimal("25"));
		DbRate dbRate2 = new DbRate(LocalDate.now().minusDays(5), "USD", new BigDecimal("25"));
		session.save(dbRate);
		session.save(dbRate2);
		session.getTransaction().commit();
		session.close();
		DbRate dbRate3 = DbRate(LocalDate.now(), "USD");
	}
	
	public static DbRate DbRate(LocalDate date, String code) {
		String hql = "FROM DbRate R WHERE R.date = :date AND R.currencyCode = :code";
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		EntityManager session = sessionFactory.openSession();
		javax.persistence.Query query = session.createQuery(hql);
		query.setParameter("date", date);
		query.setParameter("code", code);
		List<DbRate> list = query.getResultList();
		
		DbRate object = list.get(0);
		System.out.println(object);
		session.close();
		return object;
	}
}