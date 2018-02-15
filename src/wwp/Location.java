package wwp;

import net.morbz.osmonaut.osm.Entity;

public class Location {

    public int stationsCount;
    public String address;
    public Entity entity;

    public Location(String address_, Entity entity_) {
        address = address_;
        entity = entity_;
    }
}
