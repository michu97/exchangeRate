package api;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public class ExchangeRateApi implements Api {
	private final Strategy strategy;
	
	public ExchangeRateApi(Strategy strategy) { 
		this.strategy = strategy;
	}

	@Override
	public Optional<BigDecimal> getAmountInPLN(BigDecimal amount, 
			CurrencyCode code) {
		return getAmountInPLN(LocalDate.now(), amount, code);
	}

	@Override
	public Optional<BigDecimal> getAmountInPLN(LocalDate date, 
			BigDecimal amount, CurrencyCode code) {
		Optional<BigDecimal> rate = AlgorithmClass.validate(strategy, code, DateValidator.validate(date));
		if (rate.isPresent()) {
			return Optional.of(rate.get().multiply(amount));
		}
		return rate;
	}
	
}