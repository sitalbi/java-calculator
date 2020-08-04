package my.calculator;

/**
 *
 * @author Simon Talbi
 */
public class EvaluationErrorException extends Exception{
    public EvaluationErrorException(String msgErreur) {
        super(msgErreur);
    }
}
