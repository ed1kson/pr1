package consoleTasks;

import java.util.*;
import java.io.*;

public class FileListInterpolation extends ListInterpolation {

    public FileListInterpolation() {
        super();
    }

    public void readFromFile(String path) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(path));

        String s = in.readLine();
        clear();

        while ( (s = in.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(s);
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());
            addPoint(new Point2D(x, y));
        }
        in.close();
    }

    public void writeToFile(String path) throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter(path));

        for (int i = 0; i < numPoints(); i++) {
            Point2D pt = getPoint(i);
            out.println(pt.getX() + " " + pt.getY());
        }
        out.close();
    }

}
