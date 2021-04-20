package demo;

import java.math.BigDecimal;
import java.util.Optional;

public interface ResponseReciver {
	Optional<BigDecimal> getExchangeRate();
}
