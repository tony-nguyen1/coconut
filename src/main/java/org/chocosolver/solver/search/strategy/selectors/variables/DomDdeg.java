package org.chocosolver.solver.search.strategy.selectors.variables;

import org.chocosolver.solver.variables.Variable;

public class DomDdeg<V extends Variable> extends AbstractStratDegree<V> {

/*
 * Dom/Ddeg (Bessiere & R´egin, 1996), which improves MinDom by taking the dynamic degree (Ddeg) of
a variable into account, and selects the variable with the minimum ratio between Dom and Ddeg. To
compute the Ddeg of a variable xi, this heuristic first identifies the set of constraints involving xi, i.e.
C(xi) = {cj ∈ C|xi ∈ scp(cj )}; then it removes from C(xi) those constraints that involve no unbounded
variables, and uses the cardinality of C(xi) as the Ddeg value of xi"
"c'est le degré mais on garde que les contraintes qui ont déjà un peu de leurs variables instanciées
on prend pas en compte dans le degré si c'est une contrainte "toute neuve" 


sur le lien 
https://arxiv.org/pdf/1912.10762.pdf
 * 
 * 
 * 
 * 
 */

    @Override
    public V getVariable(V[] variables) {
        
        V meilleureVariable = null;
        double plusPetitRatio = Double.MAX_VALUE;

        for(V v : variables){
            if( ! v.isInstantiated()) {
                    if (meilleureVariable == null)
                        meilleureVariable = v; // c'est pour instancier au moins une fois

                    int ddeg = this.getConstraintsUntouched(v).length;
                    double ratio;
                    if (ddeg > 0) {
                        ratio = v.getDomainSize() / this.getConstraintsUntouched(v).length;

                        if (ratio < plusPetitRatio) {
                            plusPetitRatio = ratio;
                            meilleureVariable = v;
                        }
                    }
            }
        }

        return meilleureVariable;


    }
    
}
