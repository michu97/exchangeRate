package api;

class FileConfig {
	private final ExchangeDataParser parser = new FileParser();
	
	
	public ExchangeRateProvider getRateProvider() {
		return new ExchangeRateProvider(new FileRepository(parser));
	}
}
