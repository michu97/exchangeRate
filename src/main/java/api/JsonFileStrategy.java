package api;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public class JsonFileStrategy implements Strategy {
	private File jsonFile;
	
	public JsonFileStrategy(File jsonFile) {
		this.jsonFile = jsonFile;
	}

	@Override
	public Optional<BigDecimal> getExchangeRate(CurrencyCode code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<BigDecimal> getExchangeRate(CurrencyCode code,
			LocalDate date) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
