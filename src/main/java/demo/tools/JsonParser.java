package demo.tools;

import java.math.BigDecimal;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonParser implements DataParser {
	@Override
	public Optional<BigDecimal> getRate(String content) {
		try {
		JSONObject response = new JSONObject(content);
		JSONArray rates = response.getJSONArray("rates");
		JSONObject rate = rates.getJSONObject(rates.length() - 1);
		return Optional.of(rate.getBigDecimal("mid"));
		} catch (JSONException e) {
			return Optional.empty();
		}
	}
}
