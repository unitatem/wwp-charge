package wwp;

import extractor.AgglomerationsExtractor;
import extractor.CountryLocationsExtractor;
import solver.GA;
import solver.RandomLocations;
import solver.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        System.out.println("START Load");

        Agglomerations agglomerations = new Agglomerations();
        LocationsKeeper locationsKeeper = new LocationsKeeper();

        AgglomerationsExtractor agglomerationsExtractor = null;
        for (AgglomerationList city : AgglomerationList.values())
            agglomerationsExtractor = new AgglomerationsExtractor(city, agglomerations, locationsKeeper);

//        agglomerationsExtractor = new AgglomerationsExtractor(AgglomerationList.KATOWICE, agglomerations, locationsKeeper);

        if (agglomerationsExtractor != null)
            agglomerationsExtractor.checkAfterCitiesScan();

        System.out.println("START GA");
        GA ga = new GA(agglomerations, locationsKeeper);
        for (int i = 0; i < 10; ++i) {
            ga.step();
            System.out.println("iter = " + i + " error = " + ga.evaluateError());
        }
        HashMap<String, ArrayList<Location>> bestPopulation = ga.getPopulation();

//        ArrayList<Location> countryLocations = new ArrayList<>();
//        CountryLocationsExtractor countryLocationsExtractor = new CountryLocationsExtractor(locationsKeeper, countryLocations);


        System.out.println("END");
    }
}
