package api;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

class NbpRepository implements ExchangeRateRepository {
	private final ExternalExchangeRateProvider provider;
	private final ExchangeRateDataParser parser;
	
	public NbpRepository(ExternalExchangeRateProvider provider,
			ExchangeRateDataParser parser) {
		this.provider = provider;
		this.parser = parser;
	}

	@Override
	public Optional<BigDecimal> getRateByCode(CurrencyCode code) {
		return getRateByCodeAndDate(code, LocalDate.now());
	}

	@Override
	public Optional<BigDecimal> getRateByCodeAndDate(CurrencyCode code,
			LocalDate date) {
		String rate = provider.getExchangeRate(code, date);
		return parser.getRate(rate);
	}
}
