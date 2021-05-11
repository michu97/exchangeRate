package test.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.persistence.TypedQuery;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import api.CurrencyCode;
import test.Rate;
import test.entity.Country;
import test.entity.CurrencyCodeDb;
import test.entity.ExchangeRateDb;

public class ExchangeRateRepositoryDb implements ExchangeRateRepository {

	private final SessionFactory sessionFactory;

	public ExchangeRateRepositoryDb(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public Optional<Rate> getRateByDateAndCode(LocalDate date, CurrencyCode code) {
		String hql = "FROM DbRate R WHERE R.date = :date AND R.currencyCode = :code";
		Transaction tx = null;
		Session session = sessionFactory.openSession();
		try {
			tx = session.beginTransaction();
			TypedQuery<DbRate> query = session.createQuery(hql, DbRate.class);
			query.setParameter("date", date);
			query.setParameter("code", code.name());
			Optional<DbRate> rate = query.getResultList().stream().findFirst();
			tx.commit();
			return rate.map(DbRate::toDomain);
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			return Optional.empty();
		} finally {
			session.close();
		}
	}

	@Override
	public void saveRate(Rate rate) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(DbRate.fromDomain(rate));
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			session.close();
		}
	}

	@Override
	public void save(Country country) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(country);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			session.close();
		}
	}

	@Override
	public void saveNewRate(Rate rate) {
		Session session = sessionFactory.openSession();
		String hqlCode = "FROM CurrencyCodeDb c WHERE c.code = :code";
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query<CurrencyCodeDb> query = session.createQuery(hqlCode);
			query.setParameter("code", rate.getCode().name());
			CurrencyCodeDb code = query.getSingleResult();
			
			ExchangeRateDb result = new ExchangeRateDb(rate.getDate(), rate.getValue(), code);
			session.save(result);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			session.close();
		}
	}

}
