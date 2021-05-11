package test;

import java.time.LocalDate;
import java.util.Optional;

import org.hibernate.cfg.Configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import api.CurrencyCode;
import test.repository.ExchangeRateRepository;
import test.repository.ExchangeRateRepositoryDb;

public class SqlApi extends Api {
	private final ExchangeRateRepository repository;
	
	public SqlApi(ExchangeRateRepository repository) {
		this(null, repository);
	}

	public SqlApi(Api nextApi, ExchangeRateRepository repository) {
		super(nextApi);
		this.repository = repository;
	}
	
	public SqlApi(Api nextApi) {
		this(nextApi, new ExchangeRateRepositoryDb(
				new Configuration()
				.configure()
				.buildSessionFactory()));
	}
	
	public SqlApi() {
		this(null, new ExchangeRateRepositoryDb(
				new Configuration()
				.configure()
				.buildSessionFactory()));
	}

	@Override
	public String getRawData(LocalDate date, CurrencyCode code) {
		Optional<Rate> rate = repository.getRateByDateAndCode(date, code);
		return rate.map(Rate::toString).orElse("");
	}

	@Override
	public Optional<Rate> parseData(String rawData) {
		try {
			Rate rate = new ObjectMapper().readValue(rawData, Rate.class);
			return Optional.of(rate);
		} catch (JsonProcessingException e) {
			return Optional.empty();
		}
	}
	
	@Override
	void save(Rate rate) {
		repository.saveRate(rate);
	}

}
