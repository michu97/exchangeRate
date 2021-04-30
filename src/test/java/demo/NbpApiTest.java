package demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import api.CurrencyCode;
import api.NbpStrategy;

public class NbpApiTest {
private api.Api api;
	
	@Before
	public void setup() {
		api = new api.ExchangeRateApi(new NbpStrategy());
	}
	
	@Test
	public void Should_Return_Empty_Optional_When_Date_Is_Before_2002_01_02() {
		//given
		Optional<BigDecimal> expected = Optional.empty();
		
		//when
		Optional<BigDecimal> actual = 
				api.getAmountInPLN(LocalDate.of(2001, 1, 1), new BigDecimal(10), CurrencyCode.USD);
		
		//then
		assertThat(actual).isEqualTo(expected);
	}
	
	@Test
	public void Should_Return_Rate_Of_Today_When_Date_Is_After() {
		//when
		Optional<BigDecimal> actual = api.getAmountInPLN(LocalDate.now().plusDays(1), new BigDecimal(10), CurrencyCode.EUR);
		Optional<BigDecimal> expected = api.getAmountInPLN(new BigDecimal(10), CurrencyCode.EUR);
		
		//then
		assertThat(actual.get()).isEqualTo(expected.get());
	}
	
	@Test
	public void Should_Return_Conversion() {
		//given
		BigDecimal expected = new BigDecimal("5.3406").multiply(new BigDecimal("178.4500"));
		
		//when
		Optional<BigDecimal> actual = api.getAmountInPLN(LocalDate.of(2021, 4, 7), new BigDecimal("178.4500"), CurrencyCode.GBP);
		
		//then
		assertThat(actual.get()).isEqualTo(expected);
	}
	
	@Test
	public void Should_Return_Rate_Before_Weekend() {
		//given
		BigDecimal expected = new BigDecimal("5.2393").multiply(new BigDecimal(25));
		
		//when
		Optional<BigDecimal> actual = api.getAmountInPLN(LocalDate.of(2021, 4, 10), new BigDecimal(25), CurrencyCode.GBP);
		
		//then
		assertThat(actual.get()).isEqualTo(expected);
	}
	
	@Test
	public void Should_Return_Rate_For_Several_Currency_For_2021_04_15() {
		//given
		LocalDate date = LocalDate.of(2021, 4, 15);
		BigDecimal ammount = new BigDecimal("350");
		
		BigDecimal expectedGBP = new BigDecimal("5.2376").multiply(ammount);
		BigDecimal expectedEUR = new BigDecimal("4.5546").multiply(ammount);
		BigDecimal expectedUSD = new BigDecimal("3.8014").multiply(ammount);
		BigDecimal expectedCZK = new BigDecimal("0.1755").multiply(ammount);
		BigDecimal expectedPHP = new BigDecimal("0.0785").multiply(ammount);
		BigDecimal expectedXDR = new BigDecimal("5.4360").multiply(ammount);
		
		//when
		Optional<BigDecimal> actualGBP = api.getAmountInPLN(date, ammount, CurrencyCode.GBP);
		Optional<BigDecimal> actualEUR = api.getAmountInPLN(date, ammount, CurrencyCode.EUR);
		Optional<BigDecimal> actualUSD = api.getAmountInPLN(date, ammount, CurrencyCode.USD);
		Optional<BigDecimal> actualCZK = api.getAmountInPLN(date, ammount, CurrencyCode.CZK);
		Optional<BigDecimal> actualPHP = api.getAmountInPLN(date, ammount, CurrencyCode.PHP);
		Optional<BigDecimal> actualXDR = api.getAmountInPLN(date, ammount, CurrencyCode.XDR);
		
		//then
		assertThat(actualGBP.get()).isEqualTo(expectedGBP);
		assertThat(actualEUR.get()).isEqualTo(expectedEUR);
		assertThat(actualUSD.get()).isEqualTo(expectedUSD);
		assertThat(actualCZK.get()).isEqualTo(expectedCZK);
		assertThat(actualPHP.get()).isEqualTo(expectedPHP);
		assertThat(actualXDR.get()).isEqualTo(expectedXDR);
	}
}
