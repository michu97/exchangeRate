package api;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import demo.CurrencyCode;

public class FileApi implements Api {
	private FileApiConfig config = new FileApiConfig();
	
	@Override
	public Optional<BigDecimal> getRateByCode(BigDecimal amount,
			CurrencyCode code) {
		return getRateByCodeAndDate(LocalDate.now(), amount, code);
	}

	@Override
	public Optional<BigDecimal> getRateByCodeAndDate(LocalDate date,
			BigDecimal amount, CurrencyCode code) {
		return config.getRateProvider().getRateByCodeAndDate(code, date);
	}

}
