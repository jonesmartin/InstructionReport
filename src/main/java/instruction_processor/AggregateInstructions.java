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

    /** Return aggregation of the instructions grouped by adjusted settlement date,
     *  and summing the total amount of instructions for each date
     *
     * @param instructions list of instructions
     * @param buySell only aggregate over buy or sell instructions
     * @return ordered set (by date ascending) containing map entries with LocalDate the key and
     *         summed amount the value
     */
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

    /** Return aggregation of the instructions grouped by entity showing the summed total amount
     *
     * @param instructions list of instructions
     * @param buySell only aggregate over buy or sell instructions
     * @return ordered set (by summed amount) containing map entries with Entity the key and summed
     *         anount the value
     */
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
