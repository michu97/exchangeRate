package demo.tools;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import demo.CurrencyCode;

public class NbpUrlBuilder implements UrlBuilder {
	private static final String API_URL = "http://api.nbp.pl/api/exchangerates/rates/a/";
	private final BodyFormat format;
	
	public NbpUrlBuilder(BodyFormat format) {
		this.format = format;
	}

	@Override
	public String getUrl(CurrencyCode code, LocalDate date) {
		return API_URL + code.name() + "/" + 
				date.format(DateTimeFormatter.ISO_LOCAL_DATE) +
				"/?format=" + format.name();
	}

}
