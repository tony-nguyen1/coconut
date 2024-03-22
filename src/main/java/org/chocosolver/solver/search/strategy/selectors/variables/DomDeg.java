package org.chocosolver.solver.search.strategy.selectors.variables;

import java.util.ArrayList;

import org.chocosolver.solver.variables.Variable;

public class DomDeg<V extends Variable> extends AbstractStratDegree<V> {

    


    @Override
    public V getVariable(V[] variables) {

        ArrayList<V> meilleuresVariables = new ArrayList<>();
        int minDom = Integer.MAX_VALUE;

        for(V v : variables){
            int tailleDomaine = v.getDomainSize();
            if(tailleDomaine < minDom){
                minDom = tailleDomaine;
                meilleuresVariables.clear();
                
            }
            if( tailleDomaine == minDom){
                meilleuresVariables.add(v);
            }
        } // la liste contient les variables avec les plus petits domaines,
        // mtn on choisit celles qui ont le + grand degré
        
        int maxDeg = Integer.MIN_VALUE;
        V best_variable = null;
        for(V v: meilleuresVariables){
            int deg = this.getNbContraintes(v);
            if (deg > maxDeg){
                maxDeg = deg;
                best_variable = v;
            }
        }

        return best_variable;


    }
    
}
