package instruction_processor;

import instruction_processor.exceptions.InstructionLoaderException;
import instruction_processor.utils.BuySellEnum;
import java.util.List;

public class ReportPublisher {

    public static void main(String args[]) {
        final String instructionsFile = "sample_instructions.csv";

        // Load instructions from file
        List<Instruction> instructions = null;
        InstructionLoader loader = new InstructionCSVFileLoader(instructionsFile, true);
        try {
            instructions = loader.loadInstructions();
        } catch (InstructionLoaderException e) {
            e.printStackTrace();
            System.exit(1);
        }

        AggregateInstructions aggregateInstructions = new AggregateInstructions();
        // Output incoming and outgoing settlement amounts, ordered by active settlement dates
        System.out.println("Incoming Settlement: ");
        aggregateInstructions.summariseByAdjustedSettlement(instructions, BuySellEnum.S).forEach(System.out::println);
        System.out.println("Outgoing Settlement: ");
        aggregateInstructions.summariseByAdjustedSettlement(instructions, BuySellEnum.B).forEach(System.out::println);

        // Output the Entity Ranking, for incoming and then outgoing showing total value
        System.out.println("Incoming Entity Ranking: ");
        aggregateInstructions.rankedSummaryByEntity(instructions, BuySellEnum.S).forEach(System.out::println);
        System.out.println("Outgoing Entity Ranking: ");
        aggregateInstructions.rankedSummaryByEntity(instructions, BuySellEnum.B).forEach(System.out::println);
    }
}