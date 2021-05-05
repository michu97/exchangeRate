package api;

class FileApiConfig {
	private final ExchangeRateDataParser parser = new FileParser();
	
	public ExchangeRateRepository getRepository() {
		return new FileRepository(parser);
	}
}
