package extractor;

import net.morbz.osmonaut.EntityFilter;
import net.morbz.osmonaut.Osmonaut;
import net.morbz.osmonaut.osm.Entity;
import wwp.AgglomerationList;
import wwp.Location;
import wwp.LocationsKeeper;

import java.util.ArrayList;

public class CountryLocationsExtractor {

    private LocationsKeeper keeper;
    private ArrayList<Location> countryLocations;
    private Osmonaut naut;

    public CountryLocationsExtractor(LocationsKeeper keeper_, ArrayList<Location> countryLocations_) {
        keeper = keeper_;
        countryLocations = countryLocations_;

        // Set which OSM entities should be scanned (only nodes and ways in this case)
        EntityFilter generalFilter = new EntityFilter(true, true, false);
        // Set the binary OSM source file
        naut = new Osmonaut("/tmp/osm/poland-latest.osm.pbf", generalFilter);

        doScan();
    }

    private void doScan() {
        naut.scan(new LocationScanner() {
            @Override
            public void foundEntity(Entity entity) {
                for (AgglomerationList city : AgglomerationList.values()) {
                    ArrayList<Location> locationArrayList = keeper.agglomeration.get(city.getCityName());
                    if (locationArrayList == null)
                        continue;

                    for (Location loc : locationArrayList) {
                        if (loc.entity.equals(entity))
                            return;
                    }
                }

                // find address of location
                String address = generateAddress(entity);
                Location location = new Location(address, entity);
                // assign location to city
                countryLocations.add(location);

//                System.out.println(city.getCityName() + " " + address);
            }
        });
    }

    private String generateAddress(Entity entity) {
        return "" + entity.getCenter();
    }
}
