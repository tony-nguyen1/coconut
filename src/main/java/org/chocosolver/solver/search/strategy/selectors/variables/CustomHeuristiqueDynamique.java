//package fr.umontpellier.etu.heuristique.variables;
package org.chocosolver.solver.search.strategy.selectors.variables;// obliger pour pouvoir avec accès à AbstractCriterionBasedVariableSelector.Element

import org.chocosolver.solver.constraints.Propagator;
import org.chocosolver.solver.learn.ExplanationForSignedClause;
import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.solver.variables.Variable;

import java.util.function.Consumer;

public class CustomHeuristiqueDynamique<V extends Variable> extends AbstractCriterionBasedVariableSelector<V> {
    public CustomHeuristiqueDynamique(V[] vars, long seed, int flush) {
        super(vars, seed, flush);
    }

    /**
     * @param v 
     * @return
     */
    @Override
    protected double weight(V v) {
        return 0;
    }

    /**
     * @param propagator
     * @param element
     * @param doubles
     */
    @Override
    void increase(Propagator<?> propagator, Element element, double[] doubles) {

    }

    /**
     * @param pivot 
     * @param explanation
     */
    @Override
    public void explain(int pivot, ExplanationForSignedClause explanation) {
        super.explain(pivot, explanation);
    }

    /**
     * @param action 
     */
    @Override
    public void forEachIntVar(Consumer<IntVar> action) {
        super.forEachIntVar(action);
    }

    /**
     * 
     */
    @Override
    public void beforeRestart() {
        super.beforeRestart();
    }

    /**
     * 
     */
    @Override
    public void afterRestart() {
        super.afterRestart();
    }

    /**
     * @return 
     */
    @Override
    public boolean init() {
        return super.init();
    }

    /**
     * 
     */
    @Override
    public void remove() {
        super.remove();
    }
}
