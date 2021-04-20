package demo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public interface ExchangeApi {
	Optional<BigDecimal> getAmountFromPLN(LocalDate date, BigDecimal amount,
								CurrencyCode code);
	Optional<BigDecimal> getAmountFromPLN(BigDecimal amount, CurrencyCode code);
}
