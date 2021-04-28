package api;

import java.math.BigDecimal;
import java.util.Optional;

interface ExchangeRateDataParser {
	Optional<BigDecimal> getRate(String rateData);
}
