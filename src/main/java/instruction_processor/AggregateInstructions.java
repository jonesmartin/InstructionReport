package instruction_processor;

import instruction_processor.utils.BuySellEnum;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

public class AggregateInstructions {

    public Set<Entry<LocalDate, Double>> summariseByAdjustedSettlement(List<Instruction> instructions,
                                                                              BuySellEnum buySell) {
        return instructions.stream()
                .filter(i -> i.getBuySell().equals(buySell))
                .collect(
                        Collectors.groupingBy(
                                Instruction::getAdjustedSettlementDate,
                                Collectors.summingDouble(Instruction::getAmount)))
                .entrySet()
                .stream()
                .sorted(Entry.comparingByKey())
                .collect(Collectors.toCollection(LinkedHashSet<Entry<LocalDate, Double>>::new));
    }


    public Set<Entry<String, Double>> rankedSummaryByEntity(List<Instruction> instructions,
                                                                              BuySellEnum buySell) {
        return instructions.stream()
                .filter(i -> i.getBuySell().equals(buySell))
                .collect(
                        Collectors.groupingBy(
                                Instruction::getEntity,
                                Collectors.summingDouble(Instruction::getAmount)))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .collect(Collectors.toCollection(LinkedHashSet<Entry<String, Double>>::new));
    }

}
