package my.calculator;

/**
 *
 * @author Simon Talbi
 */
public class ExprVariable implements Expr {

    private String variable;
    
    
    public ExprVariable(String variable) {
        this.variable = variable;
    }

    @Override
    public int value(Environnement env) {
        return env.value(variable);
    }

    @Override
    public String description(Environnement env) {
        return Integer.toString(value(env));
    }
}
