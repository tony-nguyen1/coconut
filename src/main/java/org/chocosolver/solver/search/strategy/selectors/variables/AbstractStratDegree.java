package org.chocosolver.solver.search.strategy.selectors.variables;

import org.chocosolver.solver.constraints.Constraint;
import org.chocosolver.solver.constraints.Propagator;
import org.chocosolver.solver.variables.Variable;


import java.util.HashSet;
import java.util.function.BiConsumer;

public abstract class AbstractStratDegree<V extends Variable> implements VariableSelector<V> {


    public Constraint[] getConstraints(Variable v){
        HashSet<Constraint> toutesLesContraintesDeLaVariable = new HashSet<>();

        v.forEachPropagator(new BiConsumer<Variable, Propagator<?>>() {
            @Override
            public void accept(Variable variable, Propagator<?> propagator) {
                toutesLesContraintesDeLaVariable.add( propagator.getConstraint() );
            }
        });

        Constraint[] result = new Constraint[toutesLesContraintesDeLaVariable.size()];
        toutesLesContraintesDeLaVariable.toArray(result);
        return result;

    }


    public Constraint[] getConstraintsUntouched(Variable v){
        // pour le dynamic degree, on garde que les contraintes qui ont au moins une variable instanciée dans leur portée

        HashSet<Constraint> toutesLesContraintesDeLaVariable = new HashSet<>();

        v.forEachPropagator(new BiConsumer<Variable, Propagator<?>>() {
            @Override
            public void accept(Variable variable, Propagator<?> propagator) {
                
                for(Variable v : propagator.getVars()){
                    if(v.isInstantiated()){
                        toutesLesContraintesDeLaVariable.add( propagator.getConstraint() );
                        break;
                    }
                }
            }
        });

        Constraint[] result = new Constraint[toutesLesContraintesDeLaVariable.size()];
        toutesLesContraintesDeLaVariable.toArray(result);
        return result;

    }


    public int getNbContraintes(Variable v){
        return this.getConstraints(v).length;
    }


    public V[] getVariables(Constraint constraint){
        HashSet<V> toutesLesVariablesDeLaPortee = new HashSet<>();

        for(Propagator<V> p : constraint.getPropagators()){
            for(V variable : p.getVars()){
                
                toutesLesVariablesDeLaPortee.add(variable);
                
            }
        }

        V[] result = null;
        toutesLesVariablesDeLaPortee.toArray(result);

        return result;




    }






}
