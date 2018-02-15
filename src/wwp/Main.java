package wwp;

import extractor.AgglomerationsExtractor;
import extractor.LocationsExtractor;
import net.morbz.osmonaut.EntityFilter;
import net.morbz.osmonaut.Osmonaut;

public class Main {

    public static void main(String[] args) {
        System.out.println("START");

        // Set which OSM entities should be scanned (only nodes and ways in this case)
        EntityFilter generalFilter = new EntityFilter(true, true, false);
        // Set the binary OSM source file
        Osmonaut naut = new Osmonaut("/tmp/osm/poland-latest.osm.pbf", generalFilter);

        Agglomerations agglomerations = new Agglomerations();
        AgglomerationsExtractor agglomerationsExtractor = new AgglomerationsExtractor(naut, agglomerations);

        LocationsKeeper locationsKeeper = new LocationsKeeper();
        LocationsExtractor locationsExtractor = new LocationsExtractor(naut, agglomerations, locationsKeeper);


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
