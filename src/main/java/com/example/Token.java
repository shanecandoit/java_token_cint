package com.example;

public class Token {

    public final String text;
    public final TokenType type;

    public Token(String text) {

        text = text == null ? "" : text;
        this.text = text;

        if (text.equals(";")) {
            this.type = TokenType.SEMICOLON;
        } else if (text.equals("int")) {
            this.type = TokenType.TYPE;
        } else if (text.equals("float")) {
            this.type = TokenType.TYPE;
        } else if (text.equals("=")) {
            this.type = TokenType.ASSIGN;
        } else if (text.equals(".")) {
            this.type = TokenType.DOT;
        } else if (text.equals("+")) {
            this.type = TokenType.PLUS;
        } else if (isAlpha(text)) {
            this.type = TokenType.IDENT;
        } else if (isInt(text)) {
            this.type = TokenType.INTEGER;
        } else if (isFloat(text)) {
            this.type = TokenType.FLOAT;
        } else {
            this.type = TokenType.NONE;
        }
    }

    static boolean isAlpha(String string) {
        boolean ok = string != null && string.length() > 0;
        boolean lower = string.charAt(0) >= 'a'
                && string.charAt(0) <= 'z';
        boolean upper = string.charAt(0) >= 'A'
                && string.charAt(0) <= 'Z';
        return ok && (upper || lower);
    }

    static boolean isInt(String input) {
        try {
            Integer.parseInt(input);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    static boolean isFloat(String input) {
        try {
            Double.parseDouble(input);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Token{"
                + "text=" + text
                + ", type=" + type
                + "}";
    }
}
