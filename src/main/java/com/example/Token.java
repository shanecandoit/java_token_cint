package com.example;

public class Token {

    public final String text;
    public final TokenType type;

    public Token(String text) {
        
        this.text = text==null ? "" : text;
        text = this.text;

        if (text.equals(";")){
            this.type=TokenType.SEMICOLON;
        }else if(text.equals("int")){
            this.type=TokenType.TYPE;
        }else if(text.equals("=")){
            this.type=TokenType.ASSIGN;
        }else if( isAlpha(text) ){
            this.type=TokenType.IDENT;
        }else if( isInt(text) ){
            this.type=TokenType.INTEGER;
        } else {
            this.type=TokenType.NONE;
        }
    }

    private Token(String text, TokenType type) {
        this.text = text;
        this.type = type;
    }

    private Token() {
        this.text = "";
        this.type = TokenType.NONE;
    }

    static boolean isAlpha(String string){
        boolean ok = string!=null && string.length()>0;
        boolean lower = string.charAt(0)>='a' && string.charAt(0)<='z';
        boolean upper = string.charAt(0)>='A' && string.charAt(0)<='Z';
        return ok && (upper || lower);
    }

    static  boolean isInt(String input) {
        Integer i;
        try{
            i = Integer.parseInt(input);
        }catch(Exception e){
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Token{"
        +"text="+text
        +", type="+type
        +"}";
    }
}
