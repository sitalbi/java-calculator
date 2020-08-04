package my.calculator;

import my.calculator.*;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author simon
 */
public class TestCalculatrice {

    @Test
    public void testSums() throws EvaluationErrorException, SyntaxErrorException {
        Environnement env = new Environnement();
        Calculator calc = new Calculator(env);
        assertEquals(123, calc.evaluation("  123").valeur(env));
        assertEquals(4, calc.evaluation("2+2").valeur(env));
        assertEquals(1, calc.evaluation("421-20-400").valeur(env));
    }

    @Test
    public void testDivisionZero() throws SyntaxErrorException {
        Environnement env = new Environnement();
        Calculator calc = new Calculator(env);
        try {
            calc.evaluation("1 + 2/0 + 3");
            fail();
        } catch (EvaluationErrorException ex) {
            System.out.println("Erreur, division par 0");
        }
    }

    @Test
    public void testAssignments() throws EvaluationErrorException, SyntaxErrorException {
        Environnement env = new Environnement();
        Calculator c = new Calculator(env);
        assertEquals(12, c.evaluation("num = 3*4").valeur(env));
        assertEquals(12, c.evaluation("num").valeur(env));
        assertEquals(2, c.evaluation("den = 2").valeur(env));
        assertEquals(2, c.evaluation("den").valeur(env));
        assertEquals(6, c.evaluation("num / den").valeur(env));

        assertEquals(10, c.evaluation("(a = 2+1) + (b = 2*3 + 1)").valeur(env));
        assertEquals(3, c.evaluation("a").valeur(env));
        assertEquals(7, c.evaluation("b").valeur(env));
    }
}
