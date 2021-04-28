package demo;

import java.math.BigDecimal;
import java.util.Optional;

import api.Api;
import api.CurrencyCode;
import api.NbpApi;

public class SaleDocumentService {
	public void insert() {
		Api exchangeRateApi = new NbpApi();
		Optional<BigDecimal> amount = 
				exchangeRateApi.getAmountInPLN(new BigDecimal("80"),
						CurrencyCode.USD);
		amount.ifPresent(System.out::println);
	}
}