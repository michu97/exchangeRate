package test.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import test.Rate;

@Entity
@Table(name = "ExchangeRate")
public class ExchangeRateDb {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDate date;
	@Column(precision = 19, scale = 4)
	private BigDecimal rate;
	
	@ManyToOne
	@JoinColumn(name = "countryCode_id", nullable = false)
	private CurrencyCodeDb code;
	
	public ExchangeRateDb() {
	}

	public ExchangeRateDb(LocalDate date, BigDecimal rate,
			CurrencyCodeDb code) {
		this.date = date;
		this.rate = rate;
		this.code = code;
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

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public CurrencyCodeDb getCode() {
		return code;
	}

	public void setCode(CurrencyCodeDb code) {
		this.code = code;
	}
	
	
}
