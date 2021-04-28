package api;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import demo.CurrencyCode;

public class FileRepository implements ExchangeRateRepository {
	private final ExchangeDataParser parser;

	public FileRepository(ExchangeDataParser parser) {
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
