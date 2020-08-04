package my.calculator;

/**
 *
 * @author Simon Talbi
 */
public class Token {
    final TokenType type;
    final String string;

    public Token(TokenType type, String string) {
        this.type = type;
        this.string = string;
    }

    public boolean isNumber() {
        return type.equals(TokenType.NUMBER);
    }
    
    public int value() {
        return Integer.parseInt(string);
    }
    public boolean isEnd() {
        return type.equals(TokenType.END);
    }
    
    public boolean isSymbol() {
        return type.equals(TokenType.SYMBOL);
    }
    
    public boolean isSymbol(String string) {
        return this.string.equals(string);
    }
    
    public boolean isPrioritySymbol() {
        return (string.equals("*") || string.equals("/"));
    }

    public boolean isWord() {
        return type.equals(TokenType.WORD);
    }

    public String word() {
        return string;
    }

}
    

