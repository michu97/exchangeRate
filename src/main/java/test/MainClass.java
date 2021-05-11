package test;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.hibernate.cfg.Configuration;

import api.CurrencyCode;
import test.repository.ExchangeRateRepository;
import test.repository.ExchangeRateRepositoryDb;

public class MainClass {
	public static void main(String[] args) {
		SaleDocumentService saleDocumentService = new SaleDocumentService();
		saleDocumentService.insert();
		
		
		ExchangeRateRepository repository = new ExchangeRateRepositoryDb(new Configuration()
				.configure()
				.buildSessionFactory());
		
//		Country country = new Country("UK");
//		CountryCode code1 = new CountryCode("USD");
//		CountryCode code2 = new CountryCode("EUR");
//		country.addCode(code1);
//		country.addCode(code2);
//		repository.save(country);
		
		Rate rate = new Rate(new BigDecimal("4.2345"), LocalDate.now(), CurrencyCode.EUR);
		repository.saveNewRate(rate);
	}
}