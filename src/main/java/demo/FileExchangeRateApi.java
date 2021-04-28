package demo;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public class FileExchangeRateApi implements ExchangeApi {
	private final File exchangeRateFile;
	
	public FileExchangeRateApi(File exchangeRateFile) {
		this.exchangeRateFile = exchangeRateFile;
	}

	@Override
	public Optional<BigDecimal> getAmountFromPLN(LocalDate date,
			BigDecimal amount, CurrencyCode code){ 
		ResponseReciver reciver = new FileResponseReciver(date, code);
		Optional<BigDecimal> rate = reciver.getExchangeRate();
		if (rate.isEmpty()) {
			return rate;
		};
		return Optional.ofNullable(rate.get().multiply(amount));
	}

	@Override
	public Optional<BigDecimal> getAmountFromPLN(BigDecimal amount,
			CurrencyCode code) {
		return getAmountFromPLN(LocalDate.now(), amount, code);
	}
}