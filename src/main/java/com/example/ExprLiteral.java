package com.example;

public class ExprLiteral extends Expr {

    public final Object value;

    public ExprLiteral(String name, Object obj) {
        super(obj);
        this.value = obj;
    }

    @Override
    public String toString() {
        return "ExprLiteral{" +
                "value=" + value +
                '}';
    }
}
