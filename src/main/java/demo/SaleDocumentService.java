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
		System.out.println(rate);
		
		Strategy jsonFileStrategy = new JsonFileStrategy(null);
		exchangeRateApi.setStrategy(jsonFileStrategy);
		exchangeRateApi.getAmountInPLN(new BigDecimal("150"), CurrencyCode.USD);
		
		XmlFileStartegy xmlFileStartegy = new XmlFileStartegy(null);
		exchangeRateApi.setStrategy(xmlFileStartegy);
	}
}