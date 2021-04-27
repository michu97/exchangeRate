package demo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public class SaleDocumentService {
	public void insert() {
		api.Api api = new api.NbpApi();
		Optional<BigDecimal> amount = 
				api.getRateByCodeAndDate(CurrencyCode.EUR, LocalDate.now(),
						new BigDecimal("150"));
		System.out.println(amount);
	}
}
