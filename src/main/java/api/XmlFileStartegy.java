package api;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public class XmlFileStartegy implements Strategy {
	private File xmlFile;
	
	public XmlFileStartegy(File xmlFile) {
		this.xmlFile = xmlFile;
	}

	@Override
	public Optional<BigDecimal> getExchangeRate(CurrencyCode code) {
		return Optional.empty();
	}

	@Override
	public Optional<BigDecimal> getExchangeRate(CurrencyCode code,
			LocalDate date) {
		return Optional.empty();
	}

}
