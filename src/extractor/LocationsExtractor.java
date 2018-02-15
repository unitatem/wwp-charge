package extractor;

import net.morbz.osmonaut.IOsmonautReceiver;
import net.morbz.osmonaut.Osmonaut;
import net.morbz.osmonaut.osm.Entity;
import net.morbz.osmonaut.osm.EntityType;
import net.morbz.osmonaut.osm.Tags;
import wwp.*;

public class LocationsExtractor {

    private Osmonaut naut;
    private Agglomerations agglomerations;
    private LocationsKeeper keeper;

    public LocationsExtractor(Osmonaut naut_, Agglomerations agglomerations_, LocationsKeeper keeper_) {
        naut = naut_;
        agglomerations = agglomerations_;
        keeper = keeper_;

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
                String city = generateCity(entity);
                keeper.addLocation(city, location);

                System.out.println(city + " " + address);
            }
        });
    }

    private String generateCity(Entity entity) {
        // get city of location
        String city = entity.getTags().get(OSM.ADDRESS_CITY);
        if (city == null) {
            // bounding box for every city
            for (City c : agglomerations.cities) {
                if (c.entity == null)
                    continue;

                boolean contains = c.entity.getBounds().contains(entity.getCenter());
                if (contains) {
                    System.out.println("HIT");
                    return c.name;
                }
            }

            return AgglomerationList.NOPE;
        }

        // if city is not a point of interest (country side)
        if (agglomerations.citiesLookUp.get(city) == null)
                return AgglomerationList.NOPE;

        // location belong to valid agglomeration
        return city;
    }

    private String generateAddress(Entity entity) {
        // TODO extract exact address
        return "" + entity.getCenter();
    }
}

