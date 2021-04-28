package demo.tools;

import java.time.LocalDate;

import demo.CurrencyCode;

public interface UrlBuilder {
	String getUrl(CurrencyCode code, LocalDate date);
}
