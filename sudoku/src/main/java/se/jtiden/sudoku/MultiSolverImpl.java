package main.java.se.jtiden.sudoku;

import java.util.Collection;

public class MultiSolverImpl implements MultiSolver{

    private final Collection<Solver> solvers;

    protected MultiSolverImpl(Collection<Solver> solvers) {
        this.solvers = solvers;
    }

    @Override
    public boolean processOne() {
        boolean success = false;
        for (Solver solver : solvers) {
            success = solver.trySolve();

            if (success) {
               return true;
            }
        }

        System.out.println("[MultiSolverImpl] Couldn't solve this one. :(");
        return false;
    }
}
