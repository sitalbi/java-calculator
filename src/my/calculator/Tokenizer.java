package my.calculator;

import java.util.function.Supplier;

/**
 *
 * @author Simon Talbi
 */
class Tokenizer implements Supplier<Token> {

    final String SYMBOLS = "+-*/(=";

    String line;
    int next;

    public Tokenizer(String line) {
        this.line = line;
        this.next = 0;
    }

    @Override
    public Token get() {
        // ignorer les espaces
        while (next < line.length()
                && Character.isSpaceChar(line.charAt(next))) {
            next++;
        }
        // on est au bout ?
        if (next >= line.length()) {
            return new Token(TokenType.END, "");
        }
        // délégation du travail à une méthode par type,
        // déterminé par le premier caractère

        char first = line.charAt(next);
        if (Character.isDigit(first)) {
            return getNumber();
        } else if (Character.isLetter(first)) {
            return getWord();
        } else if (SYMBOLS.indexOf(first) >= 0) {
            return getSymbol();
        } else {
            next++;
            return new Token(TokenType.INVALID, Character.toString(first));
        }
    }

    private Token getNumber() {
        StringBuilder builder = new StringBuilder();
        do {
            builder.append(line.charAt(next));
            next++;
        }
        while (next < line.length()
                && Character.isDigit(line.charAt(next)));
        return new Token(TokenType.NUMBER, builder.toString());
    }
    
    private Token getSymbol() {
        String string = Character.toString(line.charAt(next));
        next ++;
        return new Token(TokenType.SYMBOL, string);
    }
    
    private Token getWord() {
        StringBuilder builder = new StringBuilder();
        do {
            builder.append(line.charAt(next));
            next++;
        }
        while (next < line.length()
                && isValidCharForWord(line.charAt(next)));
        return new Token(TokenType.WORD, builder.toString());
    }
    
    static boolean isValidFirstCharForWord(char c) {
        return Character.isLetter(c);
    }
    
    static boolean isValidCharForWord(char c) {
        return Character.isLetter(c) || Character.isDigit(c) || c == '_';
    }
}
