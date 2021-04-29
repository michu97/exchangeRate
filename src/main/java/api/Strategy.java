package api;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public interface Strategy {
	Optional<BigDecimal> getExchangeRate(CurrencyCode code);
	Optional<BigDecimal> getExchangeRate(CurrencyCode code, LocalDate date);
}
