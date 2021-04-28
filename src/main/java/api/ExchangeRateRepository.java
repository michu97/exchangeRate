package api;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

interface ExchangeRateRepository {
	Optional<BigDecimal> getRateByCode(CurrencyCode code);
	Optional<BigDecimal> getRateByCodeAndDate(CurrencyCode code,
			LocalDate date);
}
