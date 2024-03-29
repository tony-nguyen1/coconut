package example.zebre;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.constraints.extension.Tuples;
import org.chocosolver.solver.search.strategy.Search;
import org.chocosolver.solver.variables.IntVar;

public class ZebreExtentionClean {
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



        //C2
        model.table(new IntVar[]{eng,red}, new Tuples(new int[][]{{1,1},{2,2},{3,3},{4,4},{5,5}},true)).post();
        //C3
        model.table(new IntVar[]{spa,dog}, new Tuples(new int[][]{{1,1},{2,2},{3,3},{4,4},{5,5}},true)).post();
        //C4
        model.table(new IntVar[]{cof,gre}, new Tuples(new int[][]{{1,1},{2,2},{3,3},{4,4},{5,5}},true)).post();
        //C5
        model.table(new IntVar[]{ukr,tea}, new Tuples(new int[][]{{1,1},{2,2},{3,3},{4,4},{5,5}},true)).post();
        //C6
        model.table(new IntVar[]{gre,ivo}, new Tuples(new int[][]{{5,4},{4,3},{3,2},{2,1}},true)).post();
        //C7
        model.table(new IntVar[]{old,sna}, new Tuples(new int[][]{{1,1},{2,2},{3,3},{4,4},{5,5}},true)).post();
        //C8
        model.table(new IntVar[]{koo,yel}, new Tuples(new int[][]{{1,1},{2,2},{3,3},{4,4},{5,5}},true)).post();
        //C9
        model.table(new IntVar[]{mil}, new Tuples(new int[][]{{3}},true)).post();
        //C10
        model.table(new IntVar[]{nor}, new Tuples(new int[][]{{1}},true)).post();
        //C11
        model.table(new IntVar[]{che,fox}, new Tuples(new int[][]{{1,2},{2,3},{2,1},{3,2},{3,4},{4,3},{4,5}},true)).post();
        //C12
        model.table(new IntVar[]{koo,hor}, new Tuples(new int[][]{{1,2},{2,1},{2,3},{3,2},{3,4},{4,3},{4,5}},true)).post();
        //C13
        model.table(new IntVar[]{luc,ora}, new Tuples(new int[][]{{1,1},{2,2},{3,3},{4,4},{5,5}},true)).post();
        //C14
        model.table(new IntVar[]{jap,par}, new Tuples(new int[][]{{1,1},{2,2},{3,3},{4,4},{5,5}},true)).post();
        //C15
        model.table(new IntVar[]{nor,blu}, new Tuples(new int[][]{{1,2},{2,1},{2,3},{3,2},{3,4},{4,3},{4,5}},true)).post();
        model.table(new IntVar[]{nor,blu}, new Tuples(new int[][]{{1,2},{2,1},{2,3},{3,2},{3,4},{4,3},{4,5}},true)).post();



        // Affichage du réseau de contraintes créé
        System.out.println("*** Réseau Initial ***");
        System.out.println(model);

        model.getSolver().clearRestarter();

        // Les heuristiques
//        model.getSolver().setSearch(Search.domOverWDegSearch(tabIntVar));
//        model.getSolver().setSearch(Search.domOverWDegRefSearch(tabIntVar));
        model.getSolver().setSearch(Search.activityBasedSearch(tabIntVar));
//        model.getSolver().setSearch(Search.conflictHistorySearch(tabIntVar));
//        model.getSolver().setSearch(Search.randomSearch(tabIntVar,0));
//        model.getSolver().setSearch(new ImpactBased(tabIntVar,null,0,0,0,0,true));

//        AbstractStrategy<IntVar> maStrat = Search.intVarSearch(new CustomDomOverWDeg<>(tabIntVar, 0), new IntDomainMin(), tabIntVar);
//        model.getSolver().setSearch(maStrat);



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
