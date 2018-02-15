package wwp;

import extractor.AgglomerationsExtractor;
import extractor.LocationsExtractor;
import net.morbz.osmonaut.EntityFilter;
import net.morbz.osmonaut.Osmonaut;
import solver.Test;

public class Main {

    public static void main(String[] args) {
        System.out.println("START");

//        Agglomerations agglomerations = new Agglomerations();
//        AgglomerationsExtractor agglomerationsExtractor = new AgglomerationsExtractor(agglomerations);

        LocationsKeeper locationsKeeper = new LocationsKeeper();
        LocationsExtractor locationsExtractor = null;
//        for (AgglomerationList city : AgglomerationList.values())
//            locationsExtractor = new LocationsExtractor(locationsKeeper, city);
        locationsExtractor = new LocationsExtractor(locationsKeeper, AgglomerationList.KATOWICE);
        locationsExtractor = new LocationsExtractor(locationsKeeper, AgglomerationList.WARSAW);
        locationsExtractor = new LocationsExtractor(locationsKeeper, AgglomerationList.CRACOW);
        locationsExtractor = new LocationsExtractor(locationsKeeper, AgglomerationList.LODZ);

        Test test = new Test(locationsKeeper);


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
