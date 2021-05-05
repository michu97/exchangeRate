package test;

import java.time.LocalDate;
import java.util.Optional;

import api.CurrencyCode;

public class FileApi extends Api {
	
	public FileApi(Api nextApi) {
		super(nextApi);
	}
	
	public FileApi() {
		super();
	}

	@Override
	String getRawData(LocalDate date, CurrencyCode code) {
		return "";
	}

	@Override
	Optional<Rate> parseData(String rawData) {
		System.out.println("File Api here");
		return Optional.empty();
	}

}
