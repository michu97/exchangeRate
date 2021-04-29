package api;

import static org.assertj.core.api.Assertions.assertThat;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.annotation.processing.Generated;

import org.junit.Before;
import org.junit.Test;

public class ExchangeRateProviderTest {
	private ExchangeRateProvider provider;
	private ExchangeRateRepository repository;
	private ExternalExchangeRateProvider externalProvider;
	private ExchangeRateDataParser parser;
	
	@Before
	public void setup() {
		externalProvider = new NbpDataProviderMock();
		parser = new NbpJsonParser();
		repository = new NbpRepository(externalProvider, parser);
		provider = new ExchangeRateProvider(repository);
	}

	@Test
	public void Should_Return_Eariler_Rate_If_For_Given_Date_Doesnt_Exist() {
		//given
		BigDecimal expected = new BigDecimal("3.7855");
		LocalDate givenDate = LocalDate.of(2021, 4, 26);
		
		//when
		Optional<BigDecimal> actual = provider.getRateByCodeAndDate(CurrencyCode.USD, givenDate);
		
		//then
		assertThat(actual).hasValue(expected);
	}
	
	@Test
	public void Should_Return_Empty_Optional_If_Rate_For_Given_Date_Doesnt_Exist_More_Than_10_Days_Earlier() {
		//given
		Optional<BigDecimal> expected = Optional.empty();
		LocalDate givenDate = LocalDate.of(2021, 3, 15);
		
		//when
		Optional<BigDecimal> actual = provider.getRateByCodeAndDate(CurrencyCode.USD, givenDate);
		
		//then
		assertThat(actual).isEqualTo(expected);
	}
	
	@Test
	public void Should_Return_Rate_If_Last_Rate_Exists_For_Exactly_10_Days_Before() {
		//given
		BigDecimal expected = new BigDecimal("3.8144");
		LocalDate givenDate = LocalDate.of(2021, 4, 22);
		
		//when
		Optional<BigDecimal> actual = provider.getRateByCodeAndDate(CurrencyCode.USD, givenDate);
		
		//then
		assertThat(actual).hasValue(expected);
	}

	private class NbpDataProviderMock implements ExternalExchangeRateProvider {
		private final Map<LocalDate, String> mockData = new HashMap<>();
		
		public NbpDataProviderMock() {
			mockData.put(LocalDate.of(2021, 4, 28), "{\"table\":\"A\",\"currency\":\"dolar amerykañski\",\"code\":\"USD\",\"rates\":[{\"no\":\"081/A/NBP/2021\",\"effectiveDate\":\"2021-04-28\",\"mid\":3.7939}]}");
			mockData.put(LocalDate.of(2021, 4, 27), "{\"table\":\"A\",\"currency\":\"dolar amerykañski\",\"code\":\"USD\",\"rates\":[{\"no\":\"080/A/NBP/2021\",\"effectiveDate\":\"2021-04-27\",\"mid\":3.7826}]}");
			mockData.put(LocalDate.of(2021, 4, 23), "{\"table\":\"A\",\"currency\":\"dolar amerykañski\",\"code\":\"USD\",\"rates\":[{\"no\":\"078/A/NBP/2021\",\"effectiveDate\":\"2021-04-23\",\"mid\":3.7855}]}");
			mockData.put(LocalDate.of(2021, 4, 12), "{\"table\":\"A\",\"currency\":\"dolar amerykañski\",\"code\":\"USD\",\"rates\":[{\"no\":\"069/A/NBP/2021\",\"effectiveDate\":\"2021-04-12\",\"mid\":3.8144}]}");
		}

		@Override
		public String getExchangeRate(CurrencyCode code, LocalDate date) {
			String result = mockData.get(date);
			if (result != null) {
				return result;
			}
			
			return "404 NotFound - Not Found - Brak danych";
		}
	}
}