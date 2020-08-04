package calculatorproject;

import java.util.Scanner;
import my.calculator.*;

/**
 *
 * @author Simon Talbi
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Environnement env = new Environnement();
        System.out.println("Calculator");
        while (true) {
            Calculator calculator = new Calculator(env);
            System.out.print("> ");
            if (!in.hasNextLine()) {
                break;
            }
            String line = in.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }
            if (line.equals("\\q")) {
                break;
            }
            try {
                Expr value = calculator.evaluation(line);
                System.out.format("= %d\n", value.value(env));
            } catch (SyntaxErrorException ex) {
                System.out.format("! Incorrect syntax %s\n",
                        ex.getMessage());
            } catch (EvaluationErrorException ex) {
                System.out.format("! Evaluation failed %s\n",
                        ex.getMessage());
            }
        }
        System.out.println("Bye.");
    }
}
