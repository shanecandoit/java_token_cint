package com.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Interpreter repl or take files.
 */
public class App {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        String input = "char greetings[] = \"Hello World!\";";

        Tokenizer tokenizer = new Tokenizer(input);
        System.out.println("tokenizer = " + tokenizer);

        Token[] tokens = tokenizer.tokens;
        System.out.println("tokens = " + Arrays.toString(tokens));

        String want = "[Token{text=char, type=TYPE}, Token{text=greetings, type=IDENT}, Token{text=[, type=BRACK_OPEN}, Token{text=], type=BRACK_CLOSE}, Token{text==, type=ASSIGN}, Token{text=\"Hello World!\", type=STRING}, Token{text=;, type=SEMICOLON}]";
        boolean ok = Arrays.toString(tokens).equals(want);
        System.out.println("ok=" + ok);

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
            Token[] tokens = tokenizer.tokens;
            System.out.println("tokens = " + Arrays.toString(tokens));

            // TODO add the tokens to the interpreter
        }
        System.out.println("end.");
    }
}
