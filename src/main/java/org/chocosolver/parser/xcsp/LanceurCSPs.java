package org.chocosolver.parser.xcsp;

import org.apache.maven.surefire.shared.lang3.NotImplementedException;
import org.chocosolver.solver.search.strategy.strategy.AbstractStrategy;

import java.io.File;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class LanceurCSPs {
    private static final int NB_REPETITIONS = 1;
    private static final DecimalFormat df = new DecimalFormat("###.####");
    private static final ArrayList<LanceurCSP.ChosenHeuristic> heuristicList;
    static {
        LanceurCSP.ChosenHeuristic arrayChosenHeuristic[] = new LanceurCSP.ChosenHeuristic[]{
                LanceurCSP.ChosenHeuristic.DOMOVERWDEG,
                LanceurCSP.ChosenHeuristic.DOMOVERWDEGREF,
                LanceurCSP.ChosenHeuristic.CHS,
                LanceurCSP.ChosenHeuristic.IMPACT,
                LanceurCSP.ChosenHeuristic.RANDOM,
                LanceurCSP.ChosenHeuristic.MINDDOM,
                LanceurCSP.ChosenHeuristic.MAXDDOM,
                LanceurCSP.ChosenHeuristic.MINDOM,
                LanceurCSP.ChosenHeuristic.MAXDOM,
                LanceurCSP.ChosenHeuristic.ACTIVITY,
                LanceurCSP.ChosenHeuristic.IMPACT,
                LanceurCSP.ChosenHeuristic.CHS,
                LanceurCSP.ChosenHeuristic.CACD,
                LanceurCSP.ChosenHeuristic.DDEG,
                LanceurCSP.ChosenHeuristic.DOMDDEG,
                LanceurCSP.ChosenHeuristic.DOMDEG,
                LanceurCSP.ChosenHeuristic.ONEOVERWDEG
        };
        heuristicList = new ArrayList<>(Arrays.asList(arrayChosenHeuristic));
    }

    public static void main(String[] args) throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));

        System.out.println(args[0]);

        String dirPath = args[0];
        //"/home/tony/M1/coconut/instances/Sudoku/Sudoku-alldiff-s1";
        // /home/tony/M1/coconut/instances/TravellingSalesman-xcsp2-s25

        // Récupération de tous les paths de csp dans le dirPath
        File directoryPath = new File(dirPath);
        String contents[] = directoryPath.list();
        ArrayList<String> filesBaseName = new ArrayList<>(Arrays.asList(contents));
        ArrayList<String> filesFullPath = Arrays.stream(contents).map(nom -> dirPath+File.separator+nom).collect(Collectors.toCollection(ArrayList::new));
        Collections.sort(filesFullPath);

        for (String s : filesFullPath) {
            System.out.println(s);
        }

        StringBuilder monStringBuilder = new StringBuilder();
        monStringBuilder.append("nom;");
        for (Enum h : heuristicList) {
            System.out.println(h);
            monStringBuilder.append("nbSol"+h+";");
            monStringBuilder.append("nbVars"+h+";");
            monStringBuilder.append("nbCstrs"+h+";");
            monStringBuilder.append("temps"+h+";");
        }
        System.out.println(monStringBuilder);


        for (String aPath : filesFullPath) {
            args = new String[]{"-ansi","--log-level","SILENT","-csv",aPath};//fixme la plupart des args sert à rien vue que je print rien dans LanceurCSP
            monStringBuilder = new StringBuilder();
            monStringBuilder.append(getNetworkName(aPath));
            monStringBuilder.append(";");

            for (int indiceH=0; indiceH<heuristicList.size(); indiceH++) {
                String result[];
                LanceurCSP.ChosenHeuristic heuristicChoisit = heuristicList.get(indiceH);

                result = LanceurCSP.run(args, heuristicChoisit);


                monStringBuilder.append(result[0]);
                monStringBuilder.append(";");
                monStringBuilder.append(result[1]);
                monStringBuilder.append(";");
                monStringBuilder.append(result[2]);
                monStringBuilder.append(";");
                monStringBuilder.append(df.format(Double.valueOf(result[3])));
                monStringBuilder.append(";");
            }
            System.out.println(monStringBuilder.toString());
        }

        date = new Date();
        System.out.println(dateFormat.format(date));
    }

    private static String getNetworkName(String absolutePath) {
        int i = absolutePath.lastIndexOf(File.separator)+1;
        int ii = absolutePath.lastIndexOf(".xml");
        return absolutePath.substring(i,ii);
    }

    private static double getAverage(ArrayList<Double> tempsDeResolution) {
        double tempsMin = tempsDeResolution.stream().min(Comparator.comparing(Double::valueOf)).get();
        double tempsMax = tempsDeResolution.stream().max(Comparator.comparing(Double::valueOf)).get();
        tempsDeResolution.remove(tempsMin);
        tempsDeResolution.remove(tempsMax);
        double avg = tempsDeResolution.stream().mapToDouble(x -> x).average().getAsDouble();
        return avg;
    }

    private static ArrayList<String> getAbsolutePathFromDirPath(String dirPath) {
        throw new NotImplementedException();
    }
}
