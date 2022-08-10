package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Tokenizer {

    public final String[] lexemes;
    public final Token[] tokens;
    public final String input;

    public Tokenizer(String input) {
        this.input = input;

        // lexemes
        String delimeters = " \n\t.;:',=";
        // StringTokenizer(String str, String delim, boolean returnDelims)
        StringTokenizer st = new StringTokenizer(input, delimeters, true);
        List<String> parts = new ArrayList<>();
        while (st.hasMoreTokens()) {
            // for (String str : st.) {
            String str = st.nextToken();
            if (!str.isBlank()) {
                parts.add(str);
            }
        }

        this.lexemes = parts.toArray(new String[0]);

        this.tokens = tokenize(this.lexemes);
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
                + "lexemes=" + lexemes
                + ", tokens=" + tokens
                + ", input=\"" + input + "\""
                + "}";
    }

}
