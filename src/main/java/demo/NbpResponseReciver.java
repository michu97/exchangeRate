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
		JSONObject response = null;
		try {
			response = service.getResponse(generateURL());
		} catch (ContentNotFoundException e) {
			for (int i = 0; i < 10; i++) {
				response = seckondCheck();
				if (!response.isEmpty())
					break;
			}
			if (response.isEmpty()) {
				LOGGER.log(Level.WARNING, "Data not found: ", e);
				return null;
			}
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
	
	private JSONObject seckondCheck() {
		try {
			return service.getResponse(generateExcpetion());
		} catch (ContentNotFoundException e) {
			return new JSONObject();
		}
	}

	private String generateURL() {
		if (date.isAfter(LocalDate.now())) {
			date = LocalDate.now();
			return API_URL + code.name() + "/" +
					LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
		}

		return API_URL + code.name() + "/" + 
					date.format(DateTimeFormatter.ISO_LOCAL_DATE);
	}
	
	private String generateExcpetion() {
		date = date.minusDays(1);
		return API_URL + code.name() + "/" + 
				date.format(DateTimeFormatter.ISO_LOCAL_DATE);
	}
}
