package demo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import api.CurrencyCode;
import test.NbpApi;
import test.SaleDocumentService;

public class Main {
	public static void main(String[] args) {
		SaleDocumentService saleDocumentService = new test.SaleDocumentService();
		saleDocumentService.insert();
	}
}