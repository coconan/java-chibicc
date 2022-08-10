package me.coconan.chibicc;

public class Application {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("java-chibicc: invalid number of arguments\n");
            System.exit(1);;
        }

        System.out.printf("  .global main\n");
        System.out.printf("main:\n");
        System.out.printf("  li a0, %d\n", Integer.valueOf(args[0]));
        System.out.printf("  ret\n");
    }    
}
