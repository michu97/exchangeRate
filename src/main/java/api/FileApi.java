package api;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public class FileApi implements Api {
	private FileApiConfig config = new FileApiConfig();
	
	@Override
	public Optional<BigDecimal> getAmountInPLN(BigDecimal amount,
			CurrencyCode code) {
		return getAmountInPLN(LocalDate.now(), amount, code);
	}

	@Override
	public Optional<BigDecimal> getAmountInPLN(LocalDate date,
			BigDecimal amount, CurrencyCode code) {
		return config.getRateProvider().getRateByCodeAndDate(code, date);
	}

}
