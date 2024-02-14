package fr.umontpellier.etu.parser;

public class App {
    public static void main(String[] args) {
        try {
            new CustomParser("./instances/Sudoku/Sudoku-s01a-alldiff.xml");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
