package wwp;

import extractor.AgglomerationsExtractor;
import extractor.CountryLocationsExtractor;
import solver.Test;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        System.out.println("START");

        Agglomerations agglomerations = new Agglomerations();
        LocationsKeeper locationsKeeper = new LocationsKeeper();

        AgglomerationsExtractor agglomerationsExtractor;
//        for (AgglomerationList city : AgglomerationList.values())
//            agglomerationsExtractor = new AgglomerationsExtractor(city, agglomerations, locationsKeeper);
//        agglomerationsExtractor = new AgglomerationsExtractor(AgglomerationList.KATOWICE, agglomerations, locationsKeeper);
        agglomerationsExtractor = new AgglomerationsExtractor(AgglomerationList.WARSAW, agglomerations, locationsKeeper);
//        agglomerationsExtractor = new AgglomerationsExtractor(AgglomerationList.LODZ, agglomerations, locationsKeeper);
//        agglomerationsExtractor = new AgglomerationsExtractor(AgglomerationList.CRACOW, agglomerations, locationsKeeper);

        agglomerationsExtractor.checkAfterCitiesScan();

        ArrayList<Location> countryLocations = new ArrayList<>();
        CountryLocationsExtractor countryLocationsExtractor = new CountryLocationsExtractor(locationsKeeper, countryLocations);

        Test test = new Test(locationsKeeper);
        AgglomerationList.getAt(1);


//        // Start scanning by implementing the interface
//        naut.scan(new IOsmonautReceiver() {
//            @Override
//            public boolean needsEntity(EntityType type, Tags tags) {
//                // key, key_value
//                return (tags.hasKeyValue("place", "city") && tags.hasKey("name"));
//            }
//
//            @Override
//            public void foundEntity(Entity entity) {
//                // Print name and center coordinates
//                String name = entity.getTags().get("name");
//                Iterator<String> iter = entity.getTags().iterator();
//                for (; iter.hasNext();) {
//                    String s = (String) iter.next();
//                    System.out.print(s + " ");
//                }
//                System.out.println(name + ": " + entity.getCenter());
//            }
//        });

        System.out.println("END");
    }
}
