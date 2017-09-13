import utils.BuySellEnum;
import InstructionExceptions.MalformedCurrencyException;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;

public class ExecutionInstructionTest {

    @Test
    public void validateAdjustedSettlementDate() throws MalformedCurrencyException {
        ExecutionInstruction executionInstruction =
                new ExecutionInstruction("Martin", BuySellEnum.B, 1.45, new Currency("GBP"),
                        LocalDate.parse("13 Sep 2017", DateTimeFormatter.ofPattern("dd MMM yyyy")),
                        LocalDate.parse("16 Sep 2017", DateTimeFormatter.ofPattern("dd MMM yyyy")),
                        10,2.67);
        assertEquals(executionInstruction.getSettlementDate().getDayOfWeek(), DayOfWeek.SATURDAY);
        assertEquals(executionInstruction.getAdjustedSettlementDate().getDayOfWeek(), DayOfWeek.MONDAY);
    }
}
