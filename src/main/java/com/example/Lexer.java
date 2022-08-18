package com.example;

import java.util.ArrayList;
import java.util.List;

public class Lexer {

    // lexemes
    public final String[] lexemes;
    public final String delimeters = " \n\t.;:'\",=[](){}";

    // StringTokenizer(String str, String delim, boolean returnDelims)
    public Lexer(String input) {
        // lexemes
        // StringTokenizer(String str, String delim, boolean returnDelims)
        List<String> parts = new ArrayList<>();

        int line = 1;
        String string = "";
        // String prev = "";
        char ch = '\0';

        char[] chars = input.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            ch = input.charAt(i);

            // string literals
            if (ch == '"') {
                string += input.charAt(i);
                while (i < input.length()) {
                    i++;
                    if (input.charAt(i) == '"') {
                        string += input.charAt(i);
                        parts.add(string);
                        string = "";
                        break;
                    } else if (input.charAt(i) == '\n') {
                        System.out.println("Err new line encountered mid-string." + line + ":" + string);
                        this.lexemes = parts.toArray(new String[0]);
                        return;
                    }
                    string += input.charAt(i);
                }
                continue;
            } else if (ch == '\n') {
                line++;
            } else if (input.charAt(i) == '=' && i < input.length() && input.charAt(i + 1) == '=') {
                parts.add(string);
                string = "==";
                i++;
            } else if (ch == '=') {
                if (string.length() > 0) {
                    parts.add(string);
                }
                parts.add("=");
                string = "";
                continue;
            } else if (ch == '.') {
                // floating point?
                try {
                    char before = input.charAt(i - 1);
                    char after = input.charAt(i + 1);
                    if (Token.isInt("" + before) && Token.isInt("" + after)) {
                        string = string + ".";
                        continue;
                    }
                } catch (Exception e) {
                    continue;
                }
            }

            // newest char is a delimeter
            if (delimeters.contains("" + ch)) {
                // split
                if (string.length() > 0) {
                    parts.add(string);
                }
                if (ch == ' ' || ch == '\t' || ch == '\n') {
                    string = "";
                    continue;
                } else {
                    string = "" + ch;
                    parts.add(string);
                    string = "";
                }
            } else {
                // grew the word
                string += ch;
            }
        }
        if (string.length() > 0) {
            parts.add(string);
        }

        this.lexemes = parts.toArray(new String[0]);
    }
}
