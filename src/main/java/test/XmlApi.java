package test;

import java.time.LocalDate;
import java.util.Optional;

import api.CurrencyCode;

public class XmlApi extends Api{
	
	public XmlApi(Api nextApi) {
		super(nextApi);
	}
	
	public XmlApi() {
		super();
	}

	@Override
	public String getRawData(LocalDate date, CurrencyCode code) {
		return "";
	}

	@Override
	public Optional<Rate> parseData(String rawData) {
		System.out.println("xml api here");
		return Optional.empty();
	}

}
