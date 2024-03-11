package fr.umontpellier.etu.heuristique.variables;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.constraints.Constraint;
import org.chocosolver.solver.constraints.Propagator;
import org.chocosolver.solver.search.strategy.selectors.variables.VariableEvaluator;
import org.chocosolver.solver.search.strategy.selectors.variables.VariableSelector;
import org.chocosolver.solver.variables.Variable;

import java.util.HashMap;
import java.util.Map;

public class MinDegHeuristique <V extends Variable> implements VariableSelector<V>, VariableEvaluator<V> {

    private final Map<Variable,Integer> varsDeg = new HashMap<>();

    public MinDegHeuristique(Model model){
        for (Variable var: model.getVars()){
            varsDeg.put(var,0);
        }

        Constraint[] constraints = model.getCstrs();
        Propagator[] propagators = new Propagator[constraints.length];

        for (int idx=0; idx<constraints.length; idx++){
            propagators[idx] = constraints[idx].getPropagator(0);
        }
        for (Propagator prop: propagators) {
            for (Variable var : prop.getVars()) {
                varsDeg.replace(var, varsDeg.get(var) + 1);
            }
        }
    }
    @Override
    public double evaluate(V variable) {
        return varsDeg.get(variable);
    }

    @Override
    public V getVariable(V[] variables) {
        V skywalker = null;
        int min_deg = Integer.MAX_VALUE;

        for(V var: variables){
            if (!var.isInstantiated() && varsDeg.get(var) < min_deg){
                min_deg = varsDeg.get(var);
                skywalker = var;
            }
        }
        if (skywalker != null)
            System.out.println(skywalker.getName()+"   "+varsDeg.get(skywalker) );
        return skywalker;
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
