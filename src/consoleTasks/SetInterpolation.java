package consoleTasks;

import java.util.*;

public class SetInterpolation implements Evaluatable<Double> {
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

    public Point2D getFirst() {
        return data.first();
    }

    public Point2D getLast() {
        return data.last();
    }

    public void removeLastPoint() {
        if (!data.isEmpty())
            data.remove(data.last());
    }

    @Override
    public double evalf(Double x) {
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

}
