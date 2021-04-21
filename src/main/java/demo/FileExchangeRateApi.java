package demo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public class FileExchangeRateApi implements ExchangeApi {

	@Override
	public Optional<BigDecimal> getAmountFromPLN(LocalDate date,
			BigDecimal amount, CurrencyCode code) {
		return Optional.of(new BigDecimal("1.567"));
	}

	@Override
	public Optional<BigDecimal> getAmountFromPLN(BigDecimal amount,
			CurrencyCode code) {
		return getAmountFromPLN(LocalDate.now(), amount, code);
	}
}
