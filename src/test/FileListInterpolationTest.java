package test;

import consoleTasks.FileListInterpolation;
import consoleTasks.Point2D;
import java.io.IOException;

public class FileListInterpolationTest {
    public static void main(String[] args) {
        FileListInterpolation fun = new FileListInterpolation();
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
        System.out.println("Інтерполяція по: " + fun.numPoints() + " точкам");

        System.out.println("Несортований набір: ");
        for (int i = 0; i < fun.numPoints(); i++)
            System.out.println("Точка " + (i+1) + ": " + fun.getPoint(i));

        fun.sort();
        System.out.println("Відсортований набір: ");
        for (int i = 0; i < fun.numPoints(); i++)
            System.out.println("Точка " + (i+1) + ": " + fun.getPoint(i));

        System.out.println("Мінімальне значення x: " + fun.getPoint(0).getX());
        System.out.println("Максимальне значення x: " + fun.getPoint(fun.numPoints()-1).getX());

        System.out.println("Зберігаємо у файл");
        try {
            fun.writeToFile("data/data.dat");
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        System.out.println("Зчитуємо з файлу");
        fun.clear();
        try {
            fun.readFromFile("data/data.dat");
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        System.out.println("Дані з файлу: ");
        fun.sort();
        for (int i = 0; i < fun.numPoints(); i++)
            System.out.println("Точка " + (i+1) + ": " + fun.getPoint(i));

        System.out.println("Мінімальне значення x: " + fun.getPoint(0).getX());
        System.out.println("Максимальне значення x: " + fun.getPoint(fun.numPoints()-1).getX());
        x = 0.5*(fun.getPoint(0).getX() + fun.getPoint(fun.numPoints()-1).getX());
        System.out.println("Значення інтерполяції fun(" + x + ") = " + fun.evalf(x));
        System.out.println("Точне значення sin(" + x + ") = " + Math.sin(x));
        System.out.println("Абсолютна помилка = " + Math.abs(fun.evalf(x)-Math.sin(x)));
        System.out.println("Готуємо дані для розрахунку");
        fun.clear();

        for (x = 1.0; x <= 7.0; x += 0.1) {
            fun.addPoint(new Point2D(x, Math.sin(x)));
        } try {
            fun.writeToFile("data/TblFunc.dat");
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }
        in.close();

        System.out.println("Дані записано!");
    }
}
