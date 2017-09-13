package InstructionProcesser;

import InstructionProcesser.utils.BuySellEnum;
import InstructionProcesser.InstructionExceptions.BadlyFormedLineException;
import java.io.IOException;
import java.util.List;

public class ReportPublisher {

    private static final String instructionsFile = "sample_instructions.csv";

    public static void main(String args[]) {
        // Load instructions from file
        List<Instruction> instructions = null;
        try {
            instructions = Consumer.importFile(instructionsFile);
        } catch (IOException | BadlyFormedLineException e) {
            e.printStackTrace();
            System.exit(1);
        }

        // Output incoming and outgoing settlement amounts, ordered by active settlement dates
        System.out.println("Incoming Settlement: ");
        AggregateInstructions.summariseByAdjustedSettlement(instructions, BuySellEnum.S).forEach(System.out::println);
        System.out.println("Outgoing Settlement: ");
        AggregateInstructions.summariseByAdjustedSettlement(instructions, BuySellEnum.B).forEach(System.out::println);

        // Output the Entity Ranking, for incoming and then outgoing showing total value
        System.out.println("Incoming Entity Ranking: ");
        AggregateInstructions.rankedSummaryByEntity(instructions, BuySellEnum.S).forEach(System.out::println);
        System.out.println("Outgoing Entity Ranking: ");
        AggregateInstructions.rankedSummaryByEntity(instructions, BuySellEnum.B).forEach(System.out::println);
    }
}