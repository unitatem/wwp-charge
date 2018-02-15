package extractor;

import net.morbz.osmonaut.EntityFilter;
import net.morbz.osmonaut.IOsmonautReceiver;
import net.morbz.osmonaut.Osmonaut;
import net.morbz.osmonaut.osm.Entity;
import net.morbz.osmonaut.osm.EntityType;
import net.morbz.osmonaut.osm.Tags;
import wwp.*;

public class LocationsExtractor {

    private LocationsKeeper keeper;
    private AgglomerationList city;
    private Osmonaut naut;

    public LocationsExtractor(LocationsKeeper keeper_, AgglomerationList city_) {
        keeper = keeper_;
        city = city_;

        System.out.println(city.getCityName());

        // Set which OSM entities should be scanned (only nodes and ways in this case)
        EntityFilter generalFilter = new EntityFilter(true, true, false);
        // Set the binary OSM source file
        naut = new Osmonaut("/tmp/osm/" + city.getFileName() + ".pbf", generalFilter);

        doScan();
    }

    private void doScan() {
        naut.scan(new IOsmonautReceiver() {
            @Override
            public boolean needsEntity(EntityType type, Tags tags) {
                // key, key_value
                return tags.hasKeyValue(OSM.SHOP, OSM.SUPERMARKET)
                        || tags.hasKeyValue(OSM.OFFICE, OSM.GOVERNMENT)
                        || tags.hasKeyValue(OSM.BUILDING, OSM.CIVIC)
                        || tags.hasKeyValue(OSM.BUILDING, OSM.TRANSFORMER_TOWER);
            }

            @Override
            public void foundEntity(Entity entity) {
                // find address of location
                String address = generateAddress(entity);
                Location location = new Location(address, entity);
                // assign location to city
                keeper.addLocation(city.getCityName(), location);

//                System.out.println(city.getCityName() + " " + address);
            }
        });
    }

    private String generateAddress(Entity entity) {
        return "" + entity.getCenter();
    }
}

