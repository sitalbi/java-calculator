package my.calculator;

/**
 *
 * @author Simon Talbi
 */
public class SyntaxErrorException extends Exception{
    public SyntaxErrorException(String msgErreur) {
        super(msgErreur);
    }
}
