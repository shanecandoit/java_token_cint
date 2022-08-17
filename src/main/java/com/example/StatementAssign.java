package com.example;

public class StatementAssign extends Statement {

    public final String name;
    public final Expr value;

    public StatementAssign(String name, ExprLiteral value) {
        this.name = name;
        this.value = value;
    }

    public String toString() {
        return "StatementAssign{name="+this.name+", value="+this.value+"}";
    }
}