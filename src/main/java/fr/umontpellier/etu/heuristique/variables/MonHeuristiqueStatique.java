package fr.umontpellier.etu.heuristique.variables;

import org.chocosolver.solver.search.strategy.selectors.variables.VariableEvaluator;
import org.chocosolver.solver.search.strategy.selectors.variables.VariableSelector;
import org.chocosolver.solver.variables.Variable;

public class MonHeuristiqueStatique<V extends Variable> implements VariableSelector<V>, VariableEvaluator<V> {

    /**
     * @param v
     * @return
     */
    @Override
    public double evaluate(V v) {
        return 0;
    }

    /**
     * @param variables
     * @return
     */
    @Override
    public V getVariable(V[] variables) {
        return null;
    }

    /**
     * @return 
     */
    @Override
    public boolean init() {
        return VariableSelector.super.init();
    }

    /**
     * 
     */
    @Override
    public void remove() {
        VariableSelector.super.remove();
    }
}
