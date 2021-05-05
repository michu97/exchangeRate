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

	final Optional<BigDecimal> getRate(LocalDate date, CurrencyCode code) {
		String rawData = getRawData(date, code);
		Optional<Rate> rate = parseData(rawData);

		if (rate.isPresent()) {
			return Optional.of(rate.get().getRate());
		}

		if (nextApi != null) {
			Optional<BigDecimal> fromNextApi = nextApi.getRate(date, code);
			fromNextApi.ifPresent(x -> save(new Rate(x, date, code)));
			return fromNextApi;
		}

		return Optional.empty();
	}

	abstract String getRawData(LocalDate date, CurrencyCode code);
	abstract Optional<Rate> parseData(String rawData);
	
	void save(Rate rate) {
	}
}
