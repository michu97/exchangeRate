package test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.junit.Test;
import org.mockito.Mockito;

import api.CurrencyCode;

public class ApiTest {

	@Test
	public void test() {
		//given
		LocalDate requestDate = LocalDate.of(2021, 4, 9);
		CurrencyCode requestCode = CurrencyCode.USD;
		String response = "{\"table\":\"A\",\"currency\":\"dolar amerykañski\",\"code\":\"USD\",\"rates\":[{\"no\":\"068/A/NBP/2021\",\"effectiveDate\":\"2021-04-09\",\"mid\":3.8208}]}";
		Api nbpApi = mock(NbpApi.class);
		Api cache = Mockito.spy(new Cache(nbpApi));
		
		//when
		when(nbpApi.getRawData(requestDate, requestCode)).thenReturn(response);
		when(nbpApi.parseData(response)).thenReturn(Optional.of(new Rate(new BigDecimal("3.8208"), requestDate, requestCode)));
		cache.getRate(requestDate, requestCode);
		cache.getRate(requestDate, requestCode);
		cache.getRate(requestDate, requestCode);
		
		//then
		verify(nbpApi, times(1)).getRawData(requestDate, requestCode);
	}
	
	@Test
	public void test2() {
		//given
		LocalDate requestDate = LocalDate.of(2021, 4, 9);
		CurrencyCode requestCode = CurrencyCode.USD;
		String response = "{\"table\":\"A\",\"currency\":\"dolar amerykañski\",\"code\":\"USD\",\"rates\":[{\"no\":\"068/A/NBP/2021\",\"effectiveDate\":\"2021-04-09\",\"mid\":3.8208}]}";
		Api nbpApi = mock(NbpApi.class);
		Api cache = Mockito.spy(new Cache(nbpApi));
		
		//when
		when(nbpApi.getRawData(requestDate, requestCode)).thenReturn(response);
		when(nbpApi.parseData(response)).thenReturn(Optional.of(new Rate(new BigDecimal("3.8208"), requestDate, requestCode)));
		cache.getRate(requestDate, requestCode);
		cache.getRate(requestDate, requestCode);
		cache.getRate(requestDate, requestCode);
		
		//then
		verify(nbpApi, times(1)).getRawData(requestDate, requestCode);
	}

}
