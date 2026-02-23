package consoleTasks;
import java.util.*;

public class ListInterpolation extends Interpolator {
    private List<Point2D> data = null;
    
    public ListInterpolation(List<Point2D> data) {
        this.data = data;
    }

    public ListInterpolation(Point2D[] data) {
        this();
        for (Point2D pt : data)
            this.data.add(pt);
    }

    public ListInterpolation() {
        this.data = new ArrayList<Point2D>();
    }

    @Override
    public void clear() {
        data.clear();
    }

    @Override
    public int numPoints() {
        return data.size();
    }

    @Override
    public void addPoint(Point2D pt) {
        data.add(pt);
    }

    @Override
    public Point2D getPoint(int i) {
        return data.get(i);
    }

    @Override
    public void setPoint(int i, Point2D pt) {
        data.set(i, pt);
    }

    @Override
    public void removeLastPoint() {
        if (!data.isEmpty())
            data.remove(data.size() - 1);
    }

    @Override
    public void sort() {
        Collections.sort(data);
    }

    //Dodaty perevirku reshty methodiv ta pereviryty yak kilkist tochok vplavaye na rezultaty interpolacii
    public static void main(String[] args) {
        ListInterpolation fun = new ListInterpolation();
        int num;
        double x;

        java.util.Scanner in = new java.util.Scanner(System.in);
        do {
            System.out.print("Quantitiy of points: ");
            num = in.nextInt();
        } while (num <= 0);

        for (int i = 0; i < num; i++) {
            x = 1.0 + (5.0 - 1.0)*Math.random();
            fun.addPoint(new Point2D(x, Math.sin(x)));
        }

        System.out.println("Interpolatia po " + fun.numPoints() + " tochkam");

        System.out.println("Unsorted data:");
        for (int i = 0; i < fun.numPoints(); i++)
            System.out.println("Point" + (i+1) + ": " + fun.getPoint(i));

        fun.sort();
        System.out.println("Sorted data:");
        for (int i = 0; i < fun.numPoints(); i++)
            System.out.println("Point" + (i+1) + ": " + fun.getPoint(i));

        System.out.println("Min x: " + fun.getPoint(0).getX());
        System.out.println("Max x: " + fun.getPoint(fun.numPoints()-1).getX());

        x = 0.5*(fun.getPoint(0).getX() + fun.getPoint(fun.numPoints()-1).getX());
        System.out.println("Znachennya interpolatii fun(" + x + ") = " + fun.evalf(x));
        System.out.println("Tochne znachennya sin(" + x + ") = " + Math.sin(x));
        System.out.println("Absolutna pomylka: " + Math.abs(fun.evalf(x)-Math.sin(x)));

        in.close();
    }
}
