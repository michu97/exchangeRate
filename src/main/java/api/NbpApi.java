package api;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import demo.CurrencyCode;

public class NbpApi implements Api{
	private final NbpConfig config = new NbpConfig();
	private final ExchangeRateProvider provider;
	
	public NbpApi() { 
		provider = config.getExchangeProvider();
	}

	@Override
	public Optional<BigDecimal> getRateByCode(CurrencyCode code,
			BigDecimal amount) {
		return getRateByCodeAndDate(code, LocalDate.now(), amount);
	}

	@Override
	public Optional<BigDecimal> getRateByCodeAndDate(CurrencyCode code,
			LocalDate date, BigDecimal amount) {
		Optional<BigDecimal> rate = 
				provider.getRateByCodeAndDate(code, date);
		if (rate.isPresent()) {
			return Optional.of(rate.get().multiply(amount));
		}
		return rate;
	}
}
