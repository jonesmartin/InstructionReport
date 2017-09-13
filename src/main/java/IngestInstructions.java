import utils.*;
import InstructionExceptions.BadlyFormedLineException;
import InstructionExceptions.MalformedCurrencyException;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class IngestInstructions {

    public static ArrayList<ExecutionInstruction> importFile(String instructionsFile) throws BadlyFormedLineException, IOException {
        InputStream instFileStream = ClassLoader.getSystemResourceAsStream(instructionsFile);
        if (instFileStream == null) throw new IOException("Cannot find file: " + instructionsFile);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(instFileStream));

        String line;
        ArrayList<ExecutionInstruction> instructions = new ArrayList<>();

        bufferedReader.readLine();
        while ((line = bufferedReader.readLine()) != null) {
            instructions.add(csvtoExecutionInstruction(line));
        }
        return instructions;
    }

    private static ExecutionInstruction csvtoExecutionInstruction(String csvLine) throws BadlyFormedLineException {
        ExecutionInstruction instruction;

        if (csvLine == null || csvLine.trim().length() == 0) {
            throw new BadlyFormedLineException();
        }
        String[] splitData = csvLine.split("\\s*,\\s*");
        try {
            instruction = new ExecutionInstruction(
                    splitData[0],
                    BuySellEnum.valueOf(splitData[1]),
                    Double.valueOf(splitData[2]),
                    new Currency(splitData[3]),
                    LocalDate.parse(splitData[4], DateTimeFormatter.ofPattern("dd MMM yyyy")),
                    LocalDate.parse(splitData[5], DateTimeFormatter.ofPattern("dd MMM yyyy")),
                    Integer.valueOf(splitData[6]),
                    Double.valueOf(splitData[7]));
        } catch (DateTimeParseException dtpe) {
            System.out.println("Failed to parse line due to incorrect date format, should be dd MMM yyyy");
            throw new BadlyFormedLineException();
        } catch (MalformedCurrencyException mce) {
            System.out.println("Failed to parse line due to currency format issue");
            throw new BadlyFormedLineException();
        } catch (IllegalArgumentException iae) {
            System.out.println("Failed to parse line due to Enum not matching B/S");
            throw new BadlyFormedLineException();
        }
        return instruction;
    }

}
