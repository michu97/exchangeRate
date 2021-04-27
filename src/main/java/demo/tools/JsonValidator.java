package demo.tools;

import java.math.BigDecimal;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonValidator implements Validator {

	@Override
	public Optional<BigDecimal> getRate(String content) {
		try {
		JSONObject jsonObject = new JSONObject(content);
		JSONArray jsonArray = jsonObject.getJSONArray("rates");
		JSONObject rate = jsonArray.getJSONObject(jsonArray.length() - 1);
		return Optional.ofNullable(rate.getBigDecimal("mid"));
		} catch (JSONException e) {
			return Optional.empty();
		}
	}
}
