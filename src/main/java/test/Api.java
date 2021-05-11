package test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import api.CurrencyCode;

public abstract class Api {
	private final Api nextApi;

	public Api(Api nextApi) {
		this.nextApi = nextApi;
	}
	
	public Api() {
		this.nextApi = null;
	}

	public final Optional<BigDecimal> getRate(LocalDate date, CurrencyCode code) {
		String rawData = getRawData(date, code);
		Optional<Rate> rate = parseData(rawData);

		if (rate.isPresent()) {
			return rate.map(Rate::getValue);
		}
		
		if (nextApi != null) {
			return checkNextApi(date, code);
		}

		return Optional.empty();
	}

	final private Optional<BigDecimal> checkNextApi(LocalDate date,
			CurrencyCode code) {
		Optional<BigDecimal> fromNextApi = nextApi.getRate(date, code);
		fromNextApi.ifPresent(x -> save(new Rate(x, date, code)));
		return fromNextApi;
	}

	public abstract String getRawData(LocalDate date, CurrencyCode code);
	public abstract Optional<Rate> parseData(String rawData);
	
	void save(Rate rate) {
	}
}
