package com.example;

import java.util.ArrayList;
import java.util.List;

public class Lexer {

    // lexemes
    public final String[] lexemes;
    public final String delimeters = " \n\t.;:'\",=[](){}";

    private final char[] chars;
    private int i;
//    private String string;
    private int line;
//    private Token[] tokens;

    public Lexer(String input){
        this.chars = input.toCharArray();
        this.lexemes = lex(input);
    }
    // StringTokenizer(String str, String delim, boolean returnDelims)
    public String[] lex(String input) {
        // lexemes
        // StringTokenizer(String str, String delim, boolean returnDelims)
        List<String> parts = new ArrayList<>();

        this.line = 1;
        String current = "";
        // String prev = "";
        char ch = '\0';

        for (this.i = 0; i < this.chars.length; i++) {
            ch = input.charAt(i);

            // string literals
            if (ch == '"') {
                current += input.charAt(i);
                while (i < input.length()) {
                    i++;
                    if (input.charAt(i) == '"') {
                        current += input.charAt(i);
                        parts.add(current);
                        current = "";
                        break;
                    } else if (input.charAt(i) == '\n') {
                        System.out.println("Err new line encountered mid-string." + line + ":" + current);
//                        this.lexemes = parts.toArray(new String[0]);
                        //return parts.toArray(new String[0]);
                        break;
                    }
                    current += input.charAt(i);
                }
                continue;
            } else if (ch == '\n') {
                line++;
            } else if (input.charAt(i) == '=' && i < input.length() && input.charAt(i + 1) == '=') {
                parts.add(current);
                current = "==";
                i++;
            } else if (ch == '+') {
                if (current.length() > 0) {
                    parts.add(current);
                }
                parts.add("+");
                current = "";
                continue;
            } else if (ch == '=') {
                if (current.length() > 0) {
                    parts.add(current);
                }
                parts.add("=");
                current = "";
                continue;
            } else if (ch == '.') {
                // floating point?
                try {
                    char before = input.charAt(i - 1);
                    char after = input.charAt(i + 1);
                    if (Token.isInt("" + before) && Token.isInt("" + after)) {
                        current = current + ".";
                        continue;
                    }
                } catch (Exception e) {
                    continue;
                }
            } else if (ch==' '||ch=='\t'){
                if (current.length()>1){
                    parts.add(current);
                }
                current="";
                continue;
            }

            // newest char is a delimeter
            if (delimeters.contains("" + ch)) {
                // split
                if (current.length() > 0) {
                    parts.add(current);
                }
                if (ch == ' ' || ch == '\t' || ch == '\n') {
                    if (ch == '\n'){line++;}
                    current = "";
                    continue;
                }
                else {
                    current = "" + ch;
                    parts.add(current);
                    current = "";
                }
            } else {
                // grew the word
                current += ch;
            }
        }
        if (current.length() > 0) {
            parts.add(current);
        }

        return parts.toArray(new String[0]);
    }

    public Token[] getTokens() {
        return tokenize();
    }

    private Token[] tokenize() {
        List<Token> tokens = new ArrayList<>();

        for (String string : this.lexemes) {
            tokens.add(new Token(string));
        }

        return tokens.toArray(new Token[0]);
    }
}
