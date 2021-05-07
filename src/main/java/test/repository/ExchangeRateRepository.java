package test.repository;

import java.time.LocalDate;
import java.util.Optional;

import api.CurrencyCode;
import test.Rate;

public interface ExchangeRateRepository {
	Optional<Rate> getRateByDateAndCode(LocalDate date, CurrencyCode code);
	void saveRate(Rate rate);
}
