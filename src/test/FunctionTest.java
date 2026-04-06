package test;

import consoleTasks.Function;

public class FunctionTest {
    
    public static void main(String[] args) {
        java.util.Scanner in = new java.util.Scanner(System.in);
        System.out.println("Function class check ");
        Function fun = new Function();
        System.out.println("Enter beginning: ");
        double xBeg = in.nextDouble();
        System.out.println("Enter ending: ");
        double xEnd = in.nextDouble();
        System.out.println("Enter step: ");
        double step = in.nextDouble();

        System.out.println("Parameter a: " + fun.getA());
        for (double x = xBeg; x <= xEnd; x += step) {
            System.out.printf("x: %6.4f\tf: %6.4f\n", x, fun.evalf(x));
        }

        System.out.print("x: ");
        double x = in.nextDouble();
        System.out.print("aBeg: ");
        double aBeg = in.nextDouble();
        System.out.print("aEnd: ");
        double aEnd = in.nextDouble();
        System.out.print("aStep: ");
        double aStep = in.nextDouble();

        System.out.println("Змінна x: " + x);
        for (double a = aBeg; a <= aEnd; a += aStep) {
            fun.setA(a);
            System.out.printf("a: %6.4f\tf: %6.4f\n", fun.getA(), fun.evalf(x));
        }
        in.close();
    }
}
