package test.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;

import api.CurrencyCode;
import test.Api;
import test.NbpApi;
import test.Rate;
import test.SqlApi;

public class ExchangeRateRepositoryDbTest {
	ExchangeRateRepository repository;
	
	@Before
	public void setup() {
		repository = new ExchangeRateRepositoryDb(
				new Configuration()
				.configure()
				.buildSessionFactory());
	}
	
	@Test
	public void should_database_save_when_response_return_from_externalApi() {
		//given
		LocalDate requestDate = LocalDate.of(2021, 4, 9);
		CurrencyCode requestCode = CurrencyCode.USD;
		String response = "{\"table\":\"A\",\"currency\":\"dolar amerykañski\",\"code\":\"USD\",\"rates\":[{\"no\":\"068/A/NBP/2021\",\"effectiveDate\":\"2021-04-09\",\"mid\":3.8208}]}";
		Api nbpApi = mock(NbpApi.class);
		Api sql = new SqlApi(nbpApi);
		
		//when
		when(nbpApi.getRawData(requestDate, requestCode)).thenReturn(response);
		when(nbpApi.parseData(response)).thenReturn(Optional.of(new Rate(new BigDecimal("3.8208"), requestDate, requestCode)));
		sql.getRate(requestDate, requestCode);
		Optional<Rate> result = repository.getRateByDateAndCode(requestDate, requestCode);
		
		//then
		assertThat(result.get().getCode()).isEqualTo(requestCode);
		assertThat(result.get().getValue()).isEqualTo(new BigDecimal("3.8208"));
		assertThat(result.get().getDate()).isEqualTo(requestDate);
	}

}
