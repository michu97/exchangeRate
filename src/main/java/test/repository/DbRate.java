package test.repository;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import api.CurrencyCode;
import test.Rate;

@Entity
@Table(name = "Rate")
public class DbRate {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate date;
	private String currencyCode;
	@Column(precision = 19, scale = 5)
	private BigDecimal rate;
	
	public DbRate() {
	}

	public DbRate(LocalDate date, String currencyCode,
			BigDecimal rate) {
		this.date = date;
		this.currencyCode = currencyCode;
		this.rate = rate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	@Override
	public String toString() {
		return "{\"id\":\"" + id + "\", \"date\":\"" + date
				+ "\", \"currencyCode\":\"" + currencyCode + "\", \"rate\":\""
				+ rate + "\"}";
	}
	
	public Rate toDomain() {
		return new Rate(rate, date, CurrencyCode.valueOf(currencyCode));
	}
	
	public static DbRate fromDomain(Rate rate) {
		return new DbRate(
				rate.getDate(),
				rate.getCode().name(),
				rate.getValue());
	}
	
}
