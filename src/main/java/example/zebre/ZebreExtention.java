package example.zebre;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.constraints.extension.Tuples;
import org.chocosolver.solver.search.strategy.Search;
import org.chocosolver.solver.search.strategy.selectors.values.IntDomainMin;
import org.chocosolver.solver.search.strategy.selectors.variables.*;
import org.chocosolver.solver.search.strategy.strategy.AbstractStrategy;
import org.chocosolver.solver.variables.IntVar;

public class ZebreExtention {
    public static void main(String[] args) {

        // Création du modele
        Model model = new Model("Zebre");


        // Création des variables
        IntVar blu = model.intVar("Blue", 1, 5);	// blu est une variable entière dont le nom est "Blue" est le domaine [1,5]
        IntVar gre = model.intVar("Green", 1, 5);
        IntVar ivo = model.intVar("Ivory", 1, 5);
        IntVar red = model.intVar("Red", 1, 5);
        IntVar yel = model.intVar("Yellow", 1, 5);

        IntVar eng = model.intVar("English", 1, 5);
        IntVar jap = model.intVar("Japanese", 1, 5);
        IntVar nor = model.intVar("Norwegian", 1, 5);
        IntVar spa = model.intVar("Spanish", 1, 5);
        IntVar ukr = model.intVar("Ukrainian", 1, 5);

        IntVar cof = model.intVar("Coffee", 1, 5);
        IntVar mil = model.intVar("Milk", 1, 5);
        IntVar ora = model.intVar("Orange Juice", 1, 5);
        IntVar tea = model.intVar("Tea", 1, 5);
        IntVar wat = model.intVar("Water", 1, 5);

        IntVar dog = model.intVar("Dog", 1, 5);
        IntVar fox = model.intVar("Fox", 1, 5);
        IntVar hor = model.intVar("Horse", 1, 5);
        IntVar sna = model.intVar("Snail", 1, 5);
        IntVar zeb = model.intVar("Zebra", 1, 5);

        IntVar che = model.intVar("Chesterfield", 1, 5);
        IntVar koo = model.intVar("Kool", 1, 5);
        IntVar luc = model.intVar("Lucky Strike", 1, 5);
        IntVar old = model.intVar("Old Gold", 1, 5);
        IntVar par = model.intVar("Parliament", 1, 5);

        IntVar[] tabIntVar = new IntVar[]{
                blu,gre,ivo,red,yel,
                eng,jap,nor,spa,ukr,
                cof,mil,ora,tea,wat,
                dog,fox,hor,sna,zeb,
                che,koo,luc,old,par
        };


        // Création des contraintes
        int [][] tEq = new int[][] {{1,1},{2,2},{3,3},{4,4},{5,5}};
        Tuples tuplesAutorises = new Tuples(tEq,true);		// création de Tuples de valeurs autorisés
        Tuples tuplesInterdits = new Tuples(tEq,false);		// création de Tuples de valeurs interdits

        model.table(new IntVar[]{blu,gre}, tuplesInterdits).post();
        // création d'une contrainte en extension de portée <blu,gre>
        // dont les tuples autorisés/interdits sont données par tuplesInterdits
        model.table(new IntVar[]{blu,ivo}, tuplesInterdits).post();
        model.table(new IntVar[]{blu,red}, tuplesInterdits).post();
        model.table(new IntVar[]{blu,yel}, tuplesInterdits).post();
        model.table(new IntVar[]{gre,ivo}, tuplesInterdits).post();
        model.table(new IntVar[]{gre,red}, tuplesInterdits).post();
        model.table(new IntVar[]{gre,yel}, tuplesInterdits).post();
        model.table(new IntVar[]{ivo,red}, tuplesInterdits).post();
        model.table(new IntVar[]{ivo,yel}, tuplesInterdits).post();
        model.table(new IntVar[]{red,yel}, tuplesInterdits).post();

        model.table(new IntVar[]{eng,jap}, tuplesInterdits).post();
        model.table(new IntVar[]{eng,nor}, tuplesInterdits).post();
        model.table(new IntVar[]{eng,spa}, tuplesInterdits).post();
        model.table(new IntVar[]{eng,ukr}, tuplesInterdits).post();
        model.table(new IntVar[]{jap,nor}, tuplesInterdits).post();
        model.table(new IntVar[]{jap,spa}, tuplesInterdits).post();
        model.table(new IntVar[]{jap,ukr}, tuplesInterdits).post();
        model.table(new IntVar[]{nor,spa}, tuplesInterdits).post();
        model.table(new IntVar[]{nor,ukr}, tuplesInterdits).post();
        model.table(new IntVar[]{spa,ukr}, tuplesInterdits).post();

        model.table(new IntVar[]{cof,mil}, tuplesInterdits).post();
        model.table(new IntVar[]{cof,ora}, tuplesInterdits).post();
        model.table(new IntVar[]{cof,tea}, tuplesInterdits).post();
        model.table(new IntVar[]{cof,wat}, tuplesInterdits).post();
        model.table(new IntVar[]{mil,ora}, tuplesInterdits).post();
        model.table(new IntVar[]{mil,tea}, tuplesInterdits).post();
        model.table(new IntVar[]{mil,wat}, tuplesInterdits).post();
        model.table(new IntVar[]{ora,tea}, tuplesInterdits).post();
        model.table(new IntVar[]{ora,wat}, tuplesInterdits).post();
        model.table(new IntVar[]{tea,wat}, tuplesInterdits).post();

        model.table(new IntVar[]{dog,fox}, tuplesInterdits).post();
        model.table(new IntVar[]{dog,hor}, tuplesInterdits).post();
        model.table(new IntVar[]{dog,sna}, tuplesInterdits).post();
        model.table(new IntVar[]{dog,zeb}, tuplesInterdits).post();
        model.table(new IntVar[]{fox,hor}, tuplesInterdits).post();
        model.table(new IntVar[]{fox,sna}, tuplesInterdits).post();
        model.table(new IntVar[]{fox,zeb}, tuplesInterdits).post();
        model.table(new IntVar[]{hor,sna}, tuplesInterdits).post();
        model.table(new IntVar[]{hor,zeb}, tuplesInterdits).post();
        model.table(new IntVar[]{sna,zeb}, tuplesInterdits).post();

        model.table(new IntVar[]{che,koo}, tuplesInterdits).post();
        model.table(new IntVar[]{che,luc}, tuplesInterdits).post();
        model.table(new IntVar[]{che,old}, tuplesInterdits).post();
        model.table(new IntVar[]{che,par}, tuplesInterdits).post();
        model.table(new IntVar[]{koo,luc}, tuplesInterdits).post();
        model.table(new IntVar[]{koo,old}, tuplesInterdits).post();
        model.table(new IntVar[]{koo,par}, tuplesInterdits).post();
        model.table(new IntVar[]{luc,old}, tuplesInterdits).post();
        model.table(new IntVar[]{luc,par}, tuplesInterdits).post();
        model.table(new IntVar[]{old,par}, tuplesInterdits).post();


        int [][] aCoteDe = new int[][] {{2,1},{3,2},{4,3},{5,4},     {1,2},{2,3},{3,4},{4,5}};
        Tuples tuplesACoteDe = new Tuples(aCoteDe,true);

        model.arithm(eng,"=",red).post();
        // 2.
        model.arithm(spa,"=",dog).post();
        // 3.
        model.arithm(cof,"=",gre).post();
        // 4.
        model.arithm(ukr,"=",tea).post();
        // 5.
        model.arithm(ivo,"-",gre,"=",-1).post();
        // 6.
        model.arithm(old,"=",sna).post();
        // 7.
        model.arithm(koo,"=",yel).post();
        // 8.
        model.arithm(mil,"=",3).post();
        // 9.
        model.arithm(nor,"=",1).post();
        // 10.

        // 11.
        model.arithm(che,"-",fox,"<=",1).post();
        model.arithm(che,"-",fox,"!=",0).post();
        model.arithm(che,"-",fox,">=",-1).post();

        // 12.
        model.arithm(koo,"-",hor,"<=",1).post();
        model.arithm(koo,"-",hor,"!=",0).post();
        model.arithm(koo,"-",hor,">=",-1).post();

        // 13.
        model.arithm(luc,"=",ora).post();

        // 14.
        model.arithm(jap,"=",par).post();

        // 15.
        model.arithm(nor,"-",blu,"<=",1).post();
        model.arithm(nor,"-",blu,"!=",0).post();
        model.arithm(nor,"-",blu,">=",-1).post();

        // Affichage du réseau de contraintes créé
        System.out.println("*** Réseau Initial ***");
        System.out.println(model);

        model.getSolver().clearRestarter();

        // Les heuristiques
//        model.getSolver().setSearch(Search.domOverWDegSearch(tabIntVar));
//        model.getSolver().setSearch(Search.domOverWDegRefSearch(tabIntVar));
//        model.getSolver().setSearch(Search.activityBasedSearch(tabIntVar));
//        model.getSolver().setSearch(Search.conflictHistorySearch(tabIntVar));
//        model.getSolver().setSearch(Search.randomSearch(tabIntVar,0));
//        model.getSolver().setSearch(new ImpactBased(tabIntVar,null,0,0,0,0,true));

        AbstractStrategy<IntVar> maStrat = Search.intVarSearch(new Ddeg<>(), new IntDomainMin(), tabIntVar);
        model.getSolver().setSearch(maStrat);

        model.getSolver().showSolutions();

        // Calcul de la première solution
        model.getSolver().clearRestarter();
        model.getSolver().solve();



        // Affichage de l'ensemble des caractéristiques de résolution
        System.out.println("\n\n*** Bilan ***");
        model.getSolver().printStatistics();
        System.out.println(model.getSolver().getSearch().getClass());
        System.out.println(model.getSolver().getRestarter().toString());
    }
}