package test.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import api.CurrencyCode;
import test.Rate;
import test.entity.Country;
import test.entity.ExchangeRateDb;

public interface ExchangeRateRepository {
	Optional<Rate> getRateByDateAndCode(LocalDate date, CurrencyCode code);
	void saveRate(Rate rate);
	void save(Country country);
	void saveNewRate(Rate rate);
	List<Country> getCountryWithAtLeastTwoCurrency();
	ExchangeRateDb getMaxRateInInterval(LocalDate start, LocalDate end, CurrencyCode code);
	ExchangeRateDb getMinRateInInterval(LocalDate start, LocalDate end, CurrencyCode code);
	List<ExchangeRateDb> getFiveBestCourses(CurrencyCode code);
	List<ExchangeRateDb> getFiveWorstCourses(CurrencyCode code);
	
}
