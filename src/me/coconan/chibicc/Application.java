package me.coconan.chibicc;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("java-chibicc: invalid number of arguments\n");
            System.exit(1);;
        }

        Scanner scanner = new Scanner(new ByteArrayInputStream(args[0].getBytes())).useDelimiter("[^\\d]+");
        Token token = tokenize(scanner);

        System.out.printf("  .global main\n");
        System.out.printf("main:\n");
        System.out.printf("  li a0, %d\n", token.val);
        
        token = token.next;
        while (token.tokenKind != TokenKind.TK_EOF) {
            if (token.op.equals("+")) {
                System.out.printf("  addi a0, a0, %d\n", token.next.val);
                token = token.next.next;
                continue;
            }
            if (token.op.equals("-")) {
                System.out.printf("  addi a0, a0, -%d\n", token.next.val);
                token = token.next.next;
                continue;
            }
        }

        System.out.printf("  ret\n");
    }
    
    private static Token tokenize(Scanner input) {
        Token head = new Token(TokenKind.TK_PUNCT, null, 0);
        Token cur = head;

        while (input.useDelimiter("\\s+").hasNext()) {
            if (input.useDelimiter("[^\\d]+").hasNext("[\\d]+")) {
                cur = cur.next = new Token(TokenKind.TK_NUM, null, input.useDelimiter("[^\\d]+").nextInt());
            }
            if (input.useDelimiter("[\\d\\s]+").hasNext("[+-]+")) {
                cur = cur.next = new Token(TokenKind.TK_PUNCT, null, input.useDelimiter("[\\d\\s]+").next());
            }
        }

        cur = cur.next = new Token(TokenKind.TK_EOF, null, 0);
        return head.next;
    }
}
