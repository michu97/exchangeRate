package test.nbp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import api.CurrencyCode;
import test.Rate;


public class NbpTable {
	private final String table;
	private final String currency;
	private final String code;
	private final List<NbpRate> rates;

	public NbpTable(@JsonProperty("table") String table,
			@JsonProperty("currency") String currency,
			@JsonProperty("code") String code,
			@JsonProperty("rates") List<NbpRate> rates) {
		this.table = table;
		this.currency = currency;
		this.code = code;
		this.rates = rates;
	}

	public String getTable() {
		return table;
	}

	public String getCurrency() {
		return currency;
	}

	public String getCode() {
		return code;
	}

	public List<NbpRate> getRates() {
		return rates;
	}
	
	public Rate toModel() {
		NbpRate nbpRate = rates.get(rates.size() - 1);
		BigDecimal rate = nbpRate.getMid();
		LocalDate date = LocalDate.parse(nbpRate.getEffectiveDate());
		CurrencyCode rateCode = CurrencyCode.valueOf(this.code);
		
		return new Rate(rate, date, rateCode);
	}
}
