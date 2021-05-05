package test;

import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import test.nbp.NbpTable;

public class NbpParser implements RateParser {

	@Override
	public Optional<Rate> parseToDomain(String rawData) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			NbpTable table = objectMapper.readValue(rawData, NbpTable.class);
			Rate model = table.toModel();
			return Optional.of(model);
		} catch (JsonProcessingException e) {
			return Optional.empty();
		}
	}
}
