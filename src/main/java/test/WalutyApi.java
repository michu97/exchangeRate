package test;

import java.time.LocalDate;
import java.util.Optional;

import api.CurrencyCode;

public class WalutyApi extends Api{

	public WalutyApi(Api nextApi) {
		super(nextApi);
	}
	
	public WalutyApi() {
		super();
	}

	@Override
	String getRawData(LocalDate date, CurrencyCode code) {
		return "";
	}

	@Override
	Optional<Rate> parseData(String rawData) {
		System.out.println("waluty here");
		return Optional.empty();
	}

}
