package solver;

import utils.Geo;
import wwp.AgglomerationList;
import wwp.Agglomerations;
import wwp.Location;
import wwp.LocationsKeeper;

import java.util.*;

public class GA {

    private ArrayList<Pair<Double, HashMap<String, ArrayList<Location>>>> population;
    private Agglomerations agglomerations;
    private LocationsKeeper locationsKeeper;

    private int populationSize = 10;

    public GA(Agglomerations agglomerations_, LocationsKeeper locationsKeeper_) {
        population = new ArrayList<>();
        agglomerations = agglomerations_;
        locationsKeeper = locationsKeeper_;

        initialize();

    }

    public HashMap<String, ArrayList<Location>> getPopulation() {
        return population.get(0).getElement1();
    }

    public void step() {
        selection();
        crossOver();
        mutation();
    }

    public double evaluateError() {
        return 0.0;
    }
    
    private void initialize() {
        for (int i = 0; i < populationSize; ++i) {
            RandomLocations rl = new RandomLocations(agglomerations, locationsKeeper);
            HashMap<String, ArrayList<Location>> randomLocations = rl.randomLocations;
            double minDist = Geo.minCityDistanceHeuristic(agglomerations, randomLocations);
            double punishment = Geo.centerOfMass(agglomerations, randomLocations);
            population.add(new Pair(minDist + punishment, randomLocations));
        }
    }

    private void selection() {
        Collections.sort(population, new Comparator<Pair<Double, HashMap<String, ArrayList<Location>>>>() {
            public int compare(Pair p1, Pair p2){
                double v1 = (double)p1.getElement0();
                double v2 = (double)p2.getElement0();
                if (v1 > v2)
                    return 1;
                else if (v1 < v2)
                    return -1;
                else
                    return 0;
            }
        });

        ArrayList<Pair<Double, HashMap<String, ArrayList<Location>>>> newPopulation = new ArrayList<>();
        for (int i = 0; i < population.size() / 2; ++i)
            newPopulation.add(population.get(i));

        population = newPopulation;
    }

    private void crossOver() {
        for (int i = population.size(); i < populationSize; ++i) {
            HashMap<String, ArrayList<Location>> child = makeChild();
            double minDist = Geo.minCityDistanceHeuristic(agglomerations, child);
            double punishment = Geo.centerOfMass(agglomerations, child);
            population.add(new Pair(minDist + punishment, child));
        }
    }

    private HashMap<String, ArrayList<Location>> makeChild() {
        Random rand = new Random();
        int obj1 = rand.nextInt(populationSize / 2);
        int obj2 = rand.nextInt(populationSize / 2);
        int switchIdx = rand.nextInt(agglomerations.cities.size());

        HashMap<String, ArrayList<Location>> child = new HashMap<>();

        for (int i = 0; i < switchIdx; ++i) {
            String cityName = agglomerations.cities.get(obj1).name;
            ArrayList<Location> chargers = locationsKeeper.agglomeration.get(cityName);
            child.put(cityName, chargers);
        }
        for (int i = switchIdx; i < agglomerations.cities.size(); ++i) {
            String cityName = agglomerations.cities.get(obj2).name;
            ArrayList<Location> chargers = locationsKeeper.agglomeration.get(cityName);
            child.put(cityName, chargers);
        }

        return child;
    }

    private void mutation() {
        // TODO
    }
}
