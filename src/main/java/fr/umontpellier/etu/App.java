package fr.umontpellier.etu;


import org.chocosolver.solver.Model;
import org.xcsp.common.Utilities;
import org.xcsp.parser.XParser;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args ) throws Exception
    {
        XParser parser = new XParser(Utilities.loadDocument("instances/AllInterval-005.xml"));
        parser.parseVariables();
        System.out.println(parser.vEntries);


    }
}
