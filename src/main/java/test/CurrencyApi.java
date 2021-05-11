package test;

import java.time.LocalDate;
import java.util.Optional;

import api.CurrencyCode;

public class CurrencyApi extends Api{

	public CurrencyApi(Api nextApi) {
		super(nextApi);
	}
	
	public CurrencyApi() {
		this(null);
	}

	@Override
	public String getRawData(LocalDate date, CurrencyCode code) {
		return "";
	}

	@Override
	public Optional<Rate> parseData(String rawData) {
		System.out.println("waluty here");
		return Optional.empty();
	}

}
