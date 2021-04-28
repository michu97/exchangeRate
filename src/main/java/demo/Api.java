package demo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import demo.repository.ExhangeRateRepository;
import demo.repository.NbpRepository;
import demo.service.HttpRequestSeriveImpl;
import demo.service.HttpRequestService;
import demo.tools.BodyFormat;
import demo.tools.DataParser;
import demo.tools.JsonParser;
import demo.tools.NbpUrlBuilder;
import demo.tools.UrlBuilder;
import demo.tools.XmlParser;
import demo.usecase.FindRate;

public class Api implements ExchangeApi {
	private final FindRate findRate;
	private final ExhangeRateRepository repository;
	private DataParser parser;
	private UrlBuilder urlBuilder;
	private HttpRequestService service; 
	
	public Api() {
		setup();
		this.repository = new NbpRepository(service, parser, urlBuilder);
		this.findRate = new FindRate(repository);
	}

	@Override
	public Optional<BigDecimal> getAmountFromPLN(LocalDate date,
			BigDecimal amount, CurrencyCode code) {
		Optional<BigDecimal> rate = findRate.getRateByCodeAndDate(code, date);
		if (rate.isPresent()) {
			return Optional.of(rate.get().multiply(amount));
		}
		return Optional.empty();
	}

	@Override
	public Optional<BigDecimal> getAmountFromPLN(BigDecimal amount,
			CurrencyCode code) {
		return getAmountFromPLN(LocalDate.now(), amount, code);
	}
	
	private void setup() {
		parser = new JsonParser();
		urlBuilder = new NbpUrlBuilder(BodyFormat.JSON);
		service = new HttpRequestSeriveImpl(); 
	}
}
