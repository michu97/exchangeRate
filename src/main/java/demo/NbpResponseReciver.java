package demo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONObject;

import demo.service.ContentNotFoundException;
import demo.service.HttpRequestService;

public class NbpResponseReciver implements ResponseReciver {
	private static final String API_URL = 
			"http://api.nbp.pl/api/exchangerates/rates/a/";
	private static final Logger LOGGER = 
			Logger.getLogger(NbpResponseReciver.class.getName());
	private final CurrencyCode code;
	private final HttpRequestService service;
	private final JsonRateDeserializer deserializer;
	private LocalDate date;
	
	public NbpResponseReciver(LocalDate date, CurrencyCode code,
			HttpRequestService service) {
		this.date = date;
		this.code = code;
		this.service = service;
		this.deserializer = new JsonRateDeserializer();
	}
	

	@Override
	public Optional<BigDecimal> getExchangeRate() {
		return Optional.ofNullable(getRateValue());
	}
	
	private BigDecimal getRateValue() {
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
		
		return deserializer.getRate(response);
	}
	
	private JSONObject seckondCheck() {
		try {
			return service.getResponse(generateExcpetionURL());
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
	
	private String generateExcpetionURL() {
		date = date.minusDays(1);
		return API_URL + code.name() + "/" + 
				date.format(DateTimeFormatter.ISO_LOCAL_DATE);
	}
}
