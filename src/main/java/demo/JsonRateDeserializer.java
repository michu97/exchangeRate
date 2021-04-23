package demo;

import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonRateDeserializer {
	private static final Logger LOGGER = Logger.getLogger(JsonRateDeserializer.class.getName());
	
	public BigDecimal getRate(JSONObject response) {
		try {
			JSONArray rates = response.getJSONArray("rates");
			JSONObject rate = rates.getJSONObject(rates.length() - 1);
			return rate.getBigDecimal("mid");
		} catch (JSONException e) {
			LOGGER.log(Level.WARNING, "Cannot parse response to Json: ", e);
			return null;
		}
	}
}
