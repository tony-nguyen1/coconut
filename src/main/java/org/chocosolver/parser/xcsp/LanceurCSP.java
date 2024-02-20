package org.chocosolver.parser.xcsp;

import org.chocosolver.solver.search.strategy.strategy.AbstractStrategy;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.regex.Pattern;

public class LanceurCSP {
    public static void main(String[] args) throws Exception {

//        for (String s : args) {
//            System.out.println(s);
//        }

        // redirection de stdout vers variable perso afin de récupérer les prints des classes que je controle pas
        PrintStream stdout = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));

        XCSP xscp = new XCSP();
//        xscp.addListener(new BaseXCSPListener(xscp)); FIXME ce truc exister sur le github mais est inconu ailleurs ???explication???
//        /home/tony/M1/coconut/instances/Sudoku/Sudoku-alldiff-s1
//        args = new String[]{"-ansi","--log-level","SILENT","-csv","./instances/Sudoku/Sudoku-s01a-alldiff.xml"};
//        args = new String[]{"-ansi","--log-level","SILENT","-csv","./instances/Sudoku/Sudoku-s01a-alldiff.xml"};
//        args = new String[]{"-ansi","--log-level","SILENT","-csv","/home/tony/M1/coconut/instances/Sudoku/Sudoku-alldiff-s1/Sudoku-s01a-alldiff.xml.lzma"};
        xscp.setUp(args);
        xscp.createSolver();
        xscp.buildModel();
        xscp.configureSearch();

        // ajout d'information le sdtout
        System.out.println("strat;hasFoundSol;nbSol;readingTime;time;timeToBestSol;hasObjective;nbNoeuds;nbBacktrack;nbBackjump;nbFail;nbRestart");
        String nomStrat = getStrategyName(xscp.getModel().getSolver().getSearch());
        System.out.print(nomStrat+";");
        xscp.solve();


        // reset du stdout
        System.setOut(stdout);
        System.out.println(baos.toString());

//        String PATH_LINUX = "/root/with space/subDir/myFile.linux";
//        int index = PATH_LINUX.lastIndexOf(File.separator);
//        String filenameLinux = PATH_LINUX.substring(index + 1);
//        System.out.println(filenameLinux);
//
//        String fullClassName = "class org.chocosolver.solver.search.strategy.strategy.LastConflict";
//        int i = fullClassName.lastIndexOf(".")+1;
//        System.out.println(fullClassName.substring(i));

    }

    private static String getStrategyName(AbstractStrategy strategy) {
        String strategylassName = strategy.getClass().toString();
        int i = strategylassName.lastIndexOf(".")+1;
        return strategylassName.substring(i);
    }

}
