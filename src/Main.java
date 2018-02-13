import net.morbz.osmonaut.EntityFilter;
import net.morbz.osmonaut.IOsmonautReceiver;
import net.morbz.osmonaut.Osmonaut;
import net.morbz.osmonaut.osm.Entity;
import net.morbz.osmonaut.osm.EntityType;
import net.morbz.osmonaut.osm.Tags;

public class Main {

    public static void main(String[] args) {
        System.out.println("START");

        // Set which OSM entities should be scanned (only nodes and ways in this case)
        EntityFilter filter = new EntityFilter(true, true, false);

        // Set the binary OSM source file
        Osmonaut naut = new Osmonaut("data/osm/poland-latest.osm.pbf", filter);

        // Start scanning by implementing the interface
        naut.scan(new IOsmonautReceiver() {
            @Override
            public boolean needsEntity(EntityType type, Tags tags) {
                // Only lakes with names
//                return (tags.hasKeyValue("natural", "water") && tags.hasKey("name"));
                return (tags.hasKeyValue("amenity", "charging_station") && tags.hasKey("name"));
            }

            @Override
            public void foundEntity(Entity entity) {
                // Print name and center coordinates
                String name = entity.getTags().get("name");
                System.out.println(name + ": " + entity.getCenter());
            }
        });

        System.out.println("END");
    }
}
