package InstructionProessor;

import InstructionProcesser.Currency;
import InstructionProcesser.Instruction;
import InstructionProcesser.utils.BuySellEnum;
import InstructionProcesser.InstructionExceptions.MalformedCurrencyException;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;

public class InstructionTest {

    @Test
    public void validateAdjustedSettlementDate() throws MalformedCurrencyException {
        Instruction instruction =
                new Instruction("Martin", BuySellEnum.B, 1.45, new Currency("GBP"),
                        LocalDate.parse("13 Sep 2017", DateTimeFormatter.ofPattern("dd MMM yyyy")),
                        LocalDate.parse("16 Sep 2017", DateTimeFormatter.ofPattern("dd MMM yyyy")),
                        10,2.67);
        assertEquals(instruction.getSettlementDate().getDayOfWeek(), DayOfWeek.SATURDAY);
        assertEquals(instruction.getAdjustedSettlementDate().getDayOfWeek(), DayOfWeek.MONDAY);
    }
}
