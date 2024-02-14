package org.chocosolver.solver.search.strategy.selectors.variables;

import org.chocosolver.solver.constraints.Propagator;
import org.chocosolver.solver.variables.Variable;

import java.util.logging.Logger;

public class CustomDomOverWDeg<V extends Variable> extends DomOverWDeg<V>{

    Logger calcLogger = Logger.getLogger("");
    public CustomDomOverWDeg(V[] variables, long seed) {
        super(variables, seed);
    }

    public CustomDomOverWDeg(V[] variables, long seed, int flushThs) {
        super(variables, seed, flushThs);
    }

    @Override
    void increase(Propagator<?> prop, Element elt, double[] ws) {
        calcLogger.info("test");
        super.increase(prop,elt,ws);

        /* Je pense que prop c'est la "contrainte" qui a engendré un conflit */
        System.out.println(prop);

//        boolean trouveUn = false;
        for (Variable v : prop.getModel().getVars()) {
            if (!v.isInstantiated()) {
//                trouveUn = true;
                System.out.println(v.getName()+"n'est pas instancié!!!!!!!!!!!!!!!!!!!!!!!!!");
            }
        }
//        System.out.println(trouveUn);
////
        for (Variable v : prop.getVars()) {
            System.out.println("var="+v);
            System.out.println("size="+v.getDomainSize());
            System.out.println("scope("+v.getName()+")="+v.getNbProps());
        }
        System.out.println("arity=("+prop+")="+prop.getNbVars());
//        System.out.println("ws.length="+ws.length);
//        System.out.println(prop.getNbVars());
    }
}
