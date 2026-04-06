package test;

import consoleTasks.Point2D;

public class Point2DTest {
    public static void main(String[] args) {
        java.util.List<Point2D> data = new java.util.ArrayList<Point2D>();
        int num;
        double x;

        java.util.Scanner in = new java.util.Scanner(System.in);

        do {
            System.out.print("Quantitiy of points: ");
            num = in.nextInt();
        } while (num <= 0);

        for (int i = 0; i < num; i++) {
            x = 1.0 + (5.0 - 1.0)*Math.random();
            data.add(new Point2D(x, Math.sin(x)));
        }

        System.out.println("Unsorted data: ");
        for (Point2D pt : data)
            System.out.println(pt);

        System.out.println("\nSorted data: ");
        java.util.Collections.sort(data);
        for (Point2D pt : data)
            System.out.println(pt);

        in.close();
    }
}
