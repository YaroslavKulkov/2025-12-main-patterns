package ru.kulkov.equation;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EquationSolverTest {
    private static Solver solver;
    @BeforeAll
    static void init(){
        solver = new EquationSolver();
    }

    @Test
    void solve_WhenAOneBZeroCOne_ReturnsEmptyArray() {
        //Написать тест, который проверяет, что для уравнения x^2+1 = 0 корней нет (возвращается пустой массив)
        double[] actual = solver.solve(1, 0,1);
        double[] expected = new double[0];
        assertArrayEquals(expected, actual, "Должен вернуться пустой массив");
    }

    @Test
    void solve_WhenAOneBZeroCMinusOne_ReturnsRoots(){
        //Написать тест, который проверяет, что для уравнения x^2-1 = 0 есть два корня кратности 1 (x1=1, x2=-1)
        double[] actual = solver.solve(1, 0,-1);
        double[] expected = new double[]{1, -1};
        assertArrayEquals(expected, actual, "Должны быть получены корни (x1=1, x2=-1)");
    }

    @Test
    void solve_WhenAOneBTwoCOne_ReturnOneRoot() {
        //Написать тест, который проверяет, что для уравнения x^2+2x+1 = 0 есть один корень кратности 2 (x1= x2 = -1)
        double[] actual = solver.solve(1, 2,1);
        double[] expected = new double[]{-1, -1};
        assertArrayEquals(expected, actual, "Должны быть получены корни (x1=-1, x2=-1)");
    }

    @Test
    void solve_WhenDNeaZero_ReturnOneRoot() {
        //С учетом того, что дискриминант тоже нельзя сравнивать с 0 через знак равенства,
        // подобрать такие коэффициенты квадратного уравнения для случая одного корня кратности два,
        // чтобы дискриминант был отличный от нуля, но меньше заданного эпсилон.
        double[] actual = solver.solve(1, 2.0000000000125,1);
        double[] expected = new double[]{-1, -1};
        assertArrayEquals(expected, actual, 1e-10,"Должны быть получены корни (x1=-1, x2=-1)");
    }

    @Test
    void solve_WhenAZero_ThrowException(){
        //Написать тест, который проверяет, что коэффициент a не может быть равен 0. В этом случае solve выбрасывает исключение.
        assertThrows(
                IllegalArgumentException.class,
                ()->solver.solve(0, 5, 5),
                "При а=0 должно выбрасываться исключение IllegalArgumentException"
        );
    }

    @Test
    void solve_WhenANearZero_ThrowException(){
        //Написать тест, который проверяет, что коэффициент a не может быть равен 0. В этом случае solve выбрасывает исключение.
        assertThrows(
                IllegalArgumentException.class,
                ()->solver.solve(Double.MIN_VALUE, 5, 5),
                "При а=0 должно выбрасываться исключение IllegalArgumentException"
        );
    }

    @Test
    void solve_WhenBMaxValue_ThrowException() {
        assertThrows(
                IllegalArgumentException.class,
                ()->solver.solve(1, Double.MAX_VALUE, 1),
                "При MAX_VALUE должно выбрасываться исключение IllegalArgumentException"
        );
    }
}
