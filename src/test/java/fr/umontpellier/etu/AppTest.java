package fr.umontpellier.etu;

import fr.umontpellier.etu.heuristique.variables.DegHeuristique;
import fr.umontpellier.etu.heuristique.variables.DomHeuristique;
import fr.umontpellier.etu.heuristique.variables.LexHeuristique;
import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solution;
import org.chocosolver.solver.constraints.extension.Tuples;
import org.chocosolver.solver.search.loop.monitors.IMonitorSolution;
import org.chocosolver.solver.search.strategy.Search;
import org.chocosolver.solver.search.strategy.assignments.DecisionOperatorFactory;
import org.chocosolver.solver.search.strategy.selectors.values.IntDomainMin;
import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.solver.variables.Variable;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {
    Model model;
    IntVar blu;
    IntVar yel;
    IntVar gre;
    IntVar ivo;
    IntVar red;
    IntVar[] tabIntVar;
    int [][] tEq;
    Tuples tuplesInterdits;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        model = new Model("HEURISTIQUE_TEST");

        blu = model.intVar("blu", 1, 5);
        yel = model.intVar("yel", 1, 9);
        gre = model.intVar("gre", 1, 8);
        ivo = model.intVar("ivo", 1, 7);
        red = model.intVar("red", 1, 6);

        tabIntVar = new IntVar[]{
                blu,gre,ivo,red,yel
        };
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void main() {
    }

    @Test
    void domHTest(){
        model.getSolver().setSearch(Search.intVarSearch(new DomHeuristique(tabIntVar), new IntDomainMin(),tabIntVar));
        model.getSolver().solve();
    }
    @Test
    void lexHTest(){
        model.getSolver().setSearch(Search.intVarSearch(new LexHeuristique(tabIntVar), new IntDomainMin(),tabIntVar));
        model.getSolver().solve();
    }
    @Test
    void degHTest(){
        tEq = new int[][] {{1,1},{2,2},{3,3},{4,4},{5,5}};
        tuplesInterdits = new Tuples(tEq,false);

        model.table(new IntVar[]{blu,gre}, tuplesInterdits).post();
        model.table(new IntVar[]{blu,ivo}, tuplesInterdits).post();
        model.table(new IntVar[]{blu,red}, tuplesInterdits).post();
        model.table(new IntVar[]{blu,yel}, tuplesInterdits).post();
        model.table(new IntVar[]{gre,ivo}, tuplesInterdits).post();
        model.table(new IntVar[]{gre,red}, tuplesInterdits).post();
        model.table(new IntVar[]{gre,yel}, tuplesInterdits).post();
        model.table(new IntVar[]{ivo,red}, tuplesInterdits).post();
        model.table(new IntVar[]{ivo,yel}, tuplesInterdits).post();
        model.table(new IntVar[]{red,yel}, tuplesInterdits).post();

        model.getSolver().setSearch(Search.intVarSearch(new DegHeuristique(model), new IntDomainMin(),tabIntVar));
        model.getSolver().solve();
    }
}

