package test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import api.CurrencyCode;
import api.HttpRequest;

public class NbpApi extends Api {
	private final static String API_URL = 
			"http://api.nbp.pl/api/exchangerates/rates/a/";
	private final HttpRequest request;
	private final RateParser parser;
	
	public NbpApi(Api nextApi, RateParser parser) {
		super(nextApi);
		this.request = new HttpRequest();
		this.parser = parser;
	}
	
	public NbpApi(RateParser parser) {
		this(null, parser);
	}

	@Override
	public String getRawData(LocalDate date, CurrencyCode code) {
		String url = API_URL + code.name() + "/" +
				date.format(DateTimeFormatter.ISO_LOCAL_DATE);
		return request.getResponse(url);
	}

	@Override
	public Optional<Rate> parseData(String rawData) {
		return parser.parseToDomain(rawData);
	}
}
