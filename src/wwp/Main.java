package wwp;

import java.io.IOException;

import extractor.AgglomerationsExtractor;
import net.morbz.osmonaut.osm.LatLon;
import solver.GA;
import utils.Geo;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        int numberOfChargers = 6000;
        int gaIterations = 500;

        System.out.println("START Loading maps data");

        Agglomerations agglomerations = new Agglomerations(numberOfChargers);
        LocationsKeeper locationsKeeper = new LocationsKeeper();

        AgglomerationsExtractor agglomerationsExtractor = null;
        for (AgglomerationList city : AgglomerationList.values())
            agglomerationsExtractor = new AgglomerationsExtractor(city, agglomerations, locationsKeeper);

        if (agglomerationsExtractor != null)
            agglomerationsExtractor.checkAfterCitiesScan();

        System.out.println("START GA");

        GA ga = new GA(agglomerations, locationsKeeper);
        for (int i = 0; i < gaIterations; ++i) {
            ga.step();
            System.out.println("Iteration = " + i + " ErrorWithHeuristic = " + ga.evaluateErrorHeuristic());
        }
        HashMap<String, ArrayList<Location>> bestPopulation = ga.getPopulation();


        LatLon latLonWarsaw = Geo.centerOfMass(bestPopulation.get(AgglomerationList.WARSAW.getCityName()));
        double distWarsaw = Geo.distance(agglomerations.citiesLookUp
                .get(AgglomerationList.WARSAW.getCityName()).entity.getCenter(), latLonWarsaw);
        System.out.println("Warszawa dystans od srodka = " + distWarsaw);

        LatLon latLonWroclaw = Geo.centerOfMass(bestPopulation.get(AgglomerationList.WARSAW.getCityName()));
        double distWroclaw = Geo.distance(agglomerations.citiesLookUp
                .get(AgglomerationList.WARSAW.getCityName()).entity.getCenter(), latLonWroclaw);
        System.out.println("Wroclas dystans od srodka = " + distWroclaw);

//        ArrayList<Location> countryLocations = new ArrayList<>();
//        CountryLocationsExtractor countryLocationsExtractor = new CountryLocationsExtractor(locationsKeeper, countryLocations);

        int idx = 1;
        try {
            PrintWriter writer = new PrintWriter("lista1.txt", "UTF-8");
            for (AgglomerationList agglomeration : AgglomerationList.values()) {
                for (Location location : bestPopulation.get(agglomeration.getCityName())) {
                    writer.println(idx + " 3 " + location.entity.getCenter());
                    ++idx;
                }
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            PrintWriter writer = new PrintWriter("out.txt", "UTF-8");
            writer.println("Krzemowe Mozgi kdmszk03");
            writer.println("https://github.com/unitatem/wwp-charge");
            writer.println("" + (int) (ga.evaluateError() * 1.3 / 1000) + "km (direct)");
            writer.println("");
            writer.println("brak odleglosci " + Geo.centerOfMass(bestPopulation.get(AgglomerationList.WARSAW.getCityName())));
            writer.println("brak odleglosci " + Geo.centerOfMass(bestPopulation.get(AgglomerationList.WROCLAW.getCityName())));
            long estimatedTime = System.currentTimeMillis() - startTime;
            writer.println("" + estimatedTime + "ms");
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("END");
    }
}
