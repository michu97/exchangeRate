package api;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import demo.CurrencyCode;

class NbpRepository implements ExchangeRateRepository {
	private final ExchangeDataProvider provider;
	private final ExchangeDataParser parser;
	private final PathToSourceBuilder pathBuilder;
	
	public NbpRepository(ExchangeDataProvider provider,
			ExchangeDataParser parser, PathToSourceBuilder pathBuilder) {
		this.provider = provider;
		this.parser = parser;
		this.pathBuilder = pathBuilder;
	}

	@Override
	public Optional<BigDecimal> getRateByCode(CurrencyCode code) {
		return getRateByCodeAndDate(code, LocalDate.now());
	}

	@Override
	public Optional<BigDecimal> getRateByCodeAndDate(CurrencyCode code,
			LocalDate date) {
		String rate = provider.getExchangeRate(pathBuilder.getPath(code, date));
		return parser.getRate(rate);
	}
}
