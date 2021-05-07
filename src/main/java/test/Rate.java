package test;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import api.CurrencyCode;

public class Rate {
	private final BigDecimal value;
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")  
	private final LocalDate date;
	private final CurrencyCode code;

	
	public Rate(
			@JsonProperty("rate") BigDecimal rate,
			@JsonProperty("date") LocalDate date,
			@JsonProperty("code") CurrencyCode code) {
		this.value = rate;
		this.date = date;
		this.code = code;
	}
	
	@JsonGetter("rate")
	public BigDecimal getValue() {
		return value;
	}

	@JsonGetter("date")
	public LocalDate getDate() {
		return date;
	}

	@JsonGetter("code")
	public CurrencyCode getCode() {
		return code;
	}

	@Override
	public String toString() {
		return "{\"rate\":\"" + value + "\", \"date\":\"" + date
				+ "\", \"code\":\"" + code + "\"}";
	}
}
