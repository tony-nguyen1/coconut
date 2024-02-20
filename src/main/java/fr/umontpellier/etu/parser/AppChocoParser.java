package fr.umontpellier.etu.parser;

import org.chocosolver.parser.Parser;
import org.chocosolver.parser.SetUpException;
import org.chocosolver.parser.xcsp.XCSP;
import org.chocosolver.parser.xcsp.XCSPParser;
import org.chocosolver.parser.RegParser;

import java.util.MissingFormatArgumentException;

public class AppChocoParser {
    public static void main(String[] args) throws Exception {

        if (args.length < 1) throw new MissingFormatArgumentException("usage: ... path/csp.xml");

        Parser parser = new Parser();
        parser.main(new String[]{args[0]});

//        XCSP xscp = new XCSP();
//        xscp.addListener(new BaseXCSPListener(xscp));
//        xscp.setUp(args);
//        xscp.createSolver();
//        xscp.buildModel();
//        xscp.configureSearch();
//        xscp.solve();
    }
}
