package demo;

import api.Api;
import api.ExchangeRateApi;
import api.JsonFileStrategy;
import api.NbpStrategy;
import api.Strategy;
import api.XmlFileStartegy;

public class SaleDocumentService {
	public void insert() {
		Strategy nbpStrategy = new NbpStrategy();
		Api exchangeRateApi = new ExchangeRateApi(nbpStrategy);
		
		Strategy jsonFileStrategy = new JsonFileStrategy(null);
		exchangeRateApi.setStrategy(jsonFileStrategy);
		
		XmlFileStartegy xmlFileStartegy = new XmlFileStartegy(null);
		exchangeRateApi.setStrategy(xmlFileStartegy);
	}
}