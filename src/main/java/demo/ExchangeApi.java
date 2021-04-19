package demo;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface ExchangeApi {
	BigDecimal getAmountFromPLN(LocalDate date, BigDecimal amount,
								CurrencyCode code);
	BigDecimal getAmountFromPLN(BigDecimal amount, CurrencyCode code);
}
