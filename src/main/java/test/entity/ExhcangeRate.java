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

import test.Rate;

@Entity
public class ExhcangeRate {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDate date;
	@Column(precision = 19, scale = 4)
	private BigDecimal rate;
	
	@ManyToOne
	@JoinColumn(name = "countryCode_id", nullable = false)
	private CountryCode code;
	
	public ExhcangeRate() {
	}

	public ExhcangeRate(LocalDate date, BigDecimal rate,
			CountryCode code) {
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

	public CountryCode getCode() {
		return code;
	}

	public void setCode(CountryCode code) {
		this.code = code;
	}
	
	
}
