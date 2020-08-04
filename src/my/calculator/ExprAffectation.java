package my.calculator;

/**
 *
 * @author Simon Talbi
 */
public class ExprAffectation implements Expr {

    private String variable;
    private Expr expr;
    
    public ExprAffectation(String variable, Expr expr) {
        this.variable = variable;
        this.expr = expr;
    }

    @Override
    public int value(Environnement env) {
        int res =  expr.value(env);
        env.affect(variable,res);
        return res;
    }

    @Override
    public String description(Environnement env) {
        return Integer.toString(value(env));
    }
}
