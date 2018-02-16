package wwp;

import java.io.IOException;

import org.pcj.*;

import extractor.AgglomerationsExtractor;
import net.morbz.osmonaut.osm.LatLon;
import solver.GA;
import solver.Pair;
import utils.Geo;

import java.util.ArrayList;
import java.util.HashMap;

@RegisterStorage(Main.Shared.class)
public class Main implements StartPoint {

    @Storage(Main.class)
    public enum Shared {
        agglomerations,
        locationsKeeper,
        population
    }

    public Agglomerations agglomerations = new Agglomerations(6000);
    public LocationsKeeper locationsKeeper = new LocationsKeeper();
    HashMap<String, ArrayList<Location>> country = new HashMap<>();

    public static void main(String[] args) throws IOException {
//        String[] nodes = new String[]{"localhost", "localhost"};
//        PCJ.deploy(Main.class, nodes);

        String nodesFile = "nodes.txt";
        PCJ.start(Main.class, new NodesDescription(nodesFile));
    }

    @Override
    public void main() {
        if (PCJ.myId() == 0) {
            System.out.println("START Loading maps data");

            AgglomerationsExtractor agglomerationsExtractor = null;
            for (AgglomerationList city : AgglomerationList.values())
                agglomerationsExtractor = new AgglomerationsExtractor(city, agglomerations, locationsKeeper);

            if (agglomerationsExtractor != null)
                agglomerationsExtractor.checkAfterCitiesScan();

            System.out.println("START GA");
        }

        PCJ.monitor(Shared.agglomerations);
        PCJ.monitor(Shared.locationsKeeper);
        if (PCJ.myId() == 0) {
            PCJ.asyncBroadcast(agglomerations, Shared.agglomerations);
            PCJ.asyncBroadcast(locationsKeeper, Shared.locationsKeeper);
        }
        PCJ.waitFor(Shared.agglomerations);
        PCJ.waitFor(Shared.locationsKeeper);

        GA ga = new GA(agglomerations, locationsKeeper);
        for (int i = 0; i < 10; ++i) {
            ga.step();
            if (PCJ.myId() == 0)
                System.out.println("iter = " + i + " error = " + ga.evaluateError());
        }

        HashMap<String, ArrayList<Location>> bestPopulation = ga.getPopulation();
        LatLon latLon = Geo.centerOfMass(bestPopulation.get(AgglomerationList.WARSAW.getCityName()));
        double dist = Geo.distance(agglomerations.citiesLookUp
                .get(AgglomerationList.WARSAW.getCityName()).entity.getCenter(), latLon);

        System.out.println("Warszawa dystans = " + dist);

//        ArrayList<Location> countryLocations = new ArrayList<>();
//        CountryLocationsExtractor countryLocationsExtractor = new CountryLocationsExtractor(locationsKeeper, countryLocations);


        System.out.println("END");
    }
}
