package api;

class NbpApiConfig {
	private final ExchangeDataProvider provider = new NbpDataProvider();
	private final ExchangeDataParser parser = new NbpJsonParser();
	private final ExchangeRateRepository repository = 
			new NbpRepository(provider, parser);

	public ExchangeRateProvider getExchangeProvider() {
		return new ExchangeRateProvider(repository);
	}
}
