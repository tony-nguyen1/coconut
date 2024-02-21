package org.chocosolver.parser.xcsp;

import org.chocosolver.solver.search.strategy.strategy.AbstractStrategy;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

public class LanceurCSPs {
    public static void main(String[] args) throws Exception {
        String dirPath = "/home/tony/M1/coconut/instances/Sudoku/Sudoku-alldiff-s1";

        //Creating a File object for directory
        File directoryPath = new File(dirPath);
        //List of all files and directories
        String contents[] = directoryPath.list();
//        System.out.println("List of files and directories in the specified directory:");
        for(int i=0; i<contents.length; i++) {
//            System.out.println(contents[i]);
        }

        ArrayList<String> filesBaseName = new ArrayList<>(Arrays.asList(contents));
        System.out.println(filesBaseName);

        ArrayList<String> filesFullPath = Arrays.stream(contents).map(nom -> dirPath+File.separator+nom).collect(Collectors.toCollection(ArrayList::new));

//        System.out.println(filesFullPath);

        for (String aPath : filesFullPath) {
            args = new String[]{"-ansi","--log-level","SILENT","-csv",aPath};
//            System.out.println(Arrays.stream(args).collect(Collectors.toList()));
            StringBuilder monStringBuilder = new StringBuilder();
            monStringBuilder.append(getNetworkName(aPath));
//            System.out.println(getNetworkName(aPath));
            ArrayList<Double> tempsDeResolution = new ArrayList<>(5);
            for (int i=0;i<5;i++) {
                tempsDeResolution.add(Double.valueOf(LanceurCSP.run(args)[0]));
            }
            double tempsMin = tempsDeResolution.stream().min(Comparator.comparing(Double::valueOf)).get();
            double tempsMax = tempsDeResolution.stream().max(Comparator.comparing(Double::valueOf)).get();
            tempsDeResolution.remove(tempsMin);
            tempsDeResolution.remove(tempsMax);
            DecimalFormat df = new DecimalFormat("###.###");
            double avg = tempsDeResolution.stream().mapToDouble(x -> x).average().getAsDouble();
//            avg = Double.parseDouble(df.format(avg));

            monStringBuilder.append(";");
            monStringBuilder.append(df.format(avg));
            System.out.println(monStringBuilder.toString());

//            System.out.println("avg="+df.format(avg)+" "+"min="+tempsMin+" max="+tempsMax);
        }


    }

    private static String getNetworkName(String absolutePath) {
        int i = absolutePath.lastIndexOf(File.separator)+1;
        int ii = absolutePath.lastIndexOf(".xml");
        return absolutePath.substring(i,ii);
    }
}
