package api;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public class NbpApi implements Api {
	private final NbpApiConfig config = new NbpApiConfig();
	private final ExchangeRateProvider provider;
	
	public NbpApi() { 
		provider = config.getExchangeProvider();
	}

	@Override
	public Optional<BigDecimal> getAmountInPLN(BigDecimal amount, 
			CurrencyCode code) {
		return getAmountInPLN(LocalDate.now(), amount, code);
	}

	@Override
	public Optional<BigDecimal> getAmountInPLN(LocalDate date, 
			BigDecimal amount, CurrencyCode code) {
		Optional<BigDecimal> rate = 
				provider.getRateByCodeAndDate(code, date);
		if (rate.isPresent()) {
			return Optional.of(rate.get().multiply(amount));
		}
		return rate;
	}
}
