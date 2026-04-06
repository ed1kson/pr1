package consoleTasks;

public class NumMethods {

    private NumMethods() { }
    
    private static double symDiff(double x, double h, Evaluatable f) {
        return (f.evalf(x+h) - f.evalf(x-h))/(2.0*h);
    }

    public static double der(double x, double tol, Evaluatable f) {
        final int MAX = 100;
        
        double h = 0.1;
        double one = symDiff(x, h, f);
        h = 0.1*h;
        double two = symDiff(x, h, f);
        int i = 0;
        double tmp;
        boolean ok;
        do {
            h = 0.1*h;
            tmp = symDiff(x, h, f);
            ok = ( Math.abs(tmp-two) >= Math.abs(two-one) ) || ( Math.abs(two-one) < tol );
            if (i > MAX) {
                System.out.print("Занадто багато кроків обчислень");
                System.exit(-1);
            }
            i += 1;
            one = two;
            two = tmp;
        } while (!ok);

        return two;
    }

}
