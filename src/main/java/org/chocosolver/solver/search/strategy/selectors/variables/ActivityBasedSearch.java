package org.chocosolver.solver.search.strategy.selectors.variables;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.search.loop.monitors.IMonitorDownBranch;
import org.chocosolver.solver.variables.Variable;

import java.util.HashMap;

public class ActivityBasedSearch<V extends Variable> implements IMonitorDownBranch, VariableSelector<V> {

    private int etatInitial;
    private int pas;
    private HashMap<Variable, Integer> activites;
    private HashMap<Variable, Integer> domainesAvantInstanciation;
    private HashMap<Variable, Integer> domainesApresInstanciation;

    private Model model;

    public ActivityBasedSearch(Model model, int etatInitial, int pas ){
        this.pas = pas;
        this.etatInitial = etatInitial;
        activites = new HashMap<>();
        this.model = model;

        for(Variable var : model.getVars()){
            activites.put(var, this.etatInitial);
        }


    }

    public ActivityBasedSearch(Model model){
        this(model, 0, 1);
    }

    @Override
    public V getVariable(V[] variables) {
        V meilleureVar = null;
        int plusGrosseActivite = Integer.MIN_VALUE;
        for(V var: variables){
            if(!var.isInstantiated() && var.getDomainSize()>plusGrosseActivite){
                meilleureVar = var;
            }
        }
        return meilleureVar;
    }

    @Override
    public void beforeDownBranch(boolean left) {
        // avant de passer à la branche suivante (d'avoir fait l'instanciation quoi)
        // on enregistre l'activité de chaque variable

        for(Variable var : model.getVars()){
            domainesAvantInstanciation.put(var, var.getDomainSize());
        }


    }

    @Override
    public void afterDownBranch(boolean left) {
        for(Variable var : model.getVars()){
            if(domainesAvantInstanciation.get(var) < domainesApresInstanciation.get(var) ){
                activites.put(var, activites.get(var) + this.pas);
            }
        }
    }
}
