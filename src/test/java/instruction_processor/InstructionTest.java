package instruction_processor;

import instruction_processor.utils.BuySellEnum;
import instruction_processor.exceptions.MalformedCurrencyException;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;

public class InstructionTest {

    @Test
    public void validateAdjustedSettlementDate() throws MalformedCurrencyException {
        // check the date moves when it's a non-workday
        Instruction instruction =
                new Instruction("Martin", BuySellEnum.B, 1.45, new Currency("GBP"),
                        LocalDate.parse("13 Sep 2017", DateTimeFormatter.ofPattern("dd MMM yyyy")),
                        LocalDate.parse("16 Sep 2017", DateTimeFormatter.ofPattern("dd MMM yyyy")),
                        10,2.67);
        assertEquals(instruction.getSettlementDate().getDayOfWeek(), DayOfWeek.SATURDAY);
        assertEquals(instruction.getAdjustedSettlementDate().getDayOfWeek(), DayOfWeek.MONDAY);

        // check it doesn't move when it shouldn't
        instruction =
                new Instruction("Martin", BuySellEnum.B, 1.45, new Currency("GBP"),
                        LocalDate.parse("13 Sep 2017", DateTimeFormatter.ofPattern("dd MMM yyyy")),
                        LocalDate.parse("13 Sep 2017", DateTimeFormatter.ofPattern("dd MMM yyyy")),
                        10,2.67);
        assertEquals(instruction.getSettlementDate().getDayOfWeek(), DayOfWeek.WEDNESDAY);
        assertEquals(instruction.getAdjustedSettlementDate().getDayOfWeek(), DayOfWeek.WEDNESDAY);
    }
}
