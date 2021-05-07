package test.repository;

import java.time.LocalDate;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import api.CurrencyCode;
import test.Rate;

public class ExchangeRateRepositoryDb implements ExchangeRateRepository {

	private final EntityManager em;

	public ExchangeRateRepositoryDb(EntityManager em) {
		this.em = em;
	}

	@Override
	public Optional<Rate> getRateByDateAndCode(LocalDate date, CurrencyCode code) {
		String hql = "FROM DbRate R WHERE R.date = :date AND R.currencyCode = :code";
		TypedQuery<DbRate> query = em.createQuery(hql, DbRate.class);
		query.setParameter("date", date);
		query.setParameter("code", code.name());
		Optional<DbRate> rate = query.getResultList().stream().findFirst();
		return rate.map(DbRate::toDomain);
	}

	@Override
	public void saveRate(Rate rate) {
		em.getTransaction().begin();
		em.persist(DbRate.fromDomain(rate));
		em.getTransaction().commit();
	}

}
