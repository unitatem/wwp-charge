package solver;

import utils.Geo;
import wwp.Agglomerations;
import wwp.Location;
import wwp.LocationsKeeper;

import java.util.*;

public class GA {

    private ArrayList<Pair<Double, HashMap<String, ArrayList<Location>>>> population;
    private Agglomerations agglomerations;
    private LocationsKeeper locationsKeeper;

    private int populationSize = 300;

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

    public double evaluateErrorHeuristic() {
        return population.get(0).getElement0();
    }

    public double evaluateError() {
        sortPopulation();
        return Geo.minCityDistance(population.get(0).getElement1());
    }

    private void add2Population(HashMap<String, ArrayList<Location>> locations) {
        double minDist = Geo.minCityDistanceHeuristic(agglomerations, locations);
        double punishment = Geo.centerOfMassTotal(agglomerations, locations);
        population.add(new Pair(minDist + punishment * 100000, locations));
    }
    
    private void initialize() {
        for (int i = 0; i < populationSize; ++i) {
            RandomLocations rl = new RandomLocations(agglomerations, locationsKeeper);
            HashMap<String, ArrayList<Location>> randomLocations = rl.randomLocations;
            add2Population(randomLocations);
        }
    }

    private void sortPopulation() {
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
    }

    private void selection() {
        sortPopulation();
        ArrayList<Pair<Double, HashMap<String, ArrayList<Location>>>> newPopulation = new ArrayList<>();
        for (int i = 0; i < population.size() / 2; ++i)
            newPopulation.add(population.get(i));

        population = newPopulation;
    }

    private void crossOver() {
        for (int i = population.size(); i < populationSize; ++i) {
            HashMap<String, ArrayList<Location>> child = makeChild();
            add2Population(child);
        }
    }

    private HashMap<String, ArrayList<Location>> makeChild() {
        Random rand = new Random();
        int obj1 = rand.nextInt(populationSize / 2);
        int obj2 = rand.nextInt(populationSize / 2);
        int switchIdx = rand.nextInt(agglomerations.cities.size());

        HashMap<String, ArrayList<Location>> child = new HashMap<>();

        for (int i = 0; i < switchIdx; ++i) {
            String cityName = agglomerations.cities.get(i).name;
            ArrayList<Location> chargers = population.get(obj1).getElement1().get(cityName);
            child.put(cityName, chargers);
        }
        for (int i = switchIdx; i < agglomerations.cities.size(); ++i) {
            String cityName = agglomerations.cities.get(i).name;
            ArrayList<Location> chargers = population.get(obj2).getElement1().get(cityName);
            child.put(cityName, chargers);
        }

        return child;
    }

    private void mutation() {
        // TODO
    }
}
