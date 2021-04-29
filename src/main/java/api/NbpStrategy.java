package api;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public class NbpStrategy implements Strategy {
	NbpApiConfig config = new NbpApiConfig();
	private final ExchangeRateProvider provider;
	
	
	public NbpStrategy() {
		provider = config.getExchangeProvider();
	}

	@Override
	public Optional<BigDecimal> getExchangeRate(CurrencyCode code) {
		return getExchangeRate(code, LocalDate.now());
	}

	@Override
	public Optional<BigDecimal> getExchangeRate(CurrencyCode code,
			LocalDate date) {
		return provider.getRateByCodeAndDate(code, date);
	}
	
}
