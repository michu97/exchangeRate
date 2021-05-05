package test.nbp;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NbpRate {
	private final String no;
	private final String effectiveDate;
	private final BigDecimal mid;

	public NbpRate(@JsonProperty("no") String no,
			@JsonProperty("effectiveDate") String effectiveDate,
			@JsonProperty("mid") BigDecimal mid) {
		this.no = no;
		this.effectiveDate = effectiveDate;
		this.mid = mid;
	}

	public String getNo() {
		return no;
	}

	public String getEffectiveDate() {
		return effectiveDate;
	}

	public BigDecimal getMid() {
		return mid;
	}

}
