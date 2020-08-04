package my.calculator;

import java.util.function.IntBinaryOperator;

/**
 * @author Simon Talbi
 * Binary operations in expressions
 *
 */
public enum OpBinary {

    PLUS((a, b) -> a + b),
    MOINS((a, b) -> a - b),
    FOIS((a, b) -> a * b),
    DIVISER((a, b) -> a / b);
    
    final IntBinaryOperator op;
    
    OpBinary(IntBinaryOperator op) {
        this.op = op;
    }
    
    int appliquer(int a, int b) {
        return op.applyAsInt(a, b);
    }
    
}
