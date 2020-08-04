package my.calculator;

/**
 * 
 * @author Simon Talbi
 */

class ExprConstant implements Expr {
    

    private final int value;

    ExprConstant(int value) {
        this.value = value;
    }

    @Override
    public int value(Environnement env) {
        return value;
    }

    @Override
    public String description(Environnement env) {
        return Integer.toString(value(env));
    }
    
    
}
