package test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import api.CurrencyCode;

public class Cache extends Api {
	private final Map<String, Rate> rates;
	
	public Cache(Api nextApi) {
		super(nextApi);
		this.rates = new HashMap<>();
	}
	
	@Override
	void save(Rate rate) {
		String key = getKey(rate.getDate(), rate.getCode());
		rates.put(key, rate);
	}
	
	@Override
	String getRawData(LocalDate date, CurrencyCode code) {
		Rate rate = rates.get(getKey(date, code));
		if (rate != null) {
			return rate.toString();
		}
		return "";
	}

	@Override
	Optional<Rate> parseData(String rawData) {
		try {
			Rate rate = new ObjectMapper().readValue(rawData, Rate.class);
			return Optional.of(rate);
		} catch (JsonProcessingException e) {
			return Optional.empty();
		}
	}
	
	private String getKey(LocalDate date, CurrencyCode code) {
		return date.format(DateTimeFormatter.ISO_LOCAL_DATE) + code.name();
	}
	
}
