package ru.kulkov.equation;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Solver solver = new EquationSolver();
        System.out.println(((EquationSolver) solver).findDiscriminant(1, 2.0000000000125, 1));
        System.out.println(Arrays.toString(solver.solve(1, 2.0000000000125, 1)));

        System.out.println(((EquationSolver) solver).findDiscriminant(1, Double.MAX_VALUE, 1));
        //System.out.println(Arrays.toString(solver.solve(1, Double.MAX_VALUE, 1)));
    }
}
