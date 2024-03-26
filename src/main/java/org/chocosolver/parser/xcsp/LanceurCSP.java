package org.chocosolver.parser.xcsp;

import fr.umontpellier.etu.heuristique.variables.DegHeuristique;
import fr.umontpellier.etu.heuristique.variables.DomHeuristique;
import fr.umontpellier.etu.heuristique.variables.LexHeuristique;
import fr.umontpellier.etu.heuristique.variables.MaxDomHeuristique;
import org.chocosolver.solver.Model;
import org.chocosolver.solver.search.strategy.Search;
import org.chocosolver.solver.search.strategy.selectors.values.IntDomainMin;
import org.chocosolver.solver.search.strategy.selectors.variables.*;
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
        MINDDOM,
        MAXDDOM,
        DOMOVERWDEG,
        DOMOVERWDEGREF,
        ACTIVITY,
        IMPACT,
        CHS,
        MINDOM, MAXDOM, RANDOM,
        //
        CACD,
        DDEG,
        DOMDDEG,
        DOMDEG,
        ONEOVERWDEG,
        DEG,
        LEX
    }

    public static void main(String[] args) throws Exception {
        String param[] = new String[]{"/home/tony/M1/coconut/instances/Queens/Queens-m1-s1/Queens-0020-m1.xml.lzma"};
        System.out.print(param[0]+";");
        ChosenHeuristic h = ChosenHeuristic.MINDDOM;
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
            case MINDDOM: // minddom
                model.getSolver().setSearch(Search.intVarSearch(new GeneralizedMinDomVarSelector<>(),new IntDomainMin(), tabIntVar));
                break;
            case MAXDDOM: // maxddom
                model.getSolver().setSearch(Search.intVarSearch(new GeneralizedMinDomVarSelector<>(false),new IntDomainMin(), tabIntVar));
            case IMPACT:
                model.getSolver().setSearch(new ImpactBased(tabIntVar,null,0,0,0,0,true));
                break;
            case ACTIVITY:
                //model.getSolver().setSearch(Search.activityBasedSearch(tabIntVar));//FIXME
                model.getSolver().setSearch(Search.intVarSearch(new ActivityBasedSearch<>(model,0,1), new IntDomainMin(), tabIntVar));
                break;
            case DOMOVERWDEG:
                model.getSolver().setSearch(Search.domOverWDegSearch(tabIntVar));
                break;
            case DOMOVERWDEGREF:
                model.getSolver().setSearch(Search.domOverWDegRefSearch(tabIntVar));
                break;
            case RANDOM:
                model.getSolver().setSearch(Search.randomSearch(tabIntVar,0));
                break;
            case MINDOM:
                model.getSolver().setSearch(Search.intVarSearch(new DomHeuristique<>(tabIntVar), new IntDomainMin(),tabIntVar));
                break;
            case MAXDOM:
                model.getSolver().setSearch(Search.intVarSearch(new MaxDomHeuristique<>(tabIntVar), new IntDomainMin(),tabIntVar));
                break;
            case CACD:
                model.getSolver().setSearch(Search.intVarSearch(new CaCd<>(tabIntVar), new IntDomainMin(), tabIntVar));
                break;
            case DDEG:
                model.getSolver().setSearch(Search.intVarSearch(new Ddeg<>(), new IntDomainMin(), tabIntVar));
                break;
            case DOMDDEG:
                model.getSolver().setSearch(Search.intVarSearch(new DomDdeg<>(), new IntDomainMin(), tabIntVar));
                break;
            case DOMDEG:
                model.getSolver().setSearch(Search.intVarSearch(new DomDeg<>(), new IntDomainMin(), tabIntVar));
                break;
            case ONEOVERWDEG:
                model.getSolver().setSearch(Search.intVarSearch(new OneOverWdeg<>(tabIntVar), new IntDomainMin(), tabIntVar));
                break;
            case DEG:
                model.getSolver().setSearch(Search.intVarSearch(new DegHeuristique(model), new IntDomainMin(), tabIntVar));
                break;
            case LEX:
                model.getSolver().setSearch(Search.intVarSearch(new LexHeuristique<>(tabIntVar), new IntDomainMin(), tabIntVar));
                break;
        }


        model.getSolver().clearRestarter();
        model.getSolver().limitTime("1h");
        xscp.solve();


        // reset du stdout
        System.setOut(stdout);
//        System.out.println(baos);
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
//        model.getSolver().printStatistics();
        String nbSol = String.valueOf(model.getSolver().getSolutionCount());
        String nbVars = String.valueOf(model.getNbVars());
        String nbCstrs = String.valueOf(model.getNbCstrs());
        String temps = String.valueOf(model.getSolver().getTimeCount());

        return new String[]{nbSol,nbVars,nbCstrs,temps};

    }

    private static String getStrategyName(AbstractStrategy strategy) {
        String strategylassName = strategy.getClass().toString();
        int i = strategylassName.lastIndexOf(".")+1;
        return strategylassName.substring(i);
    }

}
