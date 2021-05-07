package test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.hibernate.cfg.Configuration;

import api.CurrencyCode;
import test.repository.ExchangeRateRepository;
import test.repository.ExchangeRateRepositoryDb;

public class SaleDocumentService {
	public void insert() {
		Api nbp = new NbpApi(new NbpJsonParser());
		Api file = new FileApi(nbp);
		Api xmlApi = new XmlApi(file);
		ExchangeRateRepository repositoryDb = new ExchangeRateRepositoryDb(
				new Configuration()
				.configure()
				.buildSessionFactory()
				.openSession());
		Api sqlApi = new SqlApi(xmlApi, repositoryDb);
		Api cacheApi = new Cache(sqlApi);
		CurrencyExchanger currencyExchanger = new CurrencyExchanger(cacheApi);

		Optional<BigDecimal> pln = currencyExchanger.getAmountInPLN(
				LocalDate.of(2021, 4, 9),
				CurrencyCode.USD,
				new BigDecimal("150"));
		pln.ifPresent(System.out::println);

		Optional<BigDecimal> pln2 = currencyExchanger.getAmountInPLN(
				LocalDate.now(),
				CurrencyCode.USD,
				new BigDecimal("120"));
		pln2.ifPresent(System.out::println);
	}
}
