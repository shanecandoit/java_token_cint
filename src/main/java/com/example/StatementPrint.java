package com.example;

public class StatementPrint extends Statement {

    public final Expr value;

    public StatementPrint(String name, ExprLiteral value) {
        this.value = value;
    }

    public String toString() {
        return "StatementPrint{value="+this.value+"}";
    }
}