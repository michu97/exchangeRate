package demo;

import java.math.BigDecimal;import java.time.LocalDate;
import java.util.Optional;

import api.Api;
import api.CurrencyCode;
import api.ExchangeRateApi;
import api.JsonFileStrategy;
import api.NbpStrategy;
import api.Strategy;
import api.XmlFileStartegy;

public class SaleDocumentService {
	public void insert() {
		Strategy nbpStrategy = new NbpStrategy();
		Api exchangeRateApi = new ExchangeRateApi(nbpStrategy);
		Optional<BigDecimal> rate = exchangeRateApi.getAmountInPLN(new BigDecimal("150"), CurrencyCode.USD);
		
		Strategy jsonFileStrategy = new JsonFileStrategy(null);
		ExchangeRateApi jsonApi = new ExchangeRateApi(jsonFileStrategy);
		jsonApi.getAmountInPLN(null, null);
		
		XmlFileStartegy xmlFileStartegy = new XmlFileStartegy(null);
		ExchangeRateApi xmlApi = new ExchangeRateApi(xmlFileStartegy);
		xmlApi.getAmountInPLN(null, null);
	}
}