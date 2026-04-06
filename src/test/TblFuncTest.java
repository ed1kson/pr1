package test;

import consoleTasks.*;

import java.io.IOException;
import java.util.Scanner;

public class TblFuncTest {
    
    static Scanner in = new Scanner(System.in);
    public static void main(String[] args) throws IOException {
        System.out.println("Введіть кількість точок: ");
        int n = in.nextInt();
        
        FileListInterpolation list = new FileListInterpolation();
        for ( int i = 0 ; i < n ; i++ ) {
            System.out.printf("Точка №1\n", i);
            System.out.printf("Введіть х: ");
            double x = in.nextDouble();
            System.out.printf("Введіть y: ");
            double y = in.nextDouble();

            list.addPoint(new Point2D(x, y));
        }

        in.nextLine();
        System.out.printf("Введіть шлях: ");
        String path = in.nextLine();

        list.writeToFile(path);
    }
}
