package api;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

class FileRepository implements ExchangeRateRepository {
	private final ExchangeRateDataParser parser;

	public FileRepository(ExchangeRateDataParser parser) {
		this.parser = parser;
	}

	@Override
	public Optional<BigDecimal> getRateByCode(CurrencyCode code) {
		return getRateByCodeAndDate(code, LocalDate.now());
	}

	@Override
	public Optional<BigDecimal> getRateByCodeAndDate(CurrencyCode code,
			LocalDate date) {
		return parser.getRate(null);
	}
}
