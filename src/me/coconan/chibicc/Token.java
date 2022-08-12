package me.coconan.chibicc;

public class Token {
    public TokenKind tokenKind;
    public Token next;
    public int val;
    public String op;

    public Token(TokenKind tokenKind, Token next, int val) {
        this.tokenKind = tokenKind;
        this.next = next;
        this.val = val;
    }

    public Token(TokenKind tokenKind, Token next, String op) {
        this.tokenKind = tokenKind;
        this.next = next;
        this.op = op;
    }
}
