package demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import demo.service.FakeHttpRequestImpl;

public class NbpResponseReciverTest {
	private FakeHttpRequestImpl service;
	
	@Before
	public void setup() {
		service = new FakeHttpRequestImpl(true);
	}
	
	@Test
	public void shouldReturnEmptyOptionalWhenIsNoInternet() {
		//given
		ResponseReciver reciver = new NbpResponseReciver(LocalDate.now(),
				CurrencyCode.USD, service);
		service.setInternetConnection(false);
		
		//when
		Optional<BigDecimal> expected = reciver.getExchangeRate();
		
		//then
		assertThat(expected).isEqualTo(Optional.empty());
	}
	
	@Test
	public void shouldReturnUsdRateFor2021_04_19() {
		//given
		ResponseReciver reciver = new NbpResponseReciver(LocalDate.of(2021, 4, 19),
				CurrencyCode.USD, service);
		BigDecimal excepted = new BigDecimal("3.7816");
		
		//when
		Optional<BigDecimal> actual = reciver.getExchangeRate();
		
		//then
		assertThat(actual.get()).isEqualTo(excepted);
	}
	
}
