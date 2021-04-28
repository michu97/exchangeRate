package demo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import demo.service.HttpRequestSeriveImpl;
import demo.service.HttpRequestService;

public class NbpApi implements ExchangeApi {
	private final HttpRequestService service;
	
	public NbpApi() {
		service = new HttpRequestSeriveImpl();
	}

	@Override
	public Optional<BigDecimal> getAmountFromPLN(LocalDate date, BigDecimal amount, 
			CurrencyCode code) {
		ResponseReciver reciver = new NbpResponseReciver(date, code, service);
		Optional<BigDecimal> rate = reciver.getExchangeRate();
		if (rate.isEmpty()) {
			return rate;
		};
		return Optional.ofNullable(rate.get().multiply(amount));
	}

	@Override
	public Optional<BigDecimal> getAmountFromPLN(BigDecimal amount, CurrencyCode code) {
		return getAmountFromPLN(LocalDate.now(), amount, code);
	}

}
