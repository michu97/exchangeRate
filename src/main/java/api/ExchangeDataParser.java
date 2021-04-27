package api;

import java.math.BigDecimal;
import java.util.Optional;

interface ExchangeDataParser {
	Optional<BigDecimal> getRate(String rateData);
}
