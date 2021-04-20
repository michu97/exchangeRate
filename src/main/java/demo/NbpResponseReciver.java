package demo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import demo.service.ContentNotFoundException;
import demo.service.HttpRequestService;

public class NbpResponseReciver implements ResponseReciver {
	private static final String API_URL = 
			"http://api.nbp.pl/api/exchangerates/rates/a/";
	private LocalDate date;
	private final CurrencyCode code;
	private final HttpRequestService service;
	private static final Logger LOGGER = 
			Logger.getLogger(NbpResponseReciver.class.getName());
	
	public NbpResponseReciver(LocalDate date, CurrencyCode code,
			HttpRequestService service) {
		this.date = date;
		this.code = code;
		this.service = service;
	}

	@Override
	public Optional<BigDecimal> getExchangeRate() {
		return Optional.ofNullable(deserializeJson());
	}
	
	private BigDecimal deserializeJson() {
		JSONObject response;
		Boolean isSecondCheck = false;
		try {
			response = service.getResponse(generateURL());
		} catch (ContentNotFoundException e) {
				if (isSecondCheck) {
					LOGGER.log(Level.WARNING, "Not found target exchange rate: ", e);
					return null;
				}
				response = service.getResponse(generateExcpetion());
				isSecondCheck = true;
		}
		if (response.isEmpty()) {
			return null;
		}
		try {
			JSONArray rates = response.getJSONArray("rates");
			JSONObject rate = rates.getJSONObject(rates.length() - 1);
			return rate.getBigDecimal("mid");
		} catch (JSONException e) {
			LOGGER.log(Level.WARNING, "Cannot parse response to Json: ", e);
			return null;
		}
	}
	
	private String generateURL() {
		if (date.isAfter(LocalDate.now())) {
			date = LocalDate.now();
			return API_URL + code.name() + "/" +
					LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
		}
		if (date.isBefore(LocalDate.of(2002, 1, 2))) {
			return API_URL + code.name() + "/" +
					LocalDate.of(2002, 1, 2).format(DateTimeFormatter.ISO_LOCAL_DATE);
		}
		return API_URL + code.name() + "/" + 
					date.format(DateTimeFormatter.ISO_LOCAL_DATE);
	}
	
	private String generateExcpetion() {
		return API_URL + code.name() + "/" + 
				date.minusDays(93).format(DateTimeFormatter.ISO_LOCAL_DATE) +
				"/" + date.format(DateTimeFormatter.ISO_LOCAL_DATE);
	}
}
