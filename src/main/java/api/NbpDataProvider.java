package api;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import demo.CurrencyCode;

class NbpDataProvider implements ExchangeDataProvider {
	private final static String API_URL = 
			"http://api.nbp.pl/api/exchangerates/rates/a/";
	private final HttpRequest request = new HttpRequest();

	@Override
	public String getExchangeRate(CurrencyCode code, LocalDate date) {
		return request.getResponse(generateURL(code, date));
	}
	
	private String generateURL(CurrencyCode code, LocalDate date) {
		return API_URL + code.name() + "/" + 
				date.format(DateTimeFormatter.ISO_LOCAL_DATE);
	}
}
