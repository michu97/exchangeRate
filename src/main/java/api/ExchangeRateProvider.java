package api;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public class ExchangeRateProvider {
	private final Strategy strategy;
	
	public ExchangeRateProvider(Strategy strategy) {
		this.strategy = strategy;
	}
	
	public Optional<BigDecimal> getRate(CurrencyCode code, LocalDate date) {
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
