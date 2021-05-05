package api;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public class NbpStrategy implements Strategy {
	NbpApiConfig config = new NbpApiConfig();
	private final ExchangeRateRepository repository;
	
	
	public NbpStrategy() {
		repository = config.getRepository();
	}

	@Override
	public Optional<BigDecimal> getExchangeRate(CurrencyCode code) {
		return getExchangeRate(code, LocalDate.now());
	}

	@Override
	public Optional<BigDecimal> getExchangeRate(CurrencyCode code,
			LocalDate date) {
		return repository.getRateByCodeAndDate(code, date);
	}
	
}
