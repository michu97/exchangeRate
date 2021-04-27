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
	public Optional<BigDecimal> getRateByCode(BigDecimal amount, 
			CurrencyCode code) {
		return getRateByCodeAndDate(LocalDate.now(), amount, code);
	}

	@Override
	public Optional<BigDecimal> getRateByCodeAndDate(LocalDate date, 
			BigDecimal amount, CurrencyCode code) {
		Optional<BigDecimal> rate = 
				provider.getRateByCodeAndDate(code, date);
		if (rate.isPresent()) {
			return Optional.of(rate.get().multiply(amount));
		}
		return rate;
	}
}
