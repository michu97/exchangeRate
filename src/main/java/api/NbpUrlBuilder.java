package api;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import demo.CurrencyCode;

class NbpUrlBuilder implements PathToSourceBuilder {
	private static final String API_URL = 
			"http://api.nbp.pl/api/exchangerates/rates/a/";
	
	@Override
	public String getPath(CurrencyCode code, LocalDate date) {
		return API_URL + code.name() + "/" + 
				date.format(DateTimeFormatter.ISO_LOCAL_DATE);
	}
}
