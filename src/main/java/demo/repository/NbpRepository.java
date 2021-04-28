package demo.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import demo.CurrencyCode;
import demo.service.HttpRequestService;
import demo.tools.DataParser;
import demo.tools.UrlBuilder;

public class NbpRepository implements ExhangeRateRepository {
	private final HttpRequestService httpService;
	private final DataParser parser;
	private final UrlBuilder builder;
	
	public NbpRepository(HttpRequestService httpService, DataParser parser,
			UrlBuilder builder) {
		this.httpService = httpService;
		this.parser = parser;
		this.builder = builder;
	}

	@Override
	public Optional<BigDecimal> findRateByCodeAndDate(CurrencyCode code,
			LocalDate date) {
		String response = httpService.getResponse(builder.getUrl(code, date));
		return parser.getRate(response);
	}

	@Override
	public Optional<BigDecimal> findRateByCode(CurrencyCode code) {
		return findRateByCodeAndDate(code, LocalDate.now());
	}

}
