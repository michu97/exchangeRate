package demo.tools;

import java.math.BigDecimal;
import java.util.Optional;

public interface DataParser {
	Optional<BigDecimal> getRate(String content);
}
