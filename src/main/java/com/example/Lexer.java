package com.example;

import java.util.ArrayList;
import java.util.List;

public class Lexer {

    // lexemes
    public final String[] lexemes;
    public final String delimeters = " \n\t.;:'\",=[](){}";

    private char[] chars;
    private int i;
    private String string;
    private int line;

    public Lexer(String input){
        this.lexemes = lex(input);
    }
    // StringTokenizer(String str, String delim, boolean returnDelims)
    public String[] lex(String input) {
        // lexemes
        // StringTokenizer(String str, String delim, boolean returnDelims)
        List<String> parts = new ArrayList<>();

        this.line = 1;
        this.string = "";
        // String prev = "";
        char ch = '\0';

        this.chars = input.toCharArray();
        for (this.i = 0; i < chars.length; i++) {
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
//                        this.lexemes = parts.toArray(new String[0]);
                        //return parts.toArray(new String[0]);
                        break;
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
            } else if (ch==' '||ch=='\t'){
                if (string.length()>1){
                    parts.add(string);
                }
                string="";
                continue;
            }

            // newest char is a delimeter
            if (delimeters.contains("" + ch)) {
                // split
                if (string.length() > 0) {
                    parts.add(string);
                }
                if (ch == ' ' || ch == '\t' || ch == '\n') {
                    if (ch == '\n'){line++;}
                    string = "";
                    continue;
                }
                else {
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

        return parts.toArray(new String[0]);
    }
}
