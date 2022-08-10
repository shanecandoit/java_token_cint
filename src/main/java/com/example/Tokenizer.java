package com.example;

import java.util.ArrayList;
import java.util.Arrays;
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

            if (str.isBlank()) {
                continue;
            }

            parts.add(str);
        }

        parts = mergeSpecials(parts);

        this.lexemes = parts.toArray(new String[0]);

        this.tokens = tokenize(this.lexemes);
    }

    private List<String> mergeSpecials(List<String> parts) {

        List<String> merged = new ArrayList<>();

        for (int i = 0; i < parts.size(); i++) {

            String part = parts.get(i);

            if (i < parts.size() - 2
                    && Token.isInt(part)
                    && parts.get(i + 1).equals(".")
                    && Token.isInt(parts.get(i + 2))) {
                // special logic for floating point numbers
                merged.add(part + parts.get(i + 1) + parts.get(i + 2));
                i += 2;
                continue;
            } else if (part.equals("\"")) {
                // special logic for string literals
                StringBuilder sb = new StringBuilder();
                sb.append(part);
                while (i < parts.size() && !parts.get(i).equals("\"")) {
                    sb.append(parts.get(i));
                    i++;
                }
                sb.append(parts.get(i));
                merged.add(sb.toString());
                continue;
            } else {
                merged.add(part);
            }
        }
        return merged;
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
