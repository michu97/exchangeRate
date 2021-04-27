package api;

import java.time.LocalDate;

import demo.CurrencyCode;

interface PathToSourceBuilder {
	String getPath(CurrencyCode code, LocalDate date);
}
