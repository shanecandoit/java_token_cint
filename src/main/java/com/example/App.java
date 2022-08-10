package com.example;

import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        String input = "int num=12;";

        Tokenizer tokenizer = new Tokenizer(input);
        System.out.println("tokenizer = "+tokenizer);

        Token[] tokens = tokenizer.tokens;
        System.out.println("tokens = "+Arrays.toString(tokens));

        String want = "[Token{text=int, type=TYPE}, Token{text=num, type=IDENT}, Token{text==, type=ASSIGN}, Token{text=12, type=INTEGER}, Token{text=;, type=SEMICOLON}]";
        boolean ok = Arrays.toString(tokens).equals(want);
        System.out.println("ok="+ok);
    }

    
}
