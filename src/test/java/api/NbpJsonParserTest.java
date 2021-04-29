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
	public void Should_Return_Empty_Optional_For_Empty_Json() {
		//given
		String emptyJson = "{}";
		Optional<BigDecimal> actual;
		Optional<BigDecimal> expected = Optional.empty();
		
		//when
		actual = jsonParser.getRate(emptyJson);
		
		//then
		assertThat(actual).isEqualTo(expected);
	}
	
	@Test
	public void Should_Return_Rate_From_NBP_Json_Format() {
		//given
		String nbpResponse = "{\"table\":\"A\",\"currency\":\"dolar amerykañski\",\"code\":\"USD\",\"rates\":[{\"no\":\"080/A/NBP/2021\",\"effectiveDate\":\"2021-04-27\",\"mid\":3.7826}]}";
		BigDecimal expected = new BigDecimal("3.7826");
		Optional<BigDecimal> actual;
		
		//when
		actual = jsonParser.getRate(nbpResponse);
		
		//then
		assertThat(actual).hasValue(expected);
	}
	
	@Test
	public void Should_Return_Empty_Optional_For_Data_Not_Found() {
		//given
		String nbpResponse = "404 NotFound - Not Found - Brak danych";
		Optional<BigDecimal> expected = Optional.empty();
		Optional<BigDecimal> actual;
		
		//when
		actual = jsonParser.getRate(nbpResponse);
		
		//then
		assertThat(actual).isEqualTo(expected);
	}
	
}