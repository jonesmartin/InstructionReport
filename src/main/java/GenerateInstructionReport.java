import utils.BuySellEnum;
import InstructionExceptions.BadlyFormedLineException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

public class GenerateInstructionReport {
    public static void main(String args[]){
        // Load instructions from file
        ArrayList<ExecutionInstruction> instructions = null;
        try {
            instructions = IngestInstructions.importFile("sample_instructions.csv");
        } catch (IOException | BadlyFormedLineException e) {
            e.printStackTrace();
            System.exit(1);
        }

        System.out.println("Incoming Settlement: ");
        instructions.stream()
                .filter(i -> i.getBuySell().equals(BuySellEnum.S))
                .collect(
                        Collectors.groupingBy(
                                ExecutionInstruction::getAdjustedSettlementDate,
                                Collectors.summingDouble(ExecutionInstruction::getAmount)
                        ))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(System.out::println);

        System.out.println("Outgoing Settlement: ");
        instructions.stream()
                .filter(i -> i.getBuySell().equals(BuySellEnum.B))
                .collect(
                        Collectors.groupingBy(
                                ExecutionInstruction::getAdjustedSettlementDate,
                                Collectors.summingDouble(ExecutionInstruction::getAmount)))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(System.out::println);

        // Output the Entity Ranking, for incoming and then outgoing showing total value
        System.out.println("Incoming Entity Ranking: ");
        instructions.stream()
                .filter(i -> i.getBuySell().equals(BuySellEnum.S))
                .collect(
                        Collectors.groupingBy(
                                ExecutionInstruction::getEntity,
                                Collectors.summingDouble(ExecutionInstruction::getAmount)))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .forEach(System.out::println);

        System.out.println("Outgoing Entity Ranking: ");
        instructions.stream()
                .filter(i -> i.getBuySell().equals(BuySellEnum.B))
                .collect(
                        Collectors.groupingBy(
                                ExecutionInstruction::getEntity,
                                Collectors.summingDouble(ExecutionInstruction::getAmount)))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .forEach(System.out::println);
    }
}