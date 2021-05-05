package api;

class NbpApiConfig {
	private final ExternalExchangeRateProvider provider = new NbpDataProvider();
	private final ExchangeRateDataParser parser = new NbpJsonParser();
	

	public ExchangeRateRepository getRepository() {
		return new NbpRepository(provider, parser);
	}
}