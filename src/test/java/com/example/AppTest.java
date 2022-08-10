package com.example;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

    /**
     * Test assinging an int to a variable
     */
    @Test
    public void testIntAssignment() {

        String input = "int num=12;";
        Tokenizer tokenizer = new Tokenizer(input);
        Token[] tokens = tokenizer.tokens;

        String want = "[Token{text=int, type=TYPE}, Token{text=num, type=IDENT}, Token{text==, type=ASSIGN}, Token{text=12, type=INTEGER}, Token{text=;, type=SEMICOLON}]";

        assertTrue(Arrays.toString(tokens).equals(want));
    }

    /**
     * Test assinging a floating point number to a variable
     */
    @Test
    public void testFloatAssignment() {
        String input = "float fp = 3.14;";

        Tokenizer tokenizer = new Tokenizer(input);
        Token[] tokens = tokenizer.tokens;

        String want = "[Token{text=float, type=TYPE}, Token{text=fp, type=IDENT}, Token{text==, type=ASSIGN}, Token{text=3.14, type=FLOAT}, Token{text=;, type=SEMICOLON}]";

        assertTrue(Arrays.toString(tokens).equals(want));
    }
}
