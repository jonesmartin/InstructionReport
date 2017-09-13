package InstructionProcesser;

import InstructionProcesser.utils.*;
import InstructionProcesser.InstructionExceptions.BadlyFormedLineException;
import InstructionProcesser.InstructionExceptions.MalformedCurrencyException;
import java.io.*;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;
import java.util.List;

import static InstructionProcesser.utils.DateUtils.parseDate;


public class Consumer {

    public static List<Instruction> importFile(String instructionsFile) throws BadlyFormedLineException, IOException {
        InputStream instFileStream = ClassLoader.getSystemResourceAsStream(instructionsFile);
        if (instFileStream == null) throw new IOException("Cannot find file: " + instructionsFile);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(instFileStream));

        String line;
        List<Instruction> instructions = new LinkedList<>();
        bufferedReader.readLine();
        while ((line = bufferedReader.readLine()) != null) {
            instructions.add(csvToExecutionInstruction(line));
        }
        return instructions;
    }

    private static Instruction csvToExecutionInstruction(String csvLine) throws BadlyFormedLineException {
        Instruction instruction;

        if (csvLine == null || csvLine.trim().length() == 0) {
            throw new BadlyFormedLineException();
        }
        String[] splitData = csvLine.split("\\s*,\\s*");
        //TODO Check if we have the correct number of fields in the line?
        try {
            instruction = new Instruction(
                    splitData[0],
                    BuySellEnum.valueOf(splitData[1]),
                    Double.valueOf(splitData[2]),
                    new Currency(splitData[3]),
                    parseDate(splitData[4]),
                    parseDate(splitData[5]),
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
