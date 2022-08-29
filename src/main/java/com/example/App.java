package com.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

/**
 * Interpreter repl or take files.
 */
public class App {

    public static void main(String[] args) {
        System.out.println("interpret:");

        String input = "int num = 12 + 1;\n"
                + "print(num);";
        Parser parser = new Parser(input);


        String statements = parser.getProgramStatementsString();
        System.out.println("statements = " + statements);

        List<String> errors = parser.getErrors();
        System.out.println("errors.get(0) = " + errors.get(0));
        String errorGot = errors.get(0);
        String errorWant = "Error 'nm' undeclared.";

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
            Lexer lexer = new Lexer(input);
            System.out.println("tokenizer = " + lexer);
            Token[] tokens = lexer.getTokens();
            System.out.println("tokens = " + Arrays.toString(tokens));

            // TODO add the tokens to the interpreter
        }
        System.out.println("end.");
    }
}
