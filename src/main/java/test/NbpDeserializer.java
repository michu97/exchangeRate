package test;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import api.CurrencyCode;

public class NbpDeserializer extends JsonDeserializer<Rate>{

	@Override
	public Rate deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		ObjectCodec oc = p.getCodec();
		JsonNode node = oc.readTree(p);
		
		final CurrencyCode code = CurrencyCode.valueOf(node.get("code").asText());
		final LocalDate date = LocalDate.parse(node.get("rates").get(0).get("effectiveDate").asText());
		final BigDecimal mid = new BigDecimal(node.get("rates").get(0).get("mid").asText());
		
		Rate rate = new Rate(mid, date, code);
		return rate;
	}

}
