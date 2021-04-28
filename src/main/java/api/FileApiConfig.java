package api;

class FileApiConfig {
	private final ExchangeRateDataParser parser = new FileParser();
	private final ExchangeRateRepository repository = new FileRepository(parser);
	
	public ExchangeRateProvider getRateProvider() {
		return new ExchangeRateProvider(repository);
	}
}
