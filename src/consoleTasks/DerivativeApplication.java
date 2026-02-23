package consoleTasks;

import java.io.*;

public class DerivativeApplication {

    public static void main(String[] args) throws IOException {
        Evaluatable funcs[] = new Evaluatable[2];
        funcs[0] = new ListInterpolation();
        funcs[1] = new FileListInterpolation();

        try {
            ((FileListInterpolation)funcs[1]).readFromFile("TblFunc.dat");
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        String fileName = "";
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
    }
}