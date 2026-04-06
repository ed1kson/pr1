package consoleTasks;

import org.matheclipse.core.eval.ExprEvaluator;
import org.matheclipse.core.interfaces.IExpr;

public class SFunction implements Evaluatable {

    //variables
    private double a;
    private ExprEvaluator evaluator = new ExprEvaluator();
    private IExpr func = null;


    public SFunction(String func, double a) {
        this.func = evaluator.eval(func);
        this.a = a;
    }

    public SFunction(String func) {
        this(func, 1.0);
    }

    @Override
    public double evalf(double x) {
        return evaluator.eval(String.format(java.util.Locale.US, "ReplaceAll(%s, {x -> %f, a -> %f})", func, x, a)).toDoubleDefault();
    }

    public SFunction derive() {
        return new SFunction(String.format("D(%s, x)", func));
    }

    public static void main(String[] args) {
        java.util.Scanner in = new java.util.Scanner(System.in);
        System.out.println("SFuncion class check ");
        SFunction fun = new SFunction("a*sin(x)", 3);
        System.out.println("Function: " + fun);
        System.out.println("Enter beginning: ");
        double xBeg = in.nextDouble();
        System.out.println("Enter ending: ");
        double xEnd = in.nextDouble();
        System.out.println("Enter step: ");
        double step = in.nextDouble();

        System.out.println("Parameter a: " + fun.getA());
        for (double x = xBeg; x <= xEnd; x += step) {
            System.out.printf("x: %6.4f\tf: %6.4f\n", x, fun.evalf(x));
        }

        SFunction derivative = fun.derive();
        System.out.println("derivative: " + derivative);

        for (double x = xBeg; x <= xEnd; x += step) {
            System.out.printf("x: %6.4f\tf: %6.4f\n", x, derivative.evalf(x));
        }

        in.close();
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    @Override
    public String toString() {
        return func.toString();
    }
}
