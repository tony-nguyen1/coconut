package fr.umontpellier.etu.heuristique.variables;

import org.chocosolver.solver.search.strategy.selectors.variables.VariableEvaluator;
import org.chocosolver.solver.search.strategy.selectors.variables.VariableSelector;
import org.chocosolver.solver.variables.Variable;

import java.util.*;

import static org.xcsp.common.Utilities.swap;

public class LexHeuristique <V extends Variable> implements VariableSelector<V>, VariableEvaluator<V> {

    private final Map<V,Integer> varsPriority = new HashMap<>();

    public LexHeuristique(V[] variables){
        List<V> test = Arrays.asList(variables);
        Collections.sort(test, new VComparator());
        for (int i = 0; i < test.size(); i++) {
            varsPriority.put(test.get(i),i);
        }
    }

    @Override
    public double evaluate(V variable) {
        return varsPriority.get(variable);
    }

    @Override
    public V getVariable(V[] variables) {
        V the_chosen_one = null;
        int min_idx = Integer.MAX_VALUE;
        for (V var: variables){
            if (!var.isInstantiated() && min_idx>varsPriority.get(var)){

                the_chosen_one = var;
                min_idx = varsPriority.get(var);
            }
        }
        //if (the_chosen_one != null)
        //    System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+the_chosen_one.getName());
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

    /*public void sortingAListOfV(V[] listOfVToSort){
        for (int idx=0; idx<listOfVToSort.length-1; idx++){
            for (int jdx=idx+1; jdx< listOfVToSort.length; jdx++){
                String i_var_name = listOfVToSort[idx].getName();
                String j_var_name = listOfVToSort[jdx].getName();

                int min_var_name_length = Math.min(i_var_name.length(),j_var_name.length());

                for (int idx_c = 0; idx_c<min_var_name_length; idx_c++) {
                    char i_char = i_var_name.charAt(idx_c);
                    char j_char = j_var_name.charAt(idx_c);

                    if (idx_c == min_var_name_length-1 && i_char == j_char && i_var_name.length() > j_var_name.length()) {
                        swap(listOfVToSort,idx,jdx);
                    }
                    else if (i_char > j_char){
                        swap(listOfVToSort, idx, jdx);
                        break;
                    }
                    else if (i_char < j_char)
                        break;
                }
            }
        }
    }*/

    class VComparator implements Comparator<V>{
        @Override
        public int compare(V o1, V o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }
}