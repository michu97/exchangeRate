package api;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public class AlgorithmClass {
	private static int DAYS_COUNT = 11;
	
	static Optional<BigDecimal> validate(Strategy strategy, CurrencyCode code, LocalDate date) {
		date = date.isBefore(LocalDate.now()) ? date : LocalDate.now();
		Optional<BigDecimal> rate = strategy.getExchangeRate(code, date);
		if (rate.isPresent()) {
			return rate;
		}
		for (int i = 1; i < DAYS_COUNT; i++) {
			rate = strategy.getExchangeRate(code, date.minusDays(i));
			if (rate.isPresent())
				break;
		}
		return rate;
	}
}
