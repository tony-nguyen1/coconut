package fr.umontpellier.etu.parser;

import org.chocosolver.parser.Parser;
import org.chocosolver.parser.SetUpException;
import org.chocosolver.parser.xcsp.XCSP;
import org.chocosolver.parser.xcsp.XCSPParser;
import org.chocosolver.parser.RegParser;

import java.util.MissingFormatArgumentException;

/**
 * /home/tony/M1/coconut/instances/Queens/Queens-0004-m1.xml
 */
public class AppChocoParser {
    public static void main(String[] args) throws Exception {

        if (args.length < 1) throw new MissingFormatArgumentException("usage: ... path/csp.xml");

        Parser parser = new Parser();
//        parser.main(new String[]{args[0]});
        parser.main(new String[]{"/home/tony/M1/coconut/instances/Queens/Queens-m1-s1/Queens-0500-m1.xml.lzma"});

//        XCSP xscp = new XCSP();
//        xscp.addListener(new BaseXCSPListener(xscp));
//        xscp.setUp(args);
//        xscp.createSolver();
//        xscp.buildModel();
//        xscp.configureSearch();
//        xscp.solve();
    }
}
