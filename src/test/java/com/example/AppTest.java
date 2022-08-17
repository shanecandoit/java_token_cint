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
     * Test statement of var assign int
     */
    @Test
    public void testStatementAssign() {

        String input = "int num=12;";
        Tokenizer tokenizer = new Tokenizer(input);
        Token[] tokens = tokenizer.tokens;
        String wantTokens = "[Token{text=int, type=TYPE}, Token{text=num, type=IDENT}, Token{text==, type=ASSIGN}, Token{text=12, type=INTEGER}, Token{text=;, type=SEMICOLON}]";
        boolean ok = Arrays.toString(tokens).equals(wantTokens);
        System.out.println("ok=" + ok);

        Parser parser = new Parser(tokens);
        String statementsStr = parser.getProgramStatementsString();
        String wantStatement = "[StatementAssign{name=num, value=ExprLiteral{value=Token{text=12, type=INTEGER}}}]";

        assertTrue(statementsStr.equals(wantStatement));
    }

    /**
     * Test statement of var assign int and evaluate
     */
    @Test
    public void testStatementAssignEvaluate() {

        String input = "int num=12;";
        Tokenizer tokenizer = new Tokenizer(input);
        Token[] tokens = tokenizer.tokens;
        String wantTokens = "[Token{text=int, type=TYPE}, Token{text=num, type=IDENT}, Token{text==, type=ASSIGN}, Token{text=12, type=INTEGER}, Token{text=;, type=SEMICOLON}]";
        boolean ok = Arrays.toString(tokens).equals(wantTokens);
        System.out.println("ok=" + ok);

        Parser parser = new Parser(tokens);
        String statementsStr = parser.getProgramStatementsString();

        System.out.println("statementsStr=" + statementsStr);
        // [StatementAssign{name=num, value=ExprLiteral{value=Token{text=12,
        // type=INTEGER}}}, ]

        String result = parser.evaluate("num");
        String wantResult = "12";

        assertTrue(result.equals(wantResult));
    }

    /**
     * Test assinging an int to a variable
     */
    @Test
    public void testSimpleIntAssignment() {

        String input = "n=12;";
        Tokenizer tokenizer = new Tokenizer(input);
        Token[] tokens = tokenizer.tokens;

        String want = "[Token{text=n, type=IDENT}, Token{text==, type=ASSIGN}, Token{text=12, type=INTEGER}, Token{text=;, type=SEMICOLON}]";

        String got = Arrays.toString(tokens);
        System.out.println("got=" + got);

        boolean ok = got.equals(want);
        System.out.println("ok=" + ok);

        assertTrue(got.equals(want));
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

    /**
     * Test lexing a string
     */
    @Test
    public void testStringLexing() {
        String input = "\"hi all.\"";

        Tokenizer tokenizer = new Tokenizer(input);
        Token[] tokens = tokenizer.tokens;
        System.out.println("tokens = " + Arrays.toString(tokens));

        String want = "[Token{text=\"hi all.\", type=STRING}]";

        String got = Arrays.toString(tokens);
        System.out.println("got=" + got);

        boolean ok = Arrays.toString(tokens).equals(want);

        assertTrue(ok);
    }

    /**
     * Test assinging a string to a variable
     */
    @Test
    public void testStringAssignment() {
        String input = "char greetings[] = \"Hello World!\";";

        Tokenizer tokenizer = new Tokenizer(input);
        System.out.println("tokenizer = " + tokenizer);

        Token[] tokens = tokenizer.tokens;
        System.out.println("tokens = " + Arrays.toString(tokens));

        String want = "[Token{text=char, type=TYPE}, Token{text=greetings, type=IDENT}, Token{text=[, type=BRACK_OPEN}, Token{text=], type=BRACK_CLOSE}, Token{text==, type=ASSIGN}, Token{text=\"Hello World!\", type=STRING}, Token{text=;, type=SEMICOLON}]";
        boolean ok = Arrays.toString(tokens).equals(want);
        System.out.println("ok=" + ok);

        assertTrue(ok);
    }
}
