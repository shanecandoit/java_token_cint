package com.example;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void testIntAssignment()
    {
        String input = "int num=12;";

        Tokenizer tokenizer = new Tokenizer(input);
        System.out.println("tokenizer = "+tokenizer);

        Token[] tokens = tokenizer.tokens;
        System.out.println("tokens = "+Arrays.toString(tokens));

        String want = "[Token{text=int, type=TYPE}, Token{text=num, type=IDENT}, Token{text==, type=ASSIGN}, Token{text=12, type=INTEGER}, Token{text=;, type=SEMICOLON}]";
        boolean ok = Arrays.toString(tokens).equals(want);
        System.out.println("ok="+ok);
        assertTrue( ok );
    }
}
