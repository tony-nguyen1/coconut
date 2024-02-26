package fr.umontpellier.etu.parser;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.variables.IntVar;
import org.w3c.dom.Document;
import org.xcsp.common.Utilities;
import org.xcsp.parser.XParser;
import org.xcsp.parser.callbacks.XCallbacks2;
import org.xcsp.parser.entries.XVariables;

import org.chocosolver.parser.xcsp.XCSPParser;

import java.util.LinkedHashMap;
import java.util.Map;

@Deprecated
public class CustomParser implements XCallbacks2 {
    private Implem implem = new Implem(this);

    @Override
    public Implem implem() { return implem; }

    private Map<XVariables.XVarInteger, IntVar> mapVar = new LinkedHashMap<>();

    @Override
    public void buildVarInteger(XVariables.XVarInteger xx, int minValue, int maxValue) {
        Model model = new Model();
        System.out.println("min="+minValue+" max="+maxValue);
        System.out.println(xx);
        IntVar x = model.intVar(xx.toString(),minValue,maxValue);
    }

    public CustomParser(String filename) throws Exception {
        Document doc = Utilities.loadDocument(filename);
        XParser parser = new XParser(doc);
        parser.vEntries.stream().forEach(e -> System.out.println(e.toString()));
        parser.cEntries.stream().forEach(e -> System.out.println(e.toString()));
        parser.oEntries.stream().forEach(e -> System.out.println(e.toString()));
        loadInstance(filename);

        new XCSPParser();
    }
}
