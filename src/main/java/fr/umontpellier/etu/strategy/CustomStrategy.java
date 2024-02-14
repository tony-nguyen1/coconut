package fr.umontpellier.etu.strategy;

import org.chocosolver.solver.search.strategy.assignments.DecisionOperator;
import org.chocosolver.solver.search.strategy.selectors.values.IntValueSelector;
import org.chocosolver.solver.search.strategy.selectors.variables.VariableSelector;
import org.chocosolver.solver.search.strategy.strategy.IntStrategy;
import org.chocosolver.solver.variables.IntVar;

@Deprecated
public class CustomStrategy extends IntStrategy {
    public CustomStrategy(IntVar[] scope, VariableSelector<IntVar> varSelector, IntValueSelector valSelector) {
        super(scope, varSelector, valSelector);
    }

    public CustomStrategy(IntVar[] scope, VariableSelector<IntVar> varSelector, IntValueSelector valSelector, DecisionOperator<IntVar> decOperator) {
        super(scope, varSelector, valSelector, decOperator);
    }
}
