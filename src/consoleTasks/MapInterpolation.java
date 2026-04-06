package consoleTasks;
import java.util.*;

public class MapInterpolation implements Evaluatable {
    private TreeMap<Double, Double> data = null;
    
    public MapInterpolation(ArrayList<Point2D> data) {
        this();
        for ( Point2D p : data ) {
            this.data.put(p.getX(), p.getY());
        }
    }

    public MapInterpolation(TreeMap<Double, Double> data) {
        this();

        for ( double x : data.keySet()) {
            this.data.put(x, data.get(x));
        }
    }

    public MapInterpolation(Point2D[] data) {
        this();
        for (Point2D p : data)
            this.data.put(p.getX(), p.getY());
    }

    public MapInterpolation() {
        this.data = new TreeMap<Double, Double>();
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

    public void addPoint(Point2D p) {
        this.data.put(p.getX(), p.getY());
    }

    public void addPoint(double x, double y) {
        this.data.put(x, y);
    }

    public Point2D getPoint(int ti) {
        if (ti < 0) return null;

        int i = 0;
        for (Map.Entry<Double, Double> entry : data.entrySet()) {
            if ( i == ti ) {
                return new Point2D(entry.getKey(), entry.getValue());
            }
        }

        return null;
    }

    // public void setPoint(int i, Point2D pt) {
    //     double x = getPoints().get(i).getX();

    //     data.remove()
    //     if ()
    // }

    public ArrayList<Point2D> getPoints() {
        ArrayList<Point2D> list = new ArrayList<>();
    
        data.forEach((x, y) -> {
            list.add(new Point2D(x, y));
        });

        return list;
    }

    public void removeLastPoint() {
        if (!data.isEmpty())
            data.remove(data.lastKey());
    }

    @Override
    public double evalf(double x) {
        double res = 0.0;

        for (Map.Entry<Double, Double> cEntry : data.entrySet()) {
            double ckey = cEntry.getKey();
            double cvalue = cEntry.getValue();
            
            double numer = 1;
            double denom = 1;

            for (Map.Entry<Double, Double> entry : data.entrySet()) {
                double key = entry.getKey();
                if (key != ckey) { 
                    numer *= (x - key);
                    denom *= (ckey - key);
                }
            }
            res += cvalue * (numer / denom);
        }
        return res;
    }

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
