package fr.umontpellier.etu.heuristique.variables;

import org.chocosolver.solver.search.strategy.selectors.variables.VariableEvaluator;
import org.chocosolver.solver.search.strategy.selectors.variables.VariableSelector;
import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.solver.variables.Variable;

import java.util.HashMap;
import java.util.Map;

public class DomHeuristique <V extends Variable> implements VariableSelector<V>, VariableEvaluator<V> {

    private final Map<V,Integer> initialVarsDomainSize = new HashMap<>();

    public DomHeuristique(V[] vars){
        for (V var: vars){
            initialVarsDomainSize.put(var, var.getDomainSize());
        }
    }

    @Override
    public double evaluate(V variable) {
        return initialVarsDomainSize.get(variable);
    }

    @Override
    public V getVariable(V[] variables) {
        V the_chosen_one = null;
        int min_dom_value = Integer.MAX_VALUE;
        for(V var: variables){
            if(!var.isInstantiated() && min_dom_value>initialVarsDomainSize.get(var)){
                the_chosen_one = var;
                min_dom_value = initialVarsDomainSize.get(var);
            }
        }
        if(the_chosen_one != null){
            System.out.println(the_chosen_one.getName());
        }
        return the_chosen_one;
    }

    @Override
    public boolean init() {
        return VariableSelector.super.init();
    }

    @Override
    public void remove() {
        VariableSelector.super.remove();
    }
}
