package org.example.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

/**
 * The type Math operation service.
 */
public class MathOperationService {

    private static final Logger LOGGER = LogManager.getLogger(MathOperationService.class);

    /**
     * Process all string instructions by applying their instruction operator and number value.
     *
     * @param path the path to file
     * @return the double - summary of all mathematical operations
     */
    public Double processAllInstructions(String path) {

        ArrayList<String> instructions = new ArrayList<>(InputService.readFile(path));
        Double base = convertStringToDouble(instructions.get(instructions.size() - 1));
        instructions.remove(instructions.size() - 1);

        for (String instruction : instructions) {
            base = processInstruction(base, instruction);
        }

        return base;
    }

    /**
     * Process single instruction and applies operator with its number to base value.
     *
     * @param base        the base value
     * @param instruction the instruction contains operator type and value
     * @return the double
     */
    protected Double processInstruction(Double base, String instruction){

        String operation = instruction.split(" ", 2)[0];
        Double number = convertStringToDouble(instruction);

        if (number == null || base == null){
            LOGGER.error("Error occured while processing instruction");
            return null;
        }

        switch(operation) {
            case "add":
                base += number;
                break;
            case "substract":
                base -= number;
                break;
            case "multiply":
                base *= number;
                break;
            case "divide":
                base /= number;
                break;
            default:
                LOGGER.error("Instruction was not recognized");
                return null;
        }

        return base;
    }

    /**
     * Convert string instruction to double and check if instruction has 2 values
     *
     * @param instruction     instruction that contains operator and value
     * @return the double
     */
    private Double convertStringToDouble(String instruction){

        if(instruction.split(" ", 2).length != 2){
            LOGGER.error("Instruction is in wrong format");
            return null;
        }

        try {
            return Double.parseDouble(instruction.split(" ", 2)[1]);
        } catch(NumberFormatException e) {
            LOGGER.error("Error occured while converting string to double");
            throw new NumberFormatException();
        }
    }
}
