package com.example;

public class StatementPrint extends Statement {

    public final Expr value;

    public StatementPrint(ExprLiteral value) {
        this.value = value;
    }

    public String toString() {
        return "StatementPrint{value="+this.value+"}";
    }
}