package test;

import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import test.nbp.NbpTable;

public class NbpXmlParser implements RateParser {

	@Override
	public Optional<Rate> parseToDomain(String rawData) {
		XmlMapper mapper = new XmlMapper();
		mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
		try {
			NbpTable table = mapper.readValue(rawData, NbpTable.class);
			Rate rate = table.toModel();
			return Optional.of(rate);
		} catch (JsonProcessingException e) {
			return Optional.empty();
		}
	}
	
}
