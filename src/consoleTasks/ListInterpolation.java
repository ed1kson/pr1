package consoleTasks;
import java.util.*;

public class ListInterpolation extends Interpolator {
    private ArrayList<Point2D> data = null;
    
    public ListInterpolation(Collection<Point2D> data) {
        this.data = new ArrayList<>();
        for ( Point2D p : data ) {
            this.data.add(p.clone());
        }
    }

    public ListInterpolation() {
        this.data = new ArrayList<Point2D>();
    }

    public void addPoints(Collection<Point2D> clctn) {
        for ( Point2D p : clctn ) {
            addPoint(p);
        }
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
        return data.get(i).clone();
    }

    @Override
    public void setPoint(int i, Point2D pt) {
        data.set(i, pt);
    }

    public ArrayList<Point2D> getPoints() {
        ArrayList<Point2D> list = new ArrayList<>();
    
        for ( Point2D p : data ) {
            list.add(p.clone());
        }

        return list;
    }

    @Override
    public void removeLastPoint() {
        if (!data.isEmpty())
            data.remove(data.size() - 1);
    }

    public void sort() {
        Collections.sort(data);
    }
}
