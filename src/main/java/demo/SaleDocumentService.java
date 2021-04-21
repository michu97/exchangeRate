package demo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public class SaleDocumentService {
	public void insert() {
		ExchangeApi api = new NbpApi();
		Optional<BigDecimal> amount = 
				api.getAmountFromPLN(LocalDate.of(2001, 4, 10), 
						new BigDecimal(150), CurrencyCode.EUR);
		System.out.println(amount);
	}
}
