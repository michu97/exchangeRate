package test.repository;

import java.time.LocalDate;
import java.util.ArrayList;
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

	@Override
	public List<Country> getCountryWithAtLeastTwoCurrency() {
		Session session = sessionFactory.openSession();
		String hqlCode = "SELECT c FROM Country c LEFT JOIN c.codes cc GROUP BY c.id HAVING COUNT(c) > 1";
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query<Country> query = session.createQuery(hqlCode);
			List<Country> list = query.getResultList();
			tx.commit();
			return list;
		} catch (Exception e) {
			if ( tx != null) {
				tx.rollback();
			}
			return new ArrayList<Country>();
		} finally {
			session.close();
		}
	}

	@Override
	public ExchangeRateDb getMaxRateInInterval(LocalDate start, LocalDate end,
			CurrencyCode code) {
		Session session = sessionFactory.openSession();
		String hql = "SELECT r FROM ExchangeRateDb r JOIN r.code c WHERE c.code = :code AND r.rate = (SELECT MAX(r.rate) FROM ExchangeRateDb r WHERE r.date > :start AND r.date < :end)";
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query<ExchangeRateDb> query = session.createQuery(hql);
			query.setParameter("code", code.name());
			query.setParameter("start", start);
			query.setParameter("end", end);
			List<ExchangeRateDb> resultList = query.getResultList();
			ExchangeRateDb result = resultList.get(0);
			tx.commit();
			return result;
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			return null;
		} finally {
			session.close();
		}
	}

	@Override
	public ExchangeRateDb getMinRateInInterval(LocalDate start, LocalDate end,
			CurrencyCode code) {
		Session session = sessionFactory.openSession();
		String hql = "SELECT r FROM ExchangeRateDb r JOIN r.code c WHERE c.code = :code AND r.rate = (SELECT MIN(r.rate) FROM ExchangeRateDb r WHERE r.date > :start AND r.date < :end)";
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query<ExchangeRateDb> query = session.createQuery(hql);
			query.setParameter("code", code.name());
			query.setParameter("start", start);
			query.setParameter("end", end);
			List<ExchangeRateDb> resultList = query.getResultList();
			ExchangeRateDb result = resultList.get(0);
			tx.commit();
			return result;
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			return null;
		} finally {
			session.close();
		}
	}

	@Override
	public List<ExchangeRateDb> getFiveBestCourses(CurrencyCode code) {
		Session session = sessionFactory.openSession();
		String hql = "SELECT r FROM ExchangeRateDb r JOIN r.code c WHERE c.code = :code ORDER BY r.rate";
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query<ExchangeRateDb> query = session.createQuery(hql);
			query.setParameter("code", code.name());
			query.setFirstResult(0);
			query.setMaxResults(5);
			List<ExchangeRateDb> resultList = query.getResultList();
			tx.commit();
			return resultList;
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			return null;
		} finally {
			session.close();
		}
	}

	@Override
	public List<ExchangeRateDb> getFiveWorstCourses(CurrencyCode code) {
		Session session = sessionFactory.openSession();
		String hql = "SELECT r FROM ExchangeRateDb r JOIN r.code c WHERE c.code = :code ORDER BY r.rate DESC";
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query<ExchangeRateDb> query = session.createQuery(hql);
			query.setParameter("code", code.name());
			query.setFirstResult(0);
			query.setMaxResults(5);
			List<ExchangeRateDb> resultList = query.getResultList();
			tx.commit();
			return resultList;
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			return null;
		} finally {
			session.close();
		}
	}

}
