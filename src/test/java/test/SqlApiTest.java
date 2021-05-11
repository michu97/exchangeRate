package test;

import static org.assertj.core.api.Assertions.assertThat;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.junit.Test;

import api.CurrencyCode;

public class SqlApiTest {

	@Test
	public void Should_Get_Data_From_Database() {
		//given
		SqlApi api = new SqlApi();
		api.save(new Rate(new BigDecimal("3.2567"), LocalDate.of(2021, 5, 7), CurrencyCode.EUR));
		
		//when
		Optional<BigDecimal> rate = api.getRate(LocalDate.of(2021, 5, 7), CurrencyCode.EUR);
		
		//then
		assertThat(rate.get()).isEqualTo(new BigDecimal("3.2567"));
	}
	
	

}
