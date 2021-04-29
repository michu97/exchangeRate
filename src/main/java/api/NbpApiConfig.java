package api;

class NbpApiConfig {
	private final ExternalExchangeRateProvider provider = new NbpDataProvider();
	private final ExchangeRateDataParser parser = new NbpJsonParser();
	private final ExchangeRateRepository repository = 
			new NbpRepository(provider, parser);

	public ExchangeRateProvider getExchangeProvider() {
		return new ExchangeRateProvider(repository);
	}
}