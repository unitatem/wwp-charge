package extractor;

import net.morbz.osmonaut.EntityFilter;
import net.morbz.osmonaut.IOsmonautReceiver;
import net.morbz.osmonaut.Osmonaut;
import net.morbz.osmonaut.osm.Entity;
import net.morbz.osmonaut.osm.EntityType;
import net.morbz.osmonaut.osm.Tags;
import wwp.*;

import java.util.Objects;

public class AgglomerationsExtractor {

    private AgglomerationList city;
    private Agglomerations agglomerations;
    private LocationsKeeper keeper;
    private Osmonaut naut;

    public AgglomerationsExtractor(AgglomerationList city_, Agglomerations agglomerations_, LocationsKeeper keeper_) {
        city = city_;
        agglomerations = agglomerations_;
        keeper = keeper_;

        System.out.println("\t\t\t\t\t" + city.getCityName());

        // Set which OSM entities should be scanned (only nodes and ways in this case)
        EntityFilter generalFilter = new EntityFilter(true, true, false);
        // Set the binary OSM source file
        naut = new Osmonaut("/tmp/osm/agglomerations/" + city.getFileName() + ".pbf", generalFilter);

        doCityScan();
        doLocationsScan();
    }

    private void doCityScan() {
        naut.scan(new IOsmonautReceiver() {
            @Override
            public boolean needsEntity(EntityType type, Tags tags) {
                // key, key_value
                return (tags.hasKeyValue(OSM.PLACE, OSM.CITY) && tags.hasKey(OSM.NAME));
            }

            @Override
            public void foundEntity(Entity entity) {
                String name = entity.getTags().get(OSM.NAME);
                City city = agglomerations.citiesLookUp.get(name);
                if (city != null) {
                    city.setEntity(entity);
//                    System.out.println("extractor.AgglomerationsExtractor => Found: " + name);
                } else if (Objects.equals(name,"Gdynia")) {
                    city = agglomerations.citiesLookUp.get(AgglomerationList.TROJMIASTO.getCityName());
                    city.setEntity(entity);
                }
            }
        });
    }

    private void doLocationsScan() {
        naut.scan(new LocationScanner() {
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

    public void checkAfterCitiesScan() {
        for (City city : agglomerations.cities) {
            if (city.entity == null)
                System.out.println("ERROR extractor.AgglomerationsExtractor => Not found: " + city.name);
        }
    }
}

