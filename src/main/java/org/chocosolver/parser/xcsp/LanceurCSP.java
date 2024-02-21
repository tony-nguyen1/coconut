package org.chocosolver.parser.xcsp;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.search.strategy.strategy.AbstractStrategy;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
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
    public static String[] run(String[] args) throws Exception {

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
//        String nomStrat = getStrategyName(xscp.getModel().getSolver().getSearch());
//        System.out.print(nomStrat+";");
        xscp.solve();


        // reset du stdout
        System.setOut(stdout);
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
        return new String[]{String.valueOf(model.getSolver().getTimeCount())};

    }

    private static String getStrategyName(AbstractStrategy strategy) {
        String strategylassName = strategy.getClass().toString();
        int i = strategylassName.lastIndexOf(".")+1;
        return strategylassName.substring(i);
    }

}
