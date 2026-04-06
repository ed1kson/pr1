package test;

import consoleTasks.ListInterpolation;
import consoleTasks.Point2D;
import consoleTasks.NumMethods;

public class NumMethodsTest {
    public static void main(String[] args) {
        ListInterpolation fun = new ListInterpolation();

        int num;
        double x = -0.5*Math.PI;
        double step = 0.1;
        java.util.Scanner in = new java.util.Scanner(System.in);
        
        do {
            System.out.println("Кількість точок: ");
            num = in.nextInt();
        } while (num <= 0);

        for (int i = 0; i < num; i++) {
            x += step;
            fun.addPoint(new Point2D(x, Math.sin(x)));
        }

        x = 0.5*(fun.getPoint(0).getX() + fun.getPoint(fun.numPoints()-1).getX());
        double res = NumMethods.der(x, 10.e-5, fun);

        System.out.println("Чисельне значення sin(" + x + ") = " + res);
        System.out.println("Символьне значення sin(" + x + ") = " + Math.cos(x));
        System.out.println("Абсолютна помилка = " + Math.abs(res-Math.cos(x)));

        in.close();
    }
}
