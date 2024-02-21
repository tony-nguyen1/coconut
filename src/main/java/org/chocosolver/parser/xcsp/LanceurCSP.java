package org.chocosolver.parser.xcsp;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.search.strategy.Search;
import org.chocosolver.solver.search.strategy.selectors.values.IntDomainMin;
import org.chocosolver.solver.search.strategy.selectors.variables.GeneralizedMinDomVarSelector;
import org.chocosolver.solver.search.strategy.selectors.variables.ImpactBased;
import org.chocosolver.solver.search.strategy.strategy.AbstractStrategy;
import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.solver.variables.Variable;
import org.chocosolver.util.tools.VariableUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/***
 * Résoud un réseau de contrainte
 *
 * Entrée : des arguments dont le path
 * Sortie : tableau de string avec le temps de calcul
 */
public class LanceurCSP {
    private final static PrintStream stdout = System.out;
    private final static ByteArrayOutputStream baos = new ByteArrayOutputStream();

    public static enum ChosenHeuristic {
        DEFAULT,//LastConflict
        DOM,
        DOMOVERWDEG,
        DOMOVERWDEGREF,
        ACTIVITY,
        IMPACT,
        CHS
    }

    public static void main(String[] args) throws Exception {
        String param[] = new String[]{"/home/tony/M1/coconut/instances/Queens/Queens-m1-s1/Queens-0020-m1.xml.lzma"};
        System.out.print(param[0]+";");
        ChosenHeuristic h = ChosenHeuristic.DOM;
        System.out.println(run(param,h)[0]);
    }
    public static String[] run(String[] args,ChosenHeuristic chosenHeuristic) throws Exception {

        // redirection de stdout vers variable perso afin de récupérer les prints des classes que je controle pas
        System.setOut(new PrintStream(baos));

        XCSP xscp = new XCSP();
        xscp.setUp(args);
        xscp.createSolver();
        xscp.buildModel();
        Model model = xscp.getModel();
        xscp.configureSearch();

        // ajout d'information le sdtout
//        System.out.println("strat;hasFoundSol;nbSol;readingTime;time;timeToBestSol;hasObjective;nbNoeuds;nbBacktrack;nbBackjump;nbFail;nbRestart");
//        System.out.print(nomStrat+";");


        // Récupération des variables types int
        // Recopier depuis BlackBoxConfigurator
        List<IntVar> livars = new ArrayList<>(); // integer and boolean variables
        for (Variable var : model.getVars()) {
            if (VariableUtils.isConstant(var)) continue;
            if (VariableUtils.isView(var) && false) continue;
            int type = var.getTypeAndKind();
            int kind = type & Variable.KIND;
            switch (kind) {
                case Variable.BOOL:
                case Variable.INT:
                    livars.add((IntVar) var);
                    break;
            }
        }

        IntVar tabIntVar[] = livars.toArray(new IntVar[livars.size()]);
        switch (chosenHeuristic) {
            case DEFAULT:

                break;
            case CHS:
                model.getSolver().setSearch(Search.conflictHistorySearch(livars.toArray(new IntVar[livars.size()])));
                break;
            case DOM:
                model.getSolver().setSearch(Search.intVarSearch(new GeneralizedMinDomVarSelector<>(false),new IntDomainMin(), tabIntVar));
                break;
            case IMPACT:
                model.getSolver().setSearch(new ImpactBased(tabIntVar,null,0,0,0,0,true));
                break;
            case ACTIVITY:
                model.getSolver().setSearch(Search.activityBasedSearch(tabIntVar));
                break;
            case DOMOVERWDEG:
                model.getSolver().setSearch(Search.domOverWDegSearch(tabIntVar));
                break;
            case DOMOVERWDEGREF:
                model.getSolver().setSearch(Search.domOverWDegRefSearch(tabIntVar));
                break;
        }


        xscp.solve();


        // reset du stdout
        System.setOut(stdout);
//        System.out.println(chosenHeuristic);
//        System.out.println(baos.toString());
//        System.out.println(model.getName());

//        String PATH_LINUX = "/root/with space/subDir/myFile.linux";
//        int index = PATH_LINUX.lastIndexOf(File.separator);
//        String filenameLinux = PATH_LINUX.substring(index + 1);
//        System.out.println(filenameLinux);
//
//        String fullClassName = "class org.chocosolver.solver.search.strategy.strategy.LastConflict";
//        int i = fullClassName.lastIndexOf(".")+1;
//        System.out.println(fullClassName.substring(i));

        String nomStrat = getStrategyName(xscp.getModel().getSolver().getSearch());
//        System.out.println(nomStrat);
        return new String[]{String.valueOf(model.getSolver().getTimeCount())};

    }

    private static String getStrategyName(AbstractStrategy strategy) {
        String strategylassName = strategy.getClass().toString();
        int i = strategylassName.lastIndexOf(".")+1;
        return strategylassName.substring(i);
    }

}
