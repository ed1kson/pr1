package consoleTasks;

import java.util.*;

public class SetInterpolation implements Evaluatable {
    private TreeSet<Point2D> data = null;
    
    public SetInterpolation(ArrayList<Point2D> data) {
        this.data = new TreeSet<Point2D>(Comparator.comparing(Point2D::getX));
        for ( Point2D p : data ) {
            this.data.add(p.clone());
        }
    }

    public SetInterpolation(TreeSet<Point2D> data) {
        this();

        for ( Point2D p : data ) {
            this.data.add(p.clone());
        }
    }

    public SetInterpolation(Point2D[] data) {
        this();
        for (Point2D p : data)
            this.data.add(p.clone());
    }

    public SetInterpolation() {
        this.data = new TreeSet<Point2D>(Comparator.comparing(Point2D::getX));
    }

    public void addPoints(Collection<Point2D> clctn) {
        for ( Point2D p : clctn ) {
            addPoint(p);
        }
    }

    public void clear() {
        data.clear();
    }

    public int numPoints() {
        return data.size();
    }

    public void addPoint(Point2D pt) {
        data.add(pt);
    }

    public Point2D getPoint(int ti) {
        if ( ti < 0 ) return null;

        int ci = 0; 
        for ( Point2D p : data ) {
            if ( ti == ci ) return p;
            ci++;
        }

        return null;
    }

    public void setPoint(int i, Point2D pt) {
        data.remove(getPoint(i));
        data.add(pt);
    }

    public ArrayList<Point2D> getPoints() {
        ArrayList<Point2D> list = new ArrayList<>();
    
        for ( Point2D p : data ) {
            list.add(p.clone());
        }

        return list;
    }

    public void removeLastPoint() {
        if (!data.isEmpty())
            data.remove(data.last());
    }

    @Override
    public double evalf(double x) {
        double res = 0.0;
        double numer, denom;

        for ( Point2D cp : data ) {
            numer = 1;
            denom = 1;

            for ( Point2D p : data ) {
                if ( cp != p ) {
                    numer *= (x-p.getX());
                    denom *= (cp.getX()-p.getX());
                }
            }
            res += cp.getY()*numer/denom;
        }

        return res;
    }

    //Додати перевірку решти методів та перевірити як кількість точок впливає на результати інтерполяції
    public static void main(String[] args) {
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

        System.out.println("Інтерполяція по " + fun.numPoints() + " точкам");

        System.out.println("Несортований набір: ");
        for (int i = 0; i < fun.numPoints(); i++)
            System.out.println("Точка " + (i+1) + ": " + fun.getPoint(i));

        fun.sort();
        System.out.println("Відсортований набір: ");
        for (int i = 0; i < fun.numPoints(); i++)
            System.out.println("Точка " + (i+1) + ": " + fun.getPoint(i));

        System.out.println("Min x: " + fun.getPoint(0).getX());
        System.out.println("Max x: " + fun.getPoint(fun.numPoints()-1).getX());

        x = 0.5*(fun.getPoint(0).getX() + fun.getPoint(fun.numPoints()-1).getX());
        System.out.println("Значення інтерполяції fun(" + x + ") = " + fun.evalf(x));
        System.out.println("Точне значення sin(" + x + ") = " + Math.sin(x));
        System.out.println("Абсолютна помилка: " + Math.abs(fun.evalf(x)-Math.sin(x)));

        in.close();
    }
}
