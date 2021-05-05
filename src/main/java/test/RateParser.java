package test;

import java.util.Optional;

public interface RateParser {
	Optional<Rate> parseToDomain(String rawData);
}
