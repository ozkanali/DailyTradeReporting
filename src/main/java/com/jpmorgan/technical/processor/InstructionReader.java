package com.jpmorgan.technical.processor;

import com.jpmorgan.technical.domain.Instruction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

/**
 * This class processes the given file and converts the file a collection of {@link Instruction} objects.
 */
public class InstructionReader {

    private static final String DATE_FORMAT = "dd MMM yyyy";
    private static Logger logger = Logger.getLogger(InstructionReader.class.getName());

    /**
     * This class handles the instructions in the given file.
     *
     * @param fileName the name of the source file to read.
     * @return a list of instructions.
     */
    public Collection<Instruction> readInstructions(String fileName) {
        logger.info("Reading instructions from:" + fileName);
        Collection<Instruction> instructions = new ArrayList<>();
        try (Scanner scan = new Scanner(getClass().getClassLoader().getResourceAsStream(fileName))) {
            while (scan.hasNextLine()) {
                Instruction instruction = createInstruction(scan.nextLine());
                instructions.add(instruction);
            }
        }
        return instructions;
    }

    /**
     * Handles each line of the file and creates necessary objects for reports.
     *
     * @param dataLine the line to be processed.
     * @return the {@link Instruction} object.
     */
    public Instruction createInstruction(String dataLine) {
        StringTokenizer tokenizer = new StringTokenizer(dataLine, ";");
        Instruction.InstructionBuilder builder = new Instruction.InstructionBuilder();
        while (tokenizer.hasMoreTokens()) {
            builder.entityName(tokenizer.nextElement().toString());
            builder.instructionType(tokenizer.nextElement().toString());
            builder.agreedFX(Double.valueOf(tokenizer.nextElement().toString()));
            builder.currency(tokenizer.nextElement().toString());
            builder.instructionDate(formatDate(tokenizer.nextElement().toString()));
            builder.settlementDate(formatDate(tokenizer.nextElement().toString()));
            builder.numberOfUnits(Integer.valueOf(tokenizer.nextElement().toString()));
            builder.pricePerUnit(Double.valueOf(tokenizer.nextElement().toString()));
        }
        return builder.build();
    }

    /**
     * Formats the given date value to the
     *
     * @param dateString date string to format.
     * @return formatted {@link Date}.
     */
    public Date formatDate(String dateString) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT, Locale.US);
            return format.parse(dateString);
        } catch (ParseException e) {
            logger.warning("Date formatting exception. Details: " + e.getMessage());
        }
        return null;
    }
}
