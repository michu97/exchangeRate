package api;

class FileApiConfig {
	private final ExchangeDataParser parser = new FileParser();
	private final ExchangeRateRepository repository = new FileRepository(parser);
	
	public ExchangeRateProvider getRateProvider() {
		return new ExchangeRateProvider(repository);
	}
}
