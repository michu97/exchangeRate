package api;

import java.math.BigDecimal;
import java.util.Optional;

class FileParser implements ExchangeRateDataParser {

	@Override
	public Optional<BigDecimal> getRate(String rateData) {
		return Optional.of(new BigDecimal("150"));
	}
}
