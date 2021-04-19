package demo;

import java.math.BigDecimal;
import java.time.LocalDate;

import demo.service.HttpRequestSeriveImpl;
import demo.service.HttpRequestService;

public class NbpApi implements ExchangeApi {
	private final HttpRequestService service;
	
	public NbpApi() {
		service = new HttpRequestSeriveImpl();
	}

	@Override
	public BigDecimal getAmountFromPLN(LocalDate date, BigDecimal amount, 
			CurrencyCode code) {
		ResponseReciver reciver = new NbpResponseReciver(date, code, service);
		return reciver.getExchangeRate();
	}

	@Override
	public BigDecimal getAmountFromPLN(BigDecimal amount, CurrencyCode code) {
		return getAmountFromPLN(LocalDate.now(), amount, code);
	}

}
