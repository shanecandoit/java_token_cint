package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Parser {

    private Token[] tokens;

    private List<Statement> programStatements = new ArrayList<>();

    // errors
    private List<String> errors = new ArrayList<>();
    // from print statements, all state in parser right now
    private List<String> prints = new ArrayList<>();

    private HashMap<String, Object> environment = new HashMap<>();

    public Parser(Token[] tokens)  {
        this.tokens = tokens;

        // assume we have a list of statements
        try {
            this.programStatements = parseStatements(this.tokens);
        }catch (Exception e) {
            System.out.println("Parser error = " + e.getMessage());
            errors.add(e.getMessage());
        }
    }

    public Parser(String input) {
        Lexer lexer = new Lexer(input);

        this.tokens = lexer.getTokens();
        this.environment = new HashMap<>();

        try {
            this.programStatements = parseStatements(this.tokens);
        }catch (Exception e){
            System.out.println("Parser error = " + e.getMessage());
            errors.add(e.getMessage());
        }
    }

    public Token[] getTokens() {
        return Arrays.copyOf(this.tokens, this.tokens.length);
    }

    public String getTokensString() {
        return Arrays.toString(getTokens());
    }

    private List<Statement> parseStatements(Token[] tokens) throws Exception {

        List<Statement> statements = new ArrayList<>();

        for (int i = 0; i < tokens.length; i++) {

            Token token = tokens[i];

            // switch over token type
            switch (token.type) {
                case TYPE: {
                    // assignment?
                    if (tokens.length >= i + 3
                            && tokens[i + 0].type == TokenType.TYPE
                            && tokens[i + 1].type == TokenType.IDENT
                            && tokens[i + 2].type == TokenType.ASSIGN
                            &&
                            (tokens[i + 3].type == TokenType.INTEGER
                                    || tokens[i + 3].type == TokenType.FLOAT
                                    || tokens[i + 3].type == TokenType.STRING)
                            && tokens[i + 4].type == TokenType.SEMICOLON) {
                        String name = tokens[i + 1].text;
                        Object value = tokens[i + 3].text;

                        if (tokens[i + 3].type == TokenType.INTEGER) {
                            value = Integer.parseInt(tokens[i + 3].text);
                        }

                        StatementAssign assignment = new StatementAssign(tokens[i + 1].text,
                                new ExprLiteral(name, value));

                        statements.add(assignment);

                        this.environment.put(name, value);
                        i += 4;
                    }
                }
                break;

                case PRINT: {
                    //5 = {Token@834} "Token{text=print, type=PRINT}"
                    //6 = {Token@847} "Token{text=(, type=PAREN_OPEN}"
                    //7 = {Token@848} "Token{text=num, type=IDENT}"
                    //8 = {Token@849} "Token{text=), type=PAREN_CLOSE}"
                    //9 = {Token@850} "Token{text=;, type=SEMICOLON}"
//                    int count = 5;
                    if (tokens.length >= i + 4
                            && tokens[i + 0].type == TokenType.PRINT
                            && tokens[i + 1].type == TokenType.PAREN_OPEN
                            && tokens[i + 2].type == TokenType.IDENT // TODO or literal
                            && tokens[i + 3].type == TokenType.PAREN_CLOSE
                            && tokens[i + 4].type == TokenType.SEMICOLON
                    ) {
                        String name = tokens[i + 2].text;
                        Object val = this.environment.get(name);

                        // if no such var in a C program?
                        // spage@spage-l:~/ctest$ tcc no_var_name.c
                        // no_var_name.c:4: error: 'num' undeclared
                        if (!this.environment.containsKey(name)) {
                            String errorMessage = "Error '"+name+"' undeclared.";
                            this.errors.add(errorMessage);
                            // throw new Exception(errorMessage);
                            return statements;
                        }

                        Statement print = new StatementPrint(new ExprLiteral(name, val));
                        // NOTE: just print it here?
                        System.out.println(val);
                        statements.add(print);

                        this.prints.add(val.toString());
                        i += 4;
                    }

                }
                break;
                default: {
                    // throw exception ?
                    System.out.println("Parser: Unexpected Token:" + token);
                    break;
                }
            } // switch (token.type)
        }

        return statements;
    }

    public List<Statement> getProgramStatements() {
        return programStatements;
    }

    public String getProgramStatementsString() {
        Statement[] statements = this.programStatements.toArray(new Statement[0]);
        return Arrays.toString(statements);
    }

    public String evaluate(String string) {
        Object value = this.environment.get(string);
        String result = value == null ? "" : value.toString();
        return result;
    }

    public List<String> getErrors() {
        return this.errors;
    }
    public void printErrors() {
        for (String error: this.errors){
            System.out.println(error);
        }
    }
}
