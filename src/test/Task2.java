package test;

import consoleTasks.*;
import java.util.Scanner;
import java.io.*;

public class Task2 {
    public static void main(String[] args) throws IOException {
        checkWritingReading();
    }

    static void finalCheck() throws IOException{
        Evaluatable funcs[] = new Evaluatable[3];
        FFunction func = new FFunction();
        FFunction func1 = new FFunction(1.5);

        Scanner in = new Scanner(System.in);
        System.out.println("Enter the number of points: ");
        int num = in.nextInt();

        funcs[0] = new ListInterpolation();
        funcs[1] = new ListInterpolation();
        funcs[2] = new ListInterpolation();

        for ( int i = 0; i < num ; i++) {
            double x = 1.0 + (7.0 - 1.0)*Math.random();
            ((ListInterpolation)(funcs[0])).addPoint(new Point2D(x, func.evalf(x)));
            ((ListInterpolation)(funcs[2])).addPoint(new Point2D(x, func1.evalf(x)));
        }

        ((ListInterpolation)(funcs[0])).sort();
        ((ListInterpolation)(funcs[2])).sort();

        try {
            ( (ListInterpolation) funcs[1] ).addPoints(FileWiz.readFromCSV("data/TblFunc.csv"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        String fileName;

        for (Evaluatable f: funcs) {
            System.out.println("функція: " + f.getClass().getSimpleName());
            fileName = f.getClass().getSimpleName() + ".dat";
            PrintWriter out = new PrintWriter(new FileWriter(fileName));
            for (double x = 1.5; x <= 6.5; x += 0.05) {
                System.out.println(("x: " + x + "\tf: " + f.evalf(x) + "\tf': " +
                 NumMethods.der(x, 1.0e-4, f)));
                out.printf("%16.6e%16.6e%16.6e\n", x, f.evalf(x), 
                NumMethods.der(x, 1.0e-4, f));
            }

            System.out.println("\n");
            out.close();
        }

        in.close();

    }

    static void checkWritingReading() {
        ListInterpolation fun = new ListInterpolation();
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
            FileWiz.writeToCSV("data/data.scv", fun.getPoints());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        System.out.println("Зчитуємо з файлу");
        fun.clear();
        try {
            fun.addPoints(FileWiz.readFromCSV("data/data.scv"));
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
            FileWiz.writeToCSV("data/TblFunc.scv", fun.getPoints());
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }
        in.close();

        System.out.println("Готово!");

    }
}
