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
    private static final int NB_REPETITIONS = 5;
    private static final DecimalFormat df = new DecimalFormat("###.####");
    private static final ArrayList<LanceurCSP.ChosenHeuristic> heuristicList;
    static {
        LanceurCSP.ChosenHeuristic arrayChosenHeuristic[] = new LanceurCSP.ChosenHeuristic[]{
                LanceurCSP.ChosenHeuristic.DEFAULT,
                LanceurCSP.ChosenHeuristic.DOM,
                LanceurCSP.ChosenHeuristic.DOMOVERWDEG,
                LanceurCSP.ChosenHeuristic.CHS
        };
        heuristicList = new ArrayList<>(Arrays.asList(arrayChosenHeuristic));
    }

    public static void main(String[] args) throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));

        String dirPath = args[0];
        //"/home/tony/M1/coconut/instances/Sudoku/Sudoku-alldiff-s1";

        // Récupération de tous les paths de csp dans le dirPath
        File directoryPath = new File(dirPath);
        String contents[] = directoryPath.list();
        ArrayList<String> filesBaseName = new ArrayList<>(Arrays.asList(contents));
        ArrayList<String> filesFullPath = Arrays.stream(contents).map(nom -> dirPath+File.separator+nom).collect(Collectors.toCollection(ArrayList::new));


        for (String aPath : filesFullPath) {
            args = new String[]{"-ansi","--log-level","SILENT","-csv",aPath};//fixme la plupart des args sert à rien vue que je print rien dans LanceurCSP
            StringBuilder monStringBuilder = new StringBuilder();
            monStringBuilder.append(getNetworkName(aPath));

            for (int indiceH=0; indiceH<heuristicList.size(); indiceH++) {
                ArrayList<Double> tempsDeResolution = new ArrayList<>(NB_REPETITIONS);
                String result[] = new String[2];
                LanceurCSP.ChosenHeuristic heuristicChoisit = heuristicList.get(indiceH);

                for (int i=0;i<NB_REPETITIONS;i++) {
                    result = LanceurCSP.run(args, heuristicList.get(indiceH));
                    tempsDeResolution.add(Double.valueOf(result[0]));
                }

                monStringBuilder.append(";");
                monStringBuilder.append(df.format(getAverage(tempsDeResolution)));
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
