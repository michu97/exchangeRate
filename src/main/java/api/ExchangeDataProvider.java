package api;

import java.time.LocalDate;

import demo.CurrencyCode;

interface ExchangeDataProvider {
	String getExchangeRate(CurrencyCode code, LocalDate date);
}
