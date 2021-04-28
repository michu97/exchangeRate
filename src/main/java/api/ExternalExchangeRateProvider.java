package api;

import java.time.LocalDate;

interface ExternalExchangeRateProvider {
	String getExchangeRate(CurrencyCode code, LocalDate date);
}
