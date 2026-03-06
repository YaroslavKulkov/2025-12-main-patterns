package ru.kulkov.equation;

public class EquationSolver implements Solver{

    private static final double EPSILON = 1e-10;

    @Override
    public double[] solve(double a, double b, double c) {
        if (!Double.isFinite(a) || !Double.isFinite(b) || !Double.isFinite(c)) {
            throw new IllegalArgumentException("Parameter a, b, c must be a real number");
        }

        if (Math.abs(a) <= EPSILON) {
            throw new IllegalArgumentException("The first parameter 'a' must not be equal to 0");
        }

        double d = findDiscriminant(a, b, c);

        if (Double.isInfinite(d)) {
            throw new IllegalArgumentException("It is impossible to find the roots with the given parameters");
        }

        if (d < 0) {
            return new double[0];
        }

        if (Math.abs(d) <= EPSILON) {
            double root = -b / (2 * a);
            return new double[]{root, root};
        }

        double root1 = (-b + Math.sqrt(d)) / 2 * a;
        double root2 = (-b - Math.sqrt(d)) / 2 * a;
        return new double[] {root1, root2};

    }

    protected double findDiscriminant(double a, double b, double c) {
        return b * b - 4 * a * c;
    }
}
