package my.calculator;

/**
 *
 * @author Simon Talbi
 */
public interface Expr {
    /**
     * Evaluate the expression in an environment.
     * @param env
     * @return expression's value
     */
    int value(Environnement env);
    
    // "factories" pour fabriquer des expression.
    
    /**
     * Return Return the int put in parameters as an expression.
     * @param value
     * @return an ExprConstante
     */
    public static Expr constant(int value) {
        return new ExprConstant(value);
    }
    
    /**
     * Return a binary expression composed of 2 expressions and an operator.
     * @param op operator
     * @return an ExprBinaire
     */
    public static Expr binary(Expr left, OpBinary op, Expr right) {
        return new ExprBinary(left, op, right);
    }
    
    /**
     * Return the variable in parameters as an expression.
     * @param variable
     * @return an ExprVariable
     */
    public static Expr variable(String variable) {
        return new ExprVariable(variable);
    }
    
    /**
     * Return the variable and the expression in parameters as an affectation expression.
     * @param variable
     * @param valeur
     * @return an ExprAffectation
     */
    public static Expr affectation(String variable, Expr value) {
        return new ExprAffectation(variable, value);
    }

    /**
     * Return the expression as a string.
     * @param env the environment of the expression
     * @return a string
     */
    public String description(Environnement env);
}
