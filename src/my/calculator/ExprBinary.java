package my.calculator;

/**
 *
 * @author Simon Talbi
 */
class ExprBinary implements Expr {

    private final  OpBinary op;
    private final Expr gauche;
    private final Expr droite;

    public ExprBinary( Expr gauche, OpBinary op, Expr droite) {
        this.op = op;
        this.gauche = gauche;
        this.droite = droite;
    }

    @Override
    public int value(Environnement env) {
         return op.appliquer(gauche.value(env), droite.value(env));
    }
    
    @Override
    public String description(Environnement env) {
        return Integer.toString(value(env));
    }
    
}
