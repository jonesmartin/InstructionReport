package instruction_processor;

import instruction_processor.exceptions.MalformedCurrencyException;
import instruction_processor.utils.BuySellEnum;
import instruction_processor.utils.DateUtils;
import org.junit.Test;
import static org.junit.Assert.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


public class AggregateInstructionsTest {

    @Test
    public void validateCalculation() throws MalformedCurrencyException {
        List<Instruction> instructions = new LinkedList<>();
        LocalDate sd1 = DateUtils.parseDate("12 Sep 2017");
        LocalDate sd2 = DateUtils.parseDate("13 Sep 2017");
        LocalDate sd3 = DateUtils.parseDate("14 Sep 2017");
        instructions.add(new Instruction("Fish", BuySellEnum.B, 2,
                new Currency("GBP"), sd1, sd1, 10, 50));
        // Different date
        instructions.add(new Instruction("James", BuySellEnum.B, 2,
                new Currency("GBP"), sd2, sd2, 10, 50));
        // Same date, should show addition of amounts
        instructions.add(new Instruction("Frank", BuySellEnum.B, 2,
                new Currency("GBP"), sd2, sd2, 10, 50.5));
        // Same entity and date, different buy/sell
        instructions.add(new Instruction("Frank", BuySellEnum.S, 2,
                new Currency("GBP"), sd2, sd2, 10, 50.5));

        ArrayList<Map.Entry<LocalDate, Double>> outgoing =
                new AggregateInstructions().summariseByAdjustedSettlement(instructions, BuySellEnum.B)
                        .stream().collect(Collectors.toCollection(ArrayList<Map.Entry<LocalDate, Double>>::new));
        ArrayList<Map.Entry<LocalDate, Double>> incoming =
                new AggregateInstructions().summariseByAdjustedSettlement(instructions, BuySellEnum.S)
                        .stream().collect(Collectors.toCollection(ArrayList<Map.Entry<LocalDate, Double>>::new));

        assertEquals(outgoing.get(0).getValue(), new Double(1000));
        assertEquals(outgoing.get(1).getValue(), new Double(2010));
        assertEquals(incoming.get(0).getValue(), new Double(1010));
    }

    @Test
    public void validateRanking() throws MalformedCurrencyException {
        List<Instruction> instructions = new LinkedList<>();
        LocalDate sd1 = DateUtils.parseDate("12 Sep 2017");
        LocalDate sd2 = DateUtils.parseDate("13 Sep 2017");
        LocalDate sd3 = DateUtils.parseDate("14 Sep 2017");
        instructions.add(new Instruction("Fish", BuySellEnum.B, 2,
                new Currency("GBP"), sd1, sd1, 10, 50));
        // Different date
        instructions.add(new Instruction("James", BuySellEnum.B, 2,
                new Currency("GBP"), sd2, sd2, 10, 50.1));
        // Same date, should show addition of amounts
        instructions.add(new Instruction("Frank", BuySellEnum.B, 2,
                new Currency("GBP"), sd2, sd2, 10, 50.5));
        // Same entity and date, different buy/sell
        instructions.add(new Instruction("Frank", BuySellEnum.S, 2,
                new Currency("GBP"), sd2, sd2, 10, 45.4));
        // Different entity and date, smaller amounts, more instructions
        instructions.add(new Instruction("Steve", BuySellEnum.S, 2,
                new Currency("GBP"), sd1, sd1, 10, 3));
        instructions.add(new Instruction("Steve", BuySellEnum.S, 2.5,
                new Currency("GBP"), sd2, sd2, 10, 3));
        instructions.add(new Instruction("Steve", BuySellEnum.S, 2.8,
                new Currency("GBP"), sd3, sd3, 10, 3));

        ArrayList<Map.Entry<String, Double>> outgoing =
                new AggregateInstructions().rankedSummaryByEntity(instructions, BuySellEnum.B)
                        .stream().collect(Collectors.toCollection(ArrayList<Map.Entry<String, Double>>::new));
        ArrayList<Map.Entry<String, Double>> incoming =
                new AggregateInstructions().rankedSummaryByEntity(instructions, BuySellEnum.S)
                        .stream().collect(Collectors.toCollection(ArrayList<Map.Entry<String, Double>>::new));

        assertEquals(outgoing.get(0).getKey(), "Frank");
        assertEquals(outgoing.get(0).getValue(), new Double(1010.0));
        assertEquals(outgoing.get(1).getKey(), "James");
        assertEquals(outgoing.get(1).getValue(), new Double(1002.0));
        assertEquals(incoming.get(0).getKey(), "Frank");
        assertEquals(incoming.get(0).getValue(), new Double(908.0));
        assertEquals(incoming.get(1).getKey(), "Steve");
        assertEquals(incoming.get(1).getValue(), new Double(219.0));
    }
}
