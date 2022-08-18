package com.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Interpreter repl or take files.
 */
public class App {

    public static void main(String[] args) {
        System.out.println("interpret:");

        String input = "int num=12;\n"
                + "print(num);" +
                "float pi=3.141593;" +
                "print(pi);";
        Parser parser = new Parser(input);

        String statements = parser.getProgramStatementsString();
        System.out.println("statements = " + statements);

        // TODO get repl working, added to parser
        // replPrompt();
    }

    private static void replPrompt() {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(reader);

        System.out.print("c interpreter:");
        while (true) {
            System.out.print("> ");
            String input = null;
            try {
                input = in.readLine();
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            if (input == null) {
                break;
            }
            Tokenizer tokenizer = new Tokenizer(input);
            System.out.println("tokenizer = " + tokenizer);
            Token[] tokens = tokenizer.getTokens();
            System.out.println("tokens = " + Arrays.toString(tokens));

            // TODO add the tokens to the interpreter
        }
        System.out.println("end.");
    }
}
