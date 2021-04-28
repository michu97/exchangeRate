package demo.tools;

import java.math.BigDecimal;
import java.util.Optional;

public interface Validator {
	Optional<BigDecimal> getRate(String content);
}
 