package test;

import consoleTasks.*;

public class SFunctionTest {
    public static void main(String[] args) {
        java.util.Scanner in = new java.util.Scanner(System.in);
        System.out.println("SFuncion class check ");
        SFunction fun = new SFunction("a*sin(x)", 3);
        System.out.println("Function: " + fun);
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

        SFunction derivative = fun.derive();
        System.out.println("derivative: " + derivative);

        for (double x = xBeg; x <= xEnd; x += step) {
            System.out.printf("x: %6.4f\tf: %6.4f\n", x, derivative.evalf(x));
        }

        in.close();
    }
}
