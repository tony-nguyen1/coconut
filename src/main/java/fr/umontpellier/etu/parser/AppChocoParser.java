package fr.umontpellier.etu.parser;

import org.chocosolver.parser.Parser;

import java.util.MissingFormatArgumentException;

public class AppChocoParser {
    public static void main(String[] args) {

        if (args.length < 1) throw new MissingFormatArgumentException("usage: ... path/csp.xml");

        Parser parser = new Parser();
        try {
            parser.main(new String[]{args[0]});
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
