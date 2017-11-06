package com.jpmorgan.technical;

import com.jpmorgan.technical.domain.Instruction;
import com.jpmorgan.technical.domain.Report;
import com.jpmorgan.technical.domain.Settlement;
import com.jpmorgan.technical.processor.InstructionProcessor;
import com.jpmorgan.technical.processor.InstructionReader;

import java.util.Collection;
import java.util.logging.Logger;

/**
 * This class represents the daily report application.
 */
public class TradeReportApplication {

    private static Logger logger = Logger.getLogger(TradeReportApplication.class.getName());

    public static void main(String args[]) throws Exception {
        try {
            InstructionReader instructionReader = new InstructionReader();
            Collection<Instruction> instructions = instructionReader.readInstructions("trading.txt");
            logger.info("Instructions are loaded successfully.");
            InstructionProcessor processor = new InstructionProcessor();
            Collection<Settlement> settlements = processor.processInstructions(instructions);
            logger.info("Settlements are calculated successfully.");
            Report tradeReport = new Report();
            settlements.forEach(tradeReport::addSettlement);
            logger.info("Instruction report is created successfully.");
            System.out.println(tradeReport);
            logger.info("Instruction report is printed to the screen.");
        } catch (Exception e) {
            String errorMsg = "Report process failed! Please check the input file.";
            logger.severe(errorMsg);
            throw new RuntimeException(errorMsg, e);
        }
    }
}
