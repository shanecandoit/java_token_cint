package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Parser {

    public final Token[] tokens;

    private List<Statement> programStatements;

    private HashMap<String, Object> environment;

    public Parser(Token[] tokens) {
        this.tokens = tokens;
        this.environment = new HashMap<>();

        // assume we have a list of statements
        this.programStatements = parseStatements(this.tokens);

    }

    public Parser(String input) {
        Tokenizer tokenizer = new Tokenizer(input);
        Token[] tokens = tokenizer.tokens;

        this.tokens = tokens;
        this.environment = new HashMap<>();

        this.programStatements = parseStatements(this.tokens);
    }

    private List<Statement> parseStatements(Token[] tokens) {

        List<Statement> statements = new ArrayList<>();

        // huh
        if (this.environment == null) {
            this.environment = new HashMap<>();
        }

        // assignment?
        if (tokens.length >= 3
                && tokens[0].type == TokenType.TYPE
                && tokens[1].type == TokenType.IDENT
                && tokens[2].type == TokenType.ASSIGN
                &&
                (tokens[3].type == TokenType.INTEGER
                        || tokens[3].type == TokenType.FLOAT
                        || tokens[3].type == TokenType.STRING)
                && tokens[4].type == TokenType.SEMICOLON) {
            String name = tokens[1].text;
            Object value = tokens[3].text;

            if (tokens[3].type == TokenType.INTEGER) {
                value = Integer.parseInt(tokens[3].text);
            }

            statements.add(new StatementAssign(tokens[1].text,
                    new ExprLiteral(tokens[3])));

            this.environment.put(name, value);
        }

        // this.environment = new HashMap<>();

        return statements;
    }

    public List<Statement> getProgramStatements() {
        return programStatements;
    }

    public String getProgramStatementsString() {
        Statement[] statements = this.programStatements.toArray(new Statement[0]);
        return Arrays.toString(statements);
        // return Arrays.toString(programStatements);
        // String string = "[";
        // for (Statement stat : getProgramStatements()) {
        // string += stat.toString() + ", ";
        // }
        // string += "]";
        // return string;
    }

    public String evaluate(String string) {
        Object value = this.environment.get(string);
        String result = value.toString();
        return result;
    }

}
