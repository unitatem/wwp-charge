import net.morbz.osmonaut.EntityFilter;
import net.morbz.osmonaut.IOsmonautReceiver;
import net.morbz.osmonaut.Osmonaut;
import net.morbz.osmonaut.osm.Entity;
import net.morbz.osmonaut.osm.EntityType;
import net.morbz.osmonaut.osm.Tags;

import java.util.Iterator;

public class Main {

    public static void main(String[] args) {
        System.out.println("START");

        Agglomerations agglomerations = new Agglomerations();


        // Set which OSM entities should be scanned (only nodes and ways in this case)
        EntityFilter filter = new EntityFilter(true, true, false);

        // Set the binary OSM source file
        Osmonaut naut = new Osmonaut("/tmp/osm/poland-latest.osm.pbf", filter);

        // Start scanning by implementing the interface
        naut.scan(new IOsmonautReceiver() {
            @Override
            public boolean needsEntity(EntityType type, Tags tags) {
                // key, key_value
                return (tags.hasKeyValue("place", "city") && tags.hasKey("name"));
            }

            @Override
            public void foundEntity(Entity entity) {
                // Print name and center coordinates
                String name = entity.getTags().get("name");
                Iterator<String> iter = entity.getTags().iterator();
                for (; iter.hasNext();) {
                    String s = (String) iter.next();
                    System.out.print(s + " ");
                }
                System.out.println(name + ": " + entity.getCenter());
            }
        });

        System.out.println("END");
    }
}
