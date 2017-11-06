package com.jpmorgan.technical;

import com.jpmorgan.technical.domain.Instruction;
import com.jpmorgan.technical.domain.InstructionType;
import com.jpmorgan.technical.domain.Settlement;
import com.jpmorgan.technical.processor.InstructionProcessor;
import com.jpmorgan.technical.processor.InstructionReader;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * This class class holds instruction reader tests.
 */
public class InstructionReaderTest {

    private static final String SAMPLE_INSTRUCTION = "Entity_1;B;0.50;SGP;01 Nov 2017;04 Nov 2017;200;100.25";
    private static final String SAMPLE_INSTRUCTION_FOR_AED = "Entity_2;S;0.80;AED;02 Nov 2017;03 Nov 2017;50;19";

    @Test
    public void readInstructions() {
        InstructionReader reader = new InstructionReader();
        // read test data
        Collection<Instruction> instructions = reader.readInstructions("testFile.txt");
        assertThat(instructions, Matchers.hasSize(1));
    }
    
    @Test
    public void createInstruction() {
        InstructionReader reader = new InstructionReader();
        // read test data
        Instruction instruction = reader.createInstruction(SAMPLE_INSTRUCTION);

        // verify instruction values.
        assertNotNull(instruction);
        assertThat(instruction.getEntityName(), is("Entity_1"));
        assertThat(instruction.getInstructionType(), is(InstructionType.B));
        assertThat(instruction.getAgreedFX(), is(Double.valueOf("0.50")));
    }
    
    @Test
    public void createSettlement() {
    	
    		InstructionReader reader = new InstructionReader();
        Instruction instruction = reader.createInstruction(SAMPLE_INSTRUCTION_FOR_AED);
        assertNotNull(instruction);
        
    		Collection<Instruction> instructionList = new ArrayList<>();
    		instructionList.add(instruction);
    		
    		InstructionProcessor processor = new InstructionProcessor();
    		Collection<Settlement> settlementList = processor.processInstructions(instructionList);
    		assertThat(settlementList, Matchers.hasSize(1));
    		
    		Settlement settlement = settlementList.iterator().next();    		
    		assertNotNull(settlement);
        assertThat(settlement.getEntityName(), is("Entity_2"));
        assertThat(settlement.getInstructionType(), is(InstructionType.S));
        assertThat(settlement.getDate(),is(reader.formatDate("05 Nov 2017")));
    }

}
