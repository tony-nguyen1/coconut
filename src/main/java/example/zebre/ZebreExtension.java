package example.zebre;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.constraints.extension.Tuples;
import org.chocosolver.solver.search.limits.ICounter;
import org.chocosolver.solver.search.limits.NodeCounter;
import org.chocosolver.solver.search.loop.monitors.IMonitorRestart;
import org.chocosolver.solver.search.loop.monitors.SearchMonitorList;
import org.chocosolver.solver.search.restart.LinearCutoff;
import org.chocosolver.solver.search.strategy.Search;
import org.chocosolver.solver.search.strategy.assignments.DecisionOperatorFactory;
import org.chocosolver.solver.search.strategy.decision.Decision;
import org.chocosolver.solver.search.strategy.decision.IntDecision;
import org.chocosolver.solver.search.strategy.selectors.values.IntDomainMin;
import org.chocosolver.solver.search.strategy.selectors.values.SetDomainMin;
import org.chocosolver.solver.search.strategy.selectors.variables.AbstractCriterionBasedVariableSelector;
import org.chocosolver.solver.search.strategy.selectors.variables.CustomDomOverWDeg;
import org.chocosolver.solver.search.strategy.selectors.variables.DomOverWDeg;
import org.chocosolver.solver.search.strategy.selectors.variables.ImpactBased;
import org.chocosolver.solver.search.strategy.strategy.AbstractStrategy;
import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.solver.variables.SetVar;
import org.chocosolver.solver.variables.Variable;
import org.chocosolver.util.PoolManager;
import org.chocosolver.util.criteria.LongCriterion;
import org.chocosolver.util.iterators.IntVarValueIterator;

import java.util.Iterator;


public class ZebreExtension {

	public static void main(String[] args) {
		
		// Création du modele
		Model model = new Model("Zebre");
		
		
		// Création des variables
		IntVar blu = model.intVar("Blue", 1, 5);	// blu est une variable entière dont le nom est "Blue" est le domaine [1,5]
		IntVar gre = model.intVar("Green", 1, 5); 
		IntVar ivo = model.intVar("Ivory", 1, 5);         
		IntVar red = model.intVar("Red", 1, 5);         
		IntVar yel = model.intVar("Yellow", 1, 5);

        System.out.println("All the values of blu");
        Iterator<Integer> myIterator = new IntVarValueIterator(blu);
        System.out.println(myIterator.hasNext());
        while(myIterator.hasNext()) {
            System.out.println(myIterator.next());
        }
        blu.forEachIntVar(x -> System.out.println(x));
        for (Integer i : blu) {
            System.out.println(i);
        }

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

        for (Variable v : model.getVars()) {
            if (!v.isInstantiated()) {
//                trouveUn = true;
                System.out.println(v.getName()+" n'est pas instancié!!!!!!!!!!!!!!!!!!!!!!!!!");
            }
        }

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

        
        /************************************************************************
         *                                                                      *
         * Compléter en ajoutant les contraintes modélisant les phrases 2 à 15  *
         *                                                                      *
         ************************************************************************/

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

//        model.getSolver().setSearch(new AbstractStrategy<IntVar>(tabIntVar) {
//            // enables to recycle decision objects (good practice)
//            PoolManager<IntDecision> pool = new PoolManager();
//            @Override
//            public Decision getDecision() {
//                IntDecision d = pool.getE();
//                if(d==null) d = new IntDecision(pool);
//                IntVar next = null;
//                for(IntVar v:vars){
//                    if(!v.isInstantiated()){
//                        next = v; break;
//                    }
//                }
//                if(next == null){
//                    return null;
//                }else {
//                    // next decision is assigning nextVar to its lower bound
//                    d.set(next,next.getLB(), DecisionOperatorFactory.makeIntEq());//fixme la doc est pas à jour
//                    return d;
//                }
//            }
//        });



        // Désactiver les redémarrages
//        model.getSolver().plugMonitor((IMonitorRestart) () -> {
//            // Ne rien faire dans cette méthode pour désactiver les redémarrages
//        });


        // Les heuristiques de bases
//        model.getSolver().setSearch(Search.domOverWDegSearch(tabIntVar));
//        model.getSolver().setSearch(Search.domOverWDegRefSearch(tabIntVar));
        model.getSolver().setSearch(Search.activityBasedSearch(tabIntVar));
//        model.getSolver().setSearch(Search.conflictHistorySearch(tabIntVar));
//        model.getSolver().setSearch(Search.randomSearch(tabIntVar,0));
//        model.getSolver().setSearch(new ImpactBased(tabIntVar,null,0,0,0,0,true));

        AbstractStrategy<IntVar> maStrat = Search.intVarSearch(new CustomDomOverWDeg<>(tabIntVar, 0), new IntDomainMin(), tabIntVar);
        model.getSolver().setSearch(maStrat);



        // activation print sol quand trouvé
        model.getSolver().showSolutions();

        // activation print décision [1/2] affectation  [2/2] refutation
        model.getSolver().showDecisions();

        LongCriterion longCriterion = ICounter.Impl.None;
//        model.getSolver().setRestarts(longCriterion, new LinearCutoff(Long.MAX_VALUE) {
//            @Override
//            public long getNextCutoff() {
//                return Long.MAX_VALUE;
//            }
//
//        },0);

        model.getSolver().setGeometricalRestart(1000,2,new NodeCounter(model,1000),0);//fixme ça marche pas

        System.out.println("dom="+blu.getDomainSize());
        // Calcul de la première solution
        if(model.getSolver().solve()) {
//        	System.out.println("\n\n*** Première solution ***");
//        	System.out.println(model);
        }

        // var\int refutation   var=int affectation
        System.out.println(model.getSolver().getDecisionPath());



        // Calcul de toutes les solutions
//    	System.out.println("\n\n*** Autres solutions ***");
//        while(model.getSolver().solve()) {
//            System.out.println("Sol "+ model.getSolver().getSolutionCount()+"\n"+model);
//	    }


        // Affichage de l'ensemble des caractéristiques de résolution
        System.out.println("\n\n*** Bilan ***");
        model.getSolver().printStatistics();

        System.out.println("solFound;nbSol;readingTime;time;timeToBestSol;hasObjective;nbNoeuds;nbBacktrack;nbBackjump;nbFail;nbRestart");
        System.out.println(model.getSolver().toCSV());
        System.out.println(model.getSolver().getSearch().getClass());

	}
}
