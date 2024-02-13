package fr.umontpellier.etu.parser;

import org.chocosolver.parser.Parser;
public class AppChocoParser {
    public static void main(String[] args) {

        Parser parser = new Parser();
        try {
            parser.main(new String[]{"./instances/Sudoku/Sudoku-s01a-alldiff.xml"});
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
