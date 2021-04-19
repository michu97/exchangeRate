package demo;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Test;

import demo.service.FakeHttpRequestImpl;
import demo.service.HttpRequestService;

public class SimpleTest {
	
	@Test
	public void shouldAssertTrue() {
		HttpRequestService service = new FakeHttpRequestImpl(false);
		ResponseReciver reciver = new NbpResponseReciver(LocalDate.of(2021, 4, 19), CurrencyCode.EUR, service);
		
		assertThatThrownBy(() -> {
			reciver.getExchangeRate();
		}).isInstanceOf(RuntimeException.class)
		.hasMessage("Cannot connecting to external api");
	}
	
	@Test
	public void shouldAssertTrue2() {
		// given
		HttpRequestService service = new FakeHttpRequestImpl(true);
		ResponseReciver reciver = new NbpResponseReciver(LocalDate.of(2021, 4, 19), CurrencyCode.EUR, service);
		BigDecimal expected = new BigDecimal("3.7816");
		
		//when
		BigDecimal actual = reciver.getExchangeRate();
		
		//then
		assertThat(expected).isEqualTo(actual);
	}
}
