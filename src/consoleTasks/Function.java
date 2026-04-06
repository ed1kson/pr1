package consoleTasks;

public class Function implements Evaluatable<Double> {

    private double a;

    public Function(double a) {
        this.a = a;
    }

    public Function() {
        this(1.0);
    }

    @Override
    public double evalf(Double x) {
        return Math.exp(-a*x*x)*Math.sin(x);
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }
}