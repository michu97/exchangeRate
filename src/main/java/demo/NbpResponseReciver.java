package demo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.json.JSONArray;
import org.json.JSONObject;

import demo.service.HttpRequestService;

public class NbpResponseReciver implements ResponseReciver {
	private static final String API_URL = "http://api.nbp.pl/api/exchangerates/rates/a/";
	private final LocalDate date;
	private final CurrencyCode code;
	private final HttpRequestService service;
	
	public NbpResponseReciver(LocalDate date, CurrencyCode code,
			HttpRequestService service) {
		this.date = date;
		this.code = code;
		this.service = service;
	}

	@Override
	public BigDecimal getExchangeRate() {
		return deserializeJson();
	}
	
	private BigDecimal deserializeJson() {
		JSONObject response = service.getResponse(generateURL());
		JSONArray rates = response.getJSONArray("rates");
		JSONObject rate = rates.getJSONObject(0);
		return rate.getBigDecimal("mid");
	}
	
	private String generateURL() {
		if (date.isAfter(LocalDate.now())) {
			return API_URL + code.name() + "/" + LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
		}
		if (date.isBefore(LocalDate.of(2002, 1, 2))) {
			return API_URL + code.name() + "/" + LocalDate.of(2002, 1, 2).format(DateTimeFormatter.ISO_LOCAL_DATE);
		}
		System.out.println(API_URL + code.name() + "/" + date.format(DateTimeFormatter.ISO_LOCAL_DATE));
		return API_URL + code.name() + "/" + date.format(DateTimeFormatter.ISO_LOCAL_DATE);
	}
}
