package test;

import consoleTasks.*;

public class InterpolationTest {
    public static void main(String[] args) { 
        SetInterpolation fun = new SetInterpolation();
        int num;
        double x;

        java.util.Scanner in = new java.util.Scanner(System.in);
        do {
            System.out.print("Кількість точок: ");
            num = in.nextInt();
        } while (num <= 0);

        for (int i = 0; i < num; i++) {
            x = 1.0 + (5.0 - 1.0)*Math.random();
            fun.addPoint(new Point2D(x, Math.sin(x)));
        }

        System.out.println("Інтерполяція по " + fun.numPoints() + " точкам");

        System.out.println("Набір: ");

        for (Point2D p : fun.getPoints())
            System.out.println(p);


        System.out.println("Min x: " + fun.getFirst());
        System.out.println("Max x: " + fun.getLast());

        x = 0.5*(fun.getFirst().getX() + fun.getLast().getX());
        System.out.println("Значення інтерполяції fun(" + x + ") = " + fun.evalf(x));
        System.out.println("Точне значення sin(" + x + ") = " + Math.sin(x));
        System.out.println("Абсолютна помилка: " + Math.abs(fun.evalf(x)-Math.sin(x)));

        in.close();
    }

}
