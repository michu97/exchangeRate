package demo.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import demo.CurrencyCode;

public interface ExhangeRateRepository {
	Optional<BigDecimal> findRateByCodeAndDate(CurrencyCode code, LocalDate date);
	Optional<BigDecimal> findRateByCode(CurrencyCode code);
}
