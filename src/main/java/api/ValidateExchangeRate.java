package api;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public class ValidateExchangeRate {
	static Optional<BigDecimal> validate(Strategy strategy, CurrencyCode code, LocalDate date) {
		date = date.isBefore(LocalDate.now()) ? date : LocalDate.now();
		Optional<BigDecimal> rate = strategy.getExchangeRate(code, date);
		if (rate.isPresent()) {
			return rate;
		}
		for (int i = 1; i < 11; i++) {
			rate = strategy.getExchangeRate(code, date.minusDays(i));
			if (rate.isPresent())
				break;
		}
		return rate;
	}
}
