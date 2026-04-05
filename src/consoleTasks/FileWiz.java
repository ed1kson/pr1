package consoleTasks;

import java.util.*;
import java.io.*;

public class FileWiz {

    private FileWiz() {}

    public static ArrayList<Point2D> readFromCSV(String path) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(path));

        String string = null;
        String[] strings = null;

        ArrayList<Point2D> list = new ArrayList<>();
        while ( (string = in.readLine()) != null) {
            strings = string.split(",");
            double x = Double.parseDouble(strings[0]);
            double y = Double.parseDouble(strings[1]);
            list.add(new Point2D(x, y));
        }
        in.close();

        return list;
    }

    public static void writeToCSV(String path, ArrayList<Point2D> list) throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter(path));

        for (int i = 0; i < list.size() ; i++) {
            Point2D pt = list.get(i);
            out.println(pt.getX() + "," + pt.getY());
        }
        out.close();
    }

    
}
