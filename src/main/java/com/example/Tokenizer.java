package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tokenizer {

    public final String[] lexemes;
    private Token[] tokens;
    public final String input;

    public Tokenizer(String input) {
        this.input = input == null ? "" : input;

        Lexer lexer = new Lexer(input);
        this.lexemes = lexer.lexemes;

        this.tokens = tokenize(this.lexemes);
    }

    public Token[] getTokens() {
        return Arrays.copyOf(this.tokens, this.tokens.length);
    }

    private Token[] tokenize(String[] lexemes2) {
        List<Token> tokens = new ArrayList<>();

        for (String string : lexemes) {
            tokens.add(new Token(string));
        }

        return tokens.toArray(new Token[0]);
    }

    @Override
    public String toString() {
        return "Tokenizer{"
                + "lexemes=" + Arrays.toString(lexemes)
                + ", tokens=" + Arrays.toString(tokens)
                + ", input=\"" + input + "\""
                + "}";
    }

}
