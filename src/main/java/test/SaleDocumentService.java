package test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import api.CurrencyCode;

public class SaleDocumentService {
	public void insert() {
		Api nbp = new NbpApi(new NbpJsonParser());
		Api file = new FileApi(nbp);
		Api xmlApi = new XmlApi(file);
		Api sqlApi = new SqlApi(xmlApi);
		Api cacheApi = new Cache(sqlApi);
		CurrencyExchanger currencyExchanger = new CurrencyExchanger(cacheApi);

		Optional<BigDecimal> pln = currencyExchanger.getAmountInPLN(
				LocalDate.of(2021, 4, 9),
				CurrencyCode.USD,
				new BigDecimal("150"));
		pln.ifPresent(System.out::println);

		Optional<BigDecimal> pln2 = currencyExchanger.getAmountInPLN(
				LocalDate.of(2020, 4, 15),
				CurrencyCode.USD,
				new BigDecimal("120"));
		pln2.ifPresent(System.out::println);
		
		for (int i = 0; i < 20; i++) {
			currencyExchanger.getAmountInPLN(LocalDate.now().minusDays(i), CurrencyCode.USD, new BigDecimal("150"));
		}
	}
}
