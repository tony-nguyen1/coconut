package org.chocosolver.parser.xcsp;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
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
            System.out.println(aPath);
            LanceurCSP.main(args);
        }


    }
}
