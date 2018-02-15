package wwp;

import java.util.ArrayList;
import java.util.HashMap;

public class LocationsKeeper {

    public HashMap<String, ArrayList<Location>> agglomeration;

    public LocationsKeeper() {
        agglomeration = new HashMap<>();
    }

    public void addLocation(String city, Location loc) {
        ArrayList locations =  agglomeration.get(city);
        if (locations == null) {
            locations = new ArrayList<>();
            agglomeration.put(city, locations);
        }
        locations.add(loc);
    }
}
