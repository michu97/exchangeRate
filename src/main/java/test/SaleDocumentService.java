package test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import api.CurrencyCode;

public class SaleDocumentService {
	public void insert() {
		Api nbp = new NbpApi(new NbpParser());
		Api file = new FileApi(nbp);
		Api xmlApi = new XmlApi(file);
		Api walutyApi = new WalutyApi(xmlApi);
		Api cacheApi = new Cache(walutyApi);
		
		Optional<BigDecimal> pln = cacheApi.getAmountInPLN(LocalDate.of(2021, 4, 9), CurrencyCode.USD, new BigDecimal("150"));
		pln.ifPresent(System.out::println);
		Optional<BigDecimal> pln2 = cacheApi.getAmountInPLN(LocalDate.of(2021, 4, 9), CurrencyCode.USD, new BigDecimal("120"));
		pln2.ifPresent(System.out::println);
	}
}
