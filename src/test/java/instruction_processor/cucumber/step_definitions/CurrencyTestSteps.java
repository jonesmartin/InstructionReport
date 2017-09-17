package instruction_processor.cucumber.step_definitions;

import cucumber.api.java8.En;
import instruction_processor.Currency;
import instruction_processor.exceptions.MalformedCurrencyException;
import java.time.LocalDate;
import static instruction_processor.utils.DateUtils.parseDate;
import static org.junit.Assert.*;

public class CurrencyTestSteps implements En {

    private Currency testCurrency;
    private LocalDate nextWorkingDate;

    public CurrencyTestSteps() {
        Given("^I use the ([A-Z]{3})$", (String currency) -> {
            try {
                testCurrency = new Currency(currency);
            } catch (MalformedCurrencyException e) {
                e.printStackTrace();
            }
            System.out.println("Currency: " + currency);
        });
        When("^The settlement date is \"([0-9]{2} [a-zA-Z]{3} [0-9]{4})\"$", (String settlementDate) -> {
            nextWorkingDate = testCurrency.nextWorkDay(parseDate(settlementDate));
            System.out.println("SettlementDate: " + settlementDate);
            System.out.println("nextWorkingDate: " + nextWorkingDate);
        });
        Then("^The adjusted settlement date should be \"([0-9]{2} [a-zA-Z]{3} [0-9]{4})\"$", (String adjustedDate) -> {
            System.out.println("AdjustedDate: " + adjustedDate);
            assertEquals(parseDate(adjustedDate), nextWorkingDate);

        });
    }
}
