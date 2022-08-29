package com.example;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

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
//        Tokenizer tokenizer = new Tokenizer(input);
        Lexer lexer = new Lexer(input);
        Token[] tokens = lexer.getTokens();

        String want = "[Token{text=int, type=TYPE}, Token{text=num, type=IDENT}, Token{text==, type=ASSIGN}, Token{text=12, type=INTEGER}, Token{text=;, type=SEMICOLON}]";

        assertTrue(Arrays.toString(tokens).equals(want));
    }

    /**
     * Test statement of var assign int
     */
    @Test
    public void testStatementAssign() {

        String input = "int num=12;";
        Lexer lexer = new Lexer(input);
        Token[] tokens = lexer.getTokens();
        String wantTokens = "[Token{text=int, type=TYPE}, Token{text=num, type=IDENT}, Token{text==, type=ASSIGN}, Token{text=12, type=INTEGER}, Token{text=;, type=SEMICOLON}]";
        boolean ok = Arrays.toString(tokens).equals(wantTokens);
        System.out.println("ok=" + ok);

        Parser parser = new Parser(tokens);
        String statementsGot = parser.getProgramStatementsString();
        System.out.println("statementsGot = " + statementsGot);
        String wantStatement = "[StatementAssign{name=num, value=ExprLiteral{value=12}}]";
        ok = statementsGot.equals(wantStatement);
        System.out.println("ok = " + ok);

        assertTrue(statementsGot.equals(wantStatement));
    }

    /**
     * Test statement of var assign int and evaluate
     */
    @Test
    public void testStatementAssignEvaluate() {

        String input = "int num=12;";
        Lexer lexer = new Lexer(input);
        Token[] tokens = lexer.getTokens();
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
        Lexer lexer = new Lexer(input);
        Token[] tokens = lexer.getTokens();

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

        Lexer lexer = new Lexer(input);
        Token[] tokens = lexer.getTokens();
        System.out.println("tokens = " + tokens);

        String want = "[Token{text=float, type=TYPE}, Token{text=fp, type=IDENT}, Token{text==, type=ASSIGN}, Token{text=3.14, type=FLOAT}, Token{text=;, type=SEMICOLON}]";

        assertTrue(Arrays.toString(tokens).equals(want));
    }

    /**
     * Test lexing a string
     */
    @Test
    public void testStringLexing() {
        String input = "\"hi all.\"";

        Lexer lexer = new Lexer(input);
        Token[] tokens = lexer.getTokens();
        System.out.println("tokens = " + Arrays.toString(tokens));

        String want = "[Token{text=\"hi all.\", type=STRING}]";

        String got = Arrays.toString(tokens);
        System.out.println("got=" + got);

        boolean ok = Arrays.toString(tokens).equals(want);

        assertTrue(ok);
    }

    /**
     * Test assigning a string to a variable
     */
    @Test
    public void testStringAssignment() {
        String input = "char greetings[] = \"Hello World!\";";

        Lexer lexer = new Lexer(input);
        Token[] tokens = lexer.getTokens();
        System.out.println("tokens = " + Arrays.toString(tokens));

        String want = "[Token{text=char, type=TYPE}, Token{text=greetings, type=IDENT}, Token{text=[, type=BRACK_OPEN}, Token{text=], type=BRACK_CLOSE}, Token{text==, type=ASSIGN}, Token{text=\"Hello World!\", type=STRING}, Token{text=;, type=SEMICOLON}]";
        boolean ok = Arrays.toString(tokens).equals(want);
        System.out.println("ok=" + ok);

        assertTrue(ok);
    }

    /**
     * Test assigning an int to a variable and getting it back
     */
    @Test
    public void testIntAssignmentAndEvaluate() {

        String input = "int num=12;\n"
                + "print(num);";
        Parser parser = new Parser(input);

        System.out.println("parser.tokens=" + parser.getTokensString());
        String tokensGet = parser.getTokensString();

        String tokensWant = "[Token{text=int, type=TYPE}, Token{text=num, type=IDENT}, Token{text==, type=ASSIGN}, Token{text=12, type=INTEGER}, Token{text=;, type=SEMICOLON}, Token{text=print, type=PRINT}, Token{text=(, type=PAREN_OPEN}, Token{text=num, type=IDENT}, Token{text=), type=PAREN_CLOSE}, Token{text=;, type=SEMICOLON}]";
        boolean tokensOk = tokensWant.equals(tokensGet);
        System.out.println("tokensOk=" + tokensOk);
        assertTrue(tokensOk);

        String numGot = parser.evaluate("num");
        System.out.println("numGot=" + numGot);

        String numWant = "12";
        boolean numsEvalOk = numGot.equals(numWant);
        System.out.println("numsEvalOk=" + numsEvalOk);

        assertTrue(numsEvalOk);
    }

    /**
     * Test assigning an int and a float, then print both
     */
    @Test
    public void testIntFloatPrint() {

        String input = "int num=12;\n"
                + "print(num);" +
                "float pi=3.141593;" +
                "print(pi);";
        Parser parser = new Parser(input);

        String statementsGot = parser.getProgramStatementsString();
        System.out.println("statementsGot=" + statementsGot);
        String statementsWant = "[StatementAssign{name=num, value=ExprLiteral{value=12}}, StatementPrint{value=ExprLiteral{value=12}}, StatementAssign{name=pi, value=ExprLiteral{value=3.141593}}, StatementPrint{value=ExprLiteral{value=3.141593}}]";

        boolean statementsOk = statementsGot.equals(statementsWant);
        System.out.println("statementsOk=" + statementsOk);

        assertTrue(statementsOk);
    }

    /**
     * Test printing missing variable error
     */
    @Test
    public void testVarUndeclaredError() {

        String input = "int num=12;\n"
                + "print(nm);";
        Parser parser = new Parser(input);

        String statements = parser.getProgramStatementsString();
        System.out.println("statements = " + statements);

        List<String> errors = parser.getErrors();
        System.out.println("errors.get(0) = " + errors.get(0));
        String errorGot = errors.get(0);
        String errorWant = "Error 'nm' undeclared.";

        assertTrue(errorGot.equals(errorWant));
    }
}
