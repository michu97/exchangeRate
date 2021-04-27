package api;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import demo.CurrencyCode;

public interface Api {
	Optional<BigDecimal> getRateByCode(CurrencyCode code, BigDecimal amount);
	Optional<BigDecimal> getRateByCodeAndDate(CurrencyCode code, LocalDate date,
			BigDecimal amount);
}
