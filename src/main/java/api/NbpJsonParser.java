package api;

import java.math.BigDecimal;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class NbpJsonParser implements ExchangeDataParser {
	
	@Override
	public Optional<BigDecimal> getRate(String rateData) {
		try {
			JSONObject table = new JSONObject(rateData);
			JSONArray rates = table.getJSONArray("rates");
			JSONObject rate = rates.getJSONObject(rates.length() - 1);
			BigDecimal mid = rate.getBigDecimal("mid");
			return Optional.of(mid);
		} catch (JSONException e) {
			return Optional.empty();
		}
	}
}
