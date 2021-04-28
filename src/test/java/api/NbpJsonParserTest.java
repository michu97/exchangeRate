package api;
import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

public class NbpJsonParserTest {
	private ExchangeRateDataParser jsonParser;
	
	@Before
	public void setup() {
		jsonParser = new NbpJsonParser();
	}

	@Test
	public void testGetRate() throws Exception {
		ExchangeRateDataParser testSubject = jsonParser;
		Optional<BigDecimal> result;
		String rateData = "{}";
		result = testSubject.getRate(rateData);
		assertThat(result).isEqualTo(Optional.empty());
	}
	
	@Test
	public void shouldReturnRateFromJson() {
		ExchangeRateDataParser testSubject;
		String rateData2 = "{\r\n"
				+ "  \"table\": \"A\",\r\n"
				+ "  \"currency\": \"dolar amerykañski\",\r\n"
				+ "  \"code\": \"USD\",\r\n"
				+ "  \"rates\": [\r\n"
				+ "    {\r\n"
				+ "      \"no\": \"080/A/NBP/2021\",\r\n"
				+ "      \"effectiveDate\": \"2021-04-27\",\r\n"
				+ "      \"mid\": 3.7826\r\n"
				+ "    }\r\n"
				+ "  ]\r\n"
				+ "}";
		Optional<BigDecimal> result2;
		testSubject = jsonParser;
		result2 = testSubject.getRate(rateData2);
		assertThat(result2).isEqualTo(Optional.of(new BigDecimal("3.7826")));
	}
	
	
}