package test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.hibernate.cfg.Configuration;

import api.CurrencyCode;
import test.entity.Country;
import test.entity.ExchangeRateDb;
import test.repository.ExchangeRateRepository;
import test.repository.ExchangeRateRepositoryDb;

public class MainClass {
	public static void main(String[] args) {
//		SaleDocumentService saleDocumentService = new SaleDocumentService();
//		saleDocumentService.insert();
		
		
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
		List<Country> list = repository.getCountryWithAtLeastTwoCurrency();
		list.forEach(x -> System.out.println(x.getName()));
		ExchangeRateDb maxRateInInterval = repository.getMinRateInInterval(LocalDate.of(2021, 5, 1), LocalDate.now(), CurrencyCode.USD);
		System.out.println(maxRateInInterval.getRate() + " " + maxRateInInterval.getDate());
		List<ExchangeRateDb> bestCourses = repository.getFiveBestCourses(CurrencyCode.USD);
		bestCourses.forEach(x -> System.out.println(x.getDate() + " " + x.getRate() + " " + x.getCode().getCode()));
		System.out.println();
		List<ExchangeRateDb> worstCourses = repository.getFiveWorstCourses(CurrencyCode.USD);
		worstCourses.forEach(x -> System.out.println(x.getDate() + " " + x.getRate() + " " + x.getCode().getCode()));
	}
}