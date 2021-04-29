package api;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public interface Api {
	Optional<BigDecimal> getAmountInPLN(BigDecimal amount, CurrencyCode code);
	Optional<BigDecimal> getAmountInPLN(LocalDate date, BigDecimal amount,
			CurrencyCode code);
	void setStrategy(Strategy strategy);
}