package demo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public class FileResponseReciver implements ResponseReciver {
	private final LocalDate date;
	private final CurrencyCode code;
	
	public FileResponseReciver(LocalDate date, CurrencyCode code) {
		this.date = date;
		this.code = code;
	}

	@Override
	public Optional<BigDecimal> getExchangeRate() {
		return Optional.of(new BigDecimal("3.5600"));
	}
}
