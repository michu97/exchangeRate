package api;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import demo.CurrencyCode;

public interface Api {
	Optional<BigDecimal> getRateByCode(BigDecimal amount, CurrencyCode code);
	Optional<BigDecimal> getRateByCodeAndDate(LocalDate date, BigDecimal amount,
			CurrencyCode code);
}
