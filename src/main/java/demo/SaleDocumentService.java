package demo;

import java.math.BigDecimal;

public class SaleDocumentService {
	public void insert() {
		ExchangeApi api = new NbpApi();
		BigDecimal amount = 
				api.getAmountFromPLN(new BigDecimal(150), CurrencyCode.EUR);
		System.out.println(amount);
	}
}
