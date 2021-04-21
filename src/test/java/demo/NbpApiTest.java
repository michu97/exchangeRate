package demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

public class NbpApiTest {
private NbpApi api;
	
	@Before
	public void setup() {
		api = new NbpApi();
	}
	
	@Test
	public void shouldReturnRateOfEmptyOptionalWhenDateIsBefore2002_01_02() {
		//given
		Optional<BigDecimal> expected = Optional.empty();
		
		//when
		Optional<BigDecimal> actual = 
				api.getAmountFromPLN(LocalDate.of(2001, 1, 1), new BigDecimal(10), CurrencyCode.USD);
		
		//then
		assertThat(actual).isEqualTo(expected);
	}
	
	@Test
	public void shouldReturnRateOfTodayWhenDateIsAfter() {
		//when
		Optional<BigDecimal> actual = api.getAmountFromPLN(LocalDate.of(2021,4,10), new BigDecimal(10), CurrencyCode.EUR);
		Optional<BigDecimal> expected = api.getAmountFromPLN(LocalDate.of(2021,4,10),new BigDecimal(10), CurrencyCode.EUR);
		
		//then
		assertThat(actual.get()).isEqualTo(expected.get());
	}
	
	@Test
	public void shouldReturnCorrectConversionForTheGivenDate() {
		//given
		BigDecimal expected = new BigDecimal("5.3406").multiply(new BigDecimal("178.4500"));
		
		//when
		Optional<BigDecimal> actual = api.getAmountFromPLN(LocalDate.of(2021, 4, 7), new BigDecimal("178.4500"), CurrencyCode.GBP);
		
		//then
		assertThat(actual.get()).isEqualTo(expected);
	}
	
	@Test
	public void shouldReturnRateBeforeWeekend() {
		//given
		BigDecimal expected = new BigDecimal("5.2393").multiply(new BigDecimal(25));
		
		//when
		Optional<BigDecimal> actual = api.getAmountFromPLN(LocalDate.of(2021, 4, 10), new BigDecimal(25), CurrencyCode.GBP);
		
		//then
		assertThat(actual.get()).isEqualTo(expected);
	}
}
