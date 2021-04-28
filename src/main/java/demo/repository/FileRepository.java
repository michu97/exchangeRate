package demo.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import demo.CurrencyCode;
import demo.service.HttpRequestSeriveImpl;
import demo.service.HttpRequestService;
import demo.tools.DataParser;
import demo.tools.UrlBuilder;

public class FileRepository implements ExhangeRateRepository {
	private final HttpRequestService fileService;
	private final DataParser parser;
	private final UrlBuilder pathToFileBuilder;
	
	@Override
	public Optional<BigDecimal> findRateByCodeAndDate(CurrencyCode code,
			LocalDate date) {
		String response = fileService.getResponse(pathToFileBuilder.getUrl(code, date));
		return parser.getRate(response);
	}

	@Override
	public Optional<BigDecimal> findRateByCode(CurrencyCode code) {
		return null;
	}
	
}
