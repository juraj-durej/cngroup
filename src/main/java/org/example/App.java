package org.example;

import org.example.services.MathOperationService;

/**
 * Hello world!
 */
public class App
{
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main( String[] args )
    {
        MathOperationService mathOperationService = new MathOperationService();
        Double solution = mathOperationService.processAllInstructions("src/main/resources/input.txt");
        System.out.println(solution);
    }
}
