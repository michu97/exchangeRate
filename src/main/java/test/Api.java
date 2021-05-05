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

	private Optional<BigDecimal> getRate(LocalDate date, CurrencyCode code) {
		date = validateDate(date);
		String rawData = getRawData(date, code);
		Optional<Rate> rate = parseData(rawData);

		if (rate.isPresent()) {
			return Optional.of(rate.get().getRate());
		}

		if (nextApi != null) {
			return getRateFromNextApi(date, code);
		}

		return Optional.empty();
	}

	protected Optional<BigDecimal> getRateFromNextApi(LocalDate date,
			CurrencyCode code) {
		return nextApi.getRate(date, code);
	}

	
	public Optional<BigDecimal> getAmountInPLN(LocalDate date, CurrencyCode code,
			BigDecimal amount) {
		for (int i = 0; i <= 10; i++) {
			Optional<BigDecimal> rate = getRate(date.minusDays(i), code);
			if (rate.isPresent()) {
				return Optional.of(rate.get().multiply(amount));
			}
		}
		return Optional.empty();
	}

	
	public Optional<BigDecimal> getAmountInPLN(CurrencyCode code,
			BigDecimal amount) {
		return getAmountInPLN(LocalDate.now(), code, amount);
	}

	abstract String getRawData(LocalDate date, CurrencyCode code);
	abstract Optional<Rate> parseData(String rawData);
	
	private LocalDate validateDate(LocalDate date) {
		return date.isBefore(LocalDate.now()) ? date : LocalDate.now();
	}
}
