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
        System.out.printf("  .global main\n");
        System.out.printf("main:\n");
        System.out.printf("  li a0, %d\n", scanner.nextInt());
        
        while (scanner.useDelimiter("[\\d]+").hasNext()) {
            String op = scanner.useDelimiter("[\\d]+").next();
            if (op.equals("+")) {
                System.out.printf("  addi a0, a0, %d\n", scanner.useDelimiter("[^\\d]+").nextInt());
                continue;
            }
            if (op.equals("-")) {
                System.out.printf("  addi a0, a0, -%d\n", scanner.useDelimiter("[^\\d]+").nextInt());
                continue;
            }
            System.err.printf("unexpected character: %c\n", op);
            System.exit(1);
        }

        System.out.printf("  ret\n");
    }    
}
