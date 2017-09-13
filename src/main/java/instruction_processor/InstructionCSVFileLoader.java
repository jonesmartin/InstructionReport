package instruction_processor;

import instruction_processor.exceptions.InstructionLoaderException;
import instruction_processor.utils.*;
import instruction_processor.exceptions.BadlyFormedLineException;
import instruction_processor.exceptions.MalformedCurrencyException;
import java.io.*;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;
import java.util.List;

import static instruction_processor.utils.DateUtils.parseDate;


public class InstructionCSVFileLoader implements InstructionLoader {

    private final int expectedNumFields = 8;
    private String instructionsFile;
    private boolean hasHeader;

    public InstructionCSVFileLoader(String instructionsFile, boolean hasHeader) {
        this.instructionsFile = instructionsFile;
        this.hasHeader = hasHeader;
    }

    /** Returns a list of Instructions loaded from CSV file
     *
     * @return Unordered List of Instructions
     * @throws InstructionLoaderException if there is a problem with the file or data in the file being loaded
     */
    public List<Instruction> loadInstructions()  throws InstructionLoaderException {
        InputStream instFileStream = ClassLoader.getSystemResourceAsStream(instructionsFile);
        List<Instruction> instructions = new LinkedList<>();
        String line;

        try {
            if (instFileStream == null) throw new IOException("Cannot find file: " + instructionsFile);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(instFileStream));
            if (hasHeader) bufferedReader.readLine();  // consume the header row if there is one
            while ((line = bufferedReader.readLine()) != null) {
                instructions.add(csvToExecutionInstruction(line));
            }
        } catch (IOException | BadlyFormedLineException e) {
            throw new InstructionLoaderException(e);
        }
        return instructions;
    }

    /** Processes a CSV line and returns an Instruction if parsed successfully
     *
     * @param csvLine csv line with exactly expectedNumFields in order
     *                entity, buySell, agreedFX, currency, instructionDate, settlementDate, units, unitPrice
     * @return single Instruction representing the data in the line
     * @throws BadlyFormedLineException if there is a problem with the format/contents of the line
     */
    private Instruction csvToExecutionInstruction(String csvLine) throws BadlyFormedLineException {
        Instruction instruction;

        if (csvLine == null || csvLine.trim().length() == 0) throw new BadlyFormedLineException();

        String[] splitData = csvLine.split("\\s*,\\s*");
        if (splitData.length != expectedNumFields) throw new BadlyFormedLineException();

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
