package fr.umontpellier.etu;


import org.chocosolver.solver.Solver;
import org.chocosolver.solver.search.restart.AbstractRestart;

/**
 *
 * Politique de restart. Désactive les restarts.
 *
 * https://javadoc.io/static/org.choco-solver/choco-solver/4.10.14/org.chocosolver.solver/org/chocosolver/solver/search/restart/AbstractRestart.html
 */
@Deprecated
public class NoRestart extends AbstractRestart {
    @Override
    public boolean mustRestart(Solver solver) {
        return false; // j'espère que ça marche
    }
}
