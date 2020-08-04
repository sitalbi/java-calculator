package my.calculator;

/**
 *
 * @author Simon Talbi
 */
public class Calculator {

    private Tokenizer tokenizer;
    private Token token;
    private Environnement env = new Environnement();

    public Calculator(Environnement env) {
        this.env = env;
    }
    
    /**
     * Evaluate a string in parameters and return an expression of this string
     * @param line
     * @return the expression of the line
     * @throws EvaluationErrorException
     * @throws SyntaxErrorException 
     */
    public Expr evaluation(String line) throws EvaluationErrorException, SyntaxErrorException {
        tokenizer = new Tokenizer(line);
        token = tokenizer.get();

        Expr value = treeExpr();
        checkSyntax(token.isEnd(),
                String.format("End of expression expected, %s found", token));
        
        return value;
    }

    private int get_number_value() throws EvaluationErrorException, SyntaxErrorException {
        checkSyntax(token.isNumber(), "Number expected");
        int value = token.value();
        token = tokenizer.get();
        return value;
    }

    private Expr treeExpr() throws EvaluationErrorException, SyntaxErrorException {
        Expr expr = treeTerm();

        while (token.isSymbol() && !token.isEnd()) {
            switch (token.string) {
                case "+":
                    token = tokenizer.get();
                    Expr tmp = treeTerm();
                    expr = Expr.binary(expr, OpBinary.PLUS, tmp);
                    break;
                case "-":
                    token = tokenizer.get();
                    Expr tmp2 = treeTerm();
                    expr = Expr.binary(expr, OpBinary.MOINS, tmp2);
                    break;
            }

        }
        return expr;
    }

    private Expr treeTerm() throws SyntaxErrorException, EvaluationErrorException {
        Expr expr = treeFactor();

        while (token.isPrioritySymbol() && !token.isEnd()) {
            switch (token.string) {
                case "/":
                    token = tokenizer.get();
                    if (token.isNumber() && token.value() == 0) {
                        throw new EvaluationErrorException("Division by 0");
                    }
                    Expr tmp = treeTerm();
                    expr = Expr.binary(expr, OpBinary.DIVISER, tmp);
                    break;
                case "*":
                    token = tokenizer.get();
                    Expr tmp2 = treeTerm();
                    expr = Expr.binary(expr, OpBinary.FOIS, tmp2);
                    break;
            }
        }
        return expr;
    }

    private Expr treeFactor() throws SyntaxErrorException, EvaluationErrorException {
        Expr expr = Expr.constant(0);
        if (token.isNumber()) {
            expr = Expr.binary(expr, OpBinary.PLUS, Expr.constant(get_number_value()));
        } else if (token.isWord()) {
            String name = token.word();
            token = tokenizer.get();
            if (token.type == TokenType.SYMBOL && token.isSymbol("=")) {
                token = tokenizer.get();
                expr = Expr.affectation(name, treeExpr());
                expr.value(env); //put the variable in the table.
            }
            if (env.table.containsKey(name)) {
                expr = Expr.variable(name);
            } else {
                throw new EvaluationErrorException(", variable '" + name + "' not defined");
            }
            return expr;
        } else if(token.string.equals("-")) {
            token = tokenizer.get();
            expr = Expr.constant(treeExpr().value(env) * -1);
            token = tokenizer.get();
        } 
        else if (token.string.equals("(")) {
            token = tokenizer.get();
            expr = Expr.binary(expr, OpBinary.PLUS, treeExpr());
            checkSyntax(token.string.equals(")"), ") expected");
            token = tokenizer.get();
        } else {
            throw new SyntaxErrorException("Syntax error");
        }
        return expr;
    }

    private void checkSyntax(boolean isSomething, String expectation) throws SyntaxErrorException {
        if (!isSomething) {
            throw new SyntaxErrorException(expectation);
        }
    }
}
